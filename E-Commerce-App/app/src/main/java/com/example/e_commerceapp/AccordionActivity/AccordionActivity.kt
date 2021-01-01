package com.example.e_commerceapp.AccordionActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.e_commerceapp.CartActivity.CartActivity
import com.example.e_commerceapp.R
import kotlinx.android.synthetic.main.activity_accordion.*

class AccordionActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var textViewDescription:TextView
    lateinit var buttonAdd:Button
    lateinit var buttonCartPreview:Button
    var count:Int = 0
    val accordion:String? = "Accordion"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accordion)
        bindViews()
        imageView.setImageResource(R.drawable.accordionimage1)
        textViewDescription.setText("The accordion is a reed instrument,pull the air by pulling the bellows to make sound,suitable for accordion lovers and students Multi-purpose,in addition to solo, but also can ensemble,you worth it.\n\n"+"Features:-\n" +
                "Brand : Mugig\n" +
                "Color : White\n" +
                "Model : MK1\n" +
                "PackageDimensions : L:10.00 X W:10.00 X H:7.00\n" +
                "ProductGroup : Musical Instruments\n" +
                "ProductTypeName : KEYBOARD_INSTRUMENTS\n")

        buttonAdd.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                count++;

            }
        })

        buttonCartPreview.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@AccordionActivity,CartActivity::class.java)
                intent.putExtra("ACCORDION_QUANTITY",count)
                intent.putExtra("ACCORDION","accordion")
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