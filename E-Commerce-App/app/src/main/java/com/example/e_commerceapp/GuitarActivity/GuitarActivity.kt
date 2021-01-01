package com.example.e_commerceapp.GuitarActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.e_commerceapp.CartActivity.CartActivity
import com.example.e_commerceapp.R

class GuitarActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var textViewDescription: TextView
    lateinit var buttonAdd: Button
    lateinit var buttonCartPreview: Button
    var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guitar)
        bindViews()
        imageView.setImageResource(R.drawable.guitarimage)
        textViewDescription.setText("Specification:-\n" +
                "Amount : 219.00\n" +
                "Binding : Electronics\n" +
                "Brand : Jasmine\n" +
                "Color : Natural\n" +
                "CurrencyCode : USD\n" +
                "HandOrientation : Right Handed\n" +
                "IsAutographed : false\n" +
                "IsMemorabilia : false\n" +
                "Label : Jasmine by Takamine\n" +
                "Languages : englishUnknown\n" +
                "Manufacturer : Jasmine by Takamine\n" +
                "Model : S34C\n" +
                "NumberOfItems : 1\n" +
                "PackageDimensions_Weight : 5.65044777506")

        buttonAdd.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                count++;

            }
        })

        buttonCartPreview.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@GuitarActivity, CartActivity::class.java)
                intent.putExtra("GUITAR_QUANTITY",count)
                intent.putExtra("GUITAR","guitar")
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