package com.example.e_commerceapp.DrumActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.e_commerceapp.CartActivity.CartActivity
import com.example.e_commerceapp.R

class DrumActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var textViewDescription: TextView
    lateinit var buttonAdd: Button
    lateinit var buttonCartPreview:Button
    var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drum)
        bindViews()
        imageView.setImageResource(R.drawable.drumimage)
        textViewDescription.setText("Specification\n" +
                "Amount : 299.00\n" +
                "Binding : Electronics\n" +
                "Brand : Alesis\n" +
                "CurrencyCode : USD\n" +
                "Label : inMusic Brands inc.\n" +
                "Manufacturer : inMusic Brands inc.\n" +
                "Model : TURBOMESHKIT\n" +
                "PackageDimensions : L:31.8897637470 X W:19.0944881695 X H:11.8110236100\n" +
                "ProductTypeName : PERCUSSION_INSTRUMENTS")

        buttonAdd.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                count++;

            }
        })

        buttonCartPreview.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@DrumActivity, CartActivity::class.java)
                intent.putExtra("DRUM_QUANTITY",count)
                intent.putExtra("DRUM","drum")
                startActivity(intent)
            }

        })
    }

    private fun bindViews() {
        imageView=findViewById(R.id.imageView)
        textViewDescription=findViewById(R.id.textViewDescription)
        buttonAdd=findViewById(R.id.buttonAdd)
        buttonCartPreview=findViewById(R.id.buttonCartPreview)
    }
}