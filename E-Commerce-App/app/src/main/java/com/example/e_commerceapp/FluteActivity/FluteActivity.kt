package com.example.e_commerceapp.FluteActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.e_commerceapp.CartActivity.CartActivity
import com.example.e_commerceapp.R

class FluteActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var textViewDescription: TextView
    lateinit var buttonAdd: Button
    lateinit var buttonCartPreview: Button
    var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flute)
        bindViews()
        imageView.setImageResource(R.drawable.fluteimage)

        textViewDescription.setText("Specification:-\n" +
                "Amount : 1530.00\n" +
                "Binding : Electronics\n" +
                "Brand : Pearl\n" +
                "Color : MultiColored\n" +
                "CurrencyCode : USD\n" +
                "Label : Pearl Corporation\n" +
                "Languages : englishUnknown\n" +
                "Manufacturer : Pearl Corporation\n" +
                "Model : PFP105E\n" +
                "NumberOfItems : 1\n" +
                "PackageDimensions_Weight : 2.9\n" +
                "PackageDimensions : L:18.2 X W:6.5 X H:6.3")

        buttonAdd.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                count++;

            }
        })

        buttonCartPreview.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@FluteActivity, CartActivity::class.java)
                intent.putExtra("FLUTE_QUANTITY",count)
                intent.putExtra("FLUTE","flute")
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