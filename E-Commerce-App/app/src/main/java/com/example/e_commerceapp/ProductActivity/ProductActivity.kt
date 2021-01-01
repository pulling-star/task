package com.example.e_commerceapp.ProductActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.AccordionActivity.AccordionActivity
import com.example.e_commerceapp.CartActivity.CartActivity
import com.example.e_commerceapp.DrumActivity.DrumActivity
import com.example.e_commerceapp.FluteActivity.FluteActivity
import com.example.e_commerceapp.GuitarActivity.GuitarActivity
import com.example.e_commerceapp.Piano.PianoActivity
import com.example.e_commerceapp.R
import com.example.e_commerceapp.Saxophone.SaxophoneActivity
import com.example.e_commerceapp.Trumpet.TrumpetActivity
import com.example.e_commerceapp.Violin.ViolinActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductActivity : AppCompatActivity() {
    lateinit var recyclerViewList: RecyclerView
    lateinit var fabCartPreview:ExtendedFloatingActionButton
    var imageList= ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        addingImages()
        setUpRecyclerView()
        fabCartPreview.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val intent  = Intent(this@ProductActivity,CartActivity::class.java)
                startActivity(intent)
            }

        })
    }

    private fun bindViews() {
        recyclerViewList=findViewById(R.id.recyclerViewList)
        fabCartPreview=findViewById(R.id.fabCartPreview)
    }

    private fun addingImages() {
        imageList.add(
            Model(
                R.drawable.accordionimage1,
                "Accordion"
            )
        )
        imageList.add(
            Model(
                R.drawable.drumimage,
                "Drum"
            )
        )
        imageList.add(
            Model(
                R.drawable.fluteimage,
                "Flute"
            )
        )
        imageList.add(
            Model(
                R.drawable.guitarimage,
                "Guitar"
            )
        )
        imageList.add(
            Model(
                R.drawable.pianoimage,
                "Piano"
            )
        )
        imageList.add(
            Model(
                R.drawable.saxophoneimage,
                "Saxophone"
            )
        )
        imageList.add(
            Model(
                R.drawable.trumpetimage,
                "Trumpet"
            )
        )
        imageList.add(
            Model(
                R.drawable.violinimage,
                "Violin"
            )
        )

    }

    private fun setUpRecyclerView(){
        val itemClickListener = object:ItemClickListener{
            override fun onCLick(model: Model,position:Int) {
                if (position==0) {
                    val intent = Intent(this@ProductActivity, AccordionActivity::class.java)
                    startActivity(intent)
                }else if (position==1){
                    val intent = Intent(this@ProductActivity, DrumActivity::class.java)
                    startActivity(intent)
                }else if (position==2) {
                    val intent = Intent(this@ProductActivity, FluteActivity::class.java)
                    startActivity(intent)
                }else if (position==3) {
                    val intent = Intent(this@ProductActivity, GuitarActivity::class.java)
                    startActivity(intent)
                }else if (position==4) {
                    val intent = Intent(this@ProductActivity, PianoActivity::class.java)
                    startActivity(intent)
                }else if (position==5) {
                    val intent = Intent(this@ProductActivity, SaxophoneActivity::class.java)
                    startActivity(intent)
                }else if (position==6) {
                    val intent = Intent(this@ProductActivity, TrumpetActivity::class.java)
                    startActivity(intent)
                }else {
                    val intent = Intent(this@ProductActivity, ViolinActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        val Adapter = ListAdapter(imageList,itemClickListener)
        val gridLayoutManager = GridLayoutManager(this,2)
        recyclerViewList.adapter= Adapter
        recyclerViewList.layoutManager=gridLayoutManager
    }


}