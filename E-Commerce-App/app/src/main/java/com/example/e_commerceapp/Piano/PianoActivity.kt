package com.example.e_commerceapp.Piano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.e_commerceapp.CartActivity.CartActivity
import com.example.e_commerceapp.R

class PianoActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var textViewDescription: TextView
    lateinit var buttonAdd: Button
    lateinit var buttonCartPreview: Button
    var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_piano)
        bindViews()
        imageView.setImageResource(R.drawable.pianoimage)
        textViewDescription.setText("Specification:-\n" +
                "Brand : Roland\n" +
                "Color : Bundle w/ Roland DP-10 Damper Pedal\n" +
                "IsAutographed : false\n" +
                "IsMemorabilia : false\n" +
                "Label : Roland\n" +
                "Manufacturer : Roland\n" +
                "Model : FP-30-BK-COMBO-DLX\n" +
                "PackageDimensions_Weight : 69.00027876076\n" +
                "PackageDimensions : L:57.99999994084 X W:17.99999998164 X H:17.49999998215\n" +
                "PartNumber : FP-30-BK-COMBO-DLX\n" +
                "ProductGroup : Musical Instruments\n" +
                "ProductTypeName : KEYBOARD_INSTRUMENTS\n" +
                "Publisher : Roland")

        buttonAdd.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                count++;

            }
        })

        buttonCartPreview.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@PianoActivity, CartActivity::class.java)
                intent.putExtra("PIANO_QUANTITY",count)
                intent.putExtra("PIANO","piano")
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