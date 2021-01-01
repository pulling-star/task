package com.example.e_commerceapp.Trumpet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.e_commerceapp.CartActivity.CartActivity
import com.example.e_commerceapp.R

class TrumpetActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var textViewDescription: TextView
    lateinit var buttonAdd: Button
    lateinit var buttonCartPreview: Button
    var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trumpet)
        bindViews()
        imageView.setImageResource(R.drawable.trumpetimage)
        textViewDescription.setText("Product Description:-\n" +
                "- Bb Key\n" +
                "-Gold Plated Finish (Silver also available but price may vary)\n" +
                "- 3 Stainless Steel Pistons\n" +
                "- 2 Water Key\n" +
                "- 4 Slides Open\n" +
                "- Yellow Brass Bell\n" +
                "- Professional System\n" +
                "- Bell Size 130mm Approx.\n" +
                "- Bore Size 20mm Approx.\n" +
                "- With Case & Mouthpiece")

        buttonAdd.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                count++;

            }
        })

        buttonCartPreview.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@TrumpetActivity, CartActivity::class.java)
                intent.putExtra("TRUMPET_QUANTITY",count)
                intent.putExtra("TRUMPET","trumpet")
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