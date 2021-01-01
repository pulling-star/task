package com.example.e_commerceapp.Saxophone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.e_commerceapp.CartActivity.CartActivity
import com.example.e_commerceapp.R

class SaxophoneActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var textViewDescription: TextView
    lateinit var buttonAdd: Button
    lateinit var buttonCartPreview: Button
    var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saxophone)
        bindViews()
        imageView.setImageResource(R.drawable.saxophoneimage)
        textViewDescription.setText("Havana M1105AY Alto Saxophone:-\n" +
                "\n" +
                "This is a High quality conductor Saxophone from the house of Havana. Includes case.\n" +
                "\n" +
                "Features:\n" +
                "\n" +
                "- alto saxophone\n" +
                "- Lever acute FA\n" +
                "- Complete with case")

        buttonAdd.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                count++;

            }
        })

        buttonCartPreview.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@SaxophoneActivity, CartActivity::class.java)
                intent.putExtra("SAXOPHONE_QUANTITY",count)
                intent.putExtra("SAXOPHONE","saxophone")
                startActivity(intent)
            }

        })
    }

    private fun bindViews(){
        imageView=findViewById(R.id.imageView)
        textViewDescription=findViewById(R.id.textViewDescription)
        buttonAdd=findViewById(R.id.buttonAdd)
        buttonCartPreview=findViewById(R.id.buttonCartPreview)
    }
}