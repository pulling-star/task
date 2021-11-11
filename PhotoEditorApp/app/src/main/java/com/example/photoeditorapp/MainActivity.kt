package com.example.photoeditorapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.photoeditorapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.exifinterface.media.ExifInterface
import com.example.photoeditorapp.constants.Constants
import com.example.photoeditorapp.constants.Constants.REQUEST_CODE_PERMISSIONS


class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    private lateinit var _binding : ActivityMainBinding
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private var imageCapture: ImageCapture? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        clickListeners()
        // Request camera permissions
        if (allPermissionsGranted()) {
            Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }
        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun clickListeners() {
        _binding.selfieLinear.setOnClickListener(this)
        _binding.galleryLinear.setOnClickListener(this)
        _binding.captureLinear.setOnClickListener(this)
    }

    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return
        // Create time-stamped output file to hold the image
        val photoFile = File(outputDirectory, SimpleDateFormat(FILENAME_FORMAT, Locale.US
        ).format(System.currentTimeMillis()) + ".jpg")
        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        // Set up image capture listener, which is triggered after photo has been taken
        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(this), object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    val msg = "Photo capture succeeded: $savedUri"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, msg)
                    _binding.viewFinder.visibility= View.INVISIBLE
                    _binding.ivPlaceholder.visibility = View.VISIBLE
                    _binding.captureLinear.visibility = View.INVISIBLE
                    _binding.selfieLinear.visibility = View.VISIBLE
                    _binding.galleryLinear.visibility = View.VISIBLE
                    rotateBitmap(photoFile)
                }
            })
    }

    private fun rotateBitmap(photofile:File) {
        if (photofile.exists()){
            val bitmap = BitmapFactory.decodeFile(photofile.absolutePath)

            val exif = ExifInterface(photofile.absoluteFile.toString())
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            val matrix = android.graphics.Matrix()

            when(orientation){
                ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90F)
                ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180F)
                ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270F)
            }

            val rotatedBitmap = Bitmap.createBitmap(bitmap, 0,0 , bitmap.width, bitmap.height, matrix, true)
            bitmap.recycle()
            _binding.ivPlaceholder.setImageBitmap(rotatedBitmap)
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            // Preview
            val preview = Preview.Builder()
                .build()
                .also { it.setSurfaceProvider(viewFinder.surfaceProvider) }
            imageCapture = ImageCapture.Builder()
                .build()
            // Select front camera as a default
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()
                // Bind use cases to camera
                cameraProvider.bindToLifecycle(this, cameraSelector, preview,imageCapture)
            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.selfie_linear -> {
                setUpUI()
                startCamera()
            }
            R.id.gallery_linear -> {
                if(allPermissionsGranted()){
                    val intent = Intent()
                    intent.type = "image/*"
                    intent.action = Intent.ACTION_GET_CONTENT
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), Constants.IMAGE_SELECTION)
                }else{
                    allPermissionsGranted()
                }
            }
            R.id.capture_linear -> {
                takePhoto()
            }
        }

    }

    private fun setUpUI() {
        _binding.viewFinder.visibility = View.VISIBLE
        _binding.ivPlaceholder.visibility = View.INVISIBLE
        _binding.captureLinear.visibility = View.VISIBLE
        _binding.selfieLinear.visibility = View.INVISIBLE
        _binding.galleryLinear.visibility = View.INVISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            Constants.IMAGE_SELECTION -> when(resultCode){
                Activity.RESULT_OK -> {
                    val imageUri: Uri? = data?.data
                    val bitMap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                    _binding.viewFinder.visibility = View.INVISIBLE
                    _binding.ivPlaceholder.visibility = View.VISIBLE
                    _binding.captureLinear.visibility = View.INVISIBLE
                    _binding.selfieLinear.visibility = View.VISIBLE
                    _binding.galleryLinear.visibility = View.VISIBLE
                    _binding.ivPlaceholder.setImageBitmap(bitMap)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

}