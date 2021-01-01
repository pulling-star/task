package com.example.e_commerceapp.Violin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.e_commerceapp.CartActivity.CartActivity
import com.example.e_commerceapp.R

class ViolinActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var textViewDescription: TextView
    lateinit var buttonAdd: Button
    lateinit var buttonCartPreview: Button
    var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_violin)
        bindViews()
        imageView.setImageResource(R.drawable.violinimage)
        textViewDescription.setText("Product Description:-\n"+"Item Type: Cufflinks\n" +
                "Material: Metal\n" +
                "Metal: Brass\n" +
                "Dimension: Approx. 17mm\n" +
                "These red violin cufflinks are very unique design and will stand out in any crowd. They are made from solid metal with engraved detailing and an epoxy resin finish that's very strong and vibrant and will not fade. The cufflinks have a high quality Platinum plated polished finish.")

        buttonAdd.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                count++;

            }
        })

        buttonCartPreview.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@ViolinActivity, CartActivity::class.java)
                intent.putExtra("VIOLIN_QUANTITY",count)
                intent.putExtra("VIOLIN","violin")
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