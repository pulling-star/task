package com.example.transparentstatusbar

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    lateinit var recyclerViewList: RecyclerView
    var imageList= ArrayList<Model>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTransparentStatusBar()
        bindViews()
        addingImages()
        setUpRecyclerView()

    }

    private fun bindViews() {
        recyclerViewList=findViewById(R.id.recyclerViewList)
    }

    private fun setTransparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    private fun addingImages() {
        var model1 = Model("","")
        model1.setImage("https://cdn.wallpapersafari.com/74/99/M67dUT.jpg")
        model1.setText("Rock")
        imageList.add(model1)
        var model2 = Model("","")
        model2.setImage("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-5238-deer-art-vector.jpg")
        model2.setText("Jazz")
        imageList.add(model2)
        var model3 = Model("","")
        model3.setImage("https://i.pinimg.com/originals/28/f9/02/28f90226c40801a76fdff4daedbe7409.jpg")
        model3.setText("Pop")
        imageList.add(model3)
        var model4 = Model("","")
        model4.setImage("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-4556-wolf-minimalism-art-vector.jpg")
        model4.setText("Country")
        imageList.add(model4)
        var model5 = Model("","")
        model5.setImage("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-6346-wolf-starry-sky-tree-moon.jpg")
        model5.setText("Disco")
        imageList.add(model5)
        var model6 = Model("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-3917-city-vector-panorama.jpg","Party")
        imageList.add(model6)
        var model7 = Model("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-3304-lawn-forest-mountains.jpg","Folk")
        imageList.add(model7)
        var model8 = Model("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-2293-tree-planet-stars-galaxy-art.jpg","Hiphop")
        imageList.add(model8)
        var model9 = Model("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-8015-owl-bird-freddy-krueger-minimalism.jpg","Indie")
        imageList.add(model9)
        var model10 = Model("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-3375-minimalism-origami-japan-rising-sun-wave.jpg","Melody")
        imageList.add(model10)
        /*imageList.add(0,listOf("https://cdn.wallpapersafari.com/74/99/M67dUT.jpg","Rock").toString())
        imageList.add("https://cdn.wallpapersafari.com/74/99/M67dUT.jpg");
        imageList.add("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-5238-deer-art-vector.jpg");
        imageList.add("https://i.pinimg.com/originals/28/f9/02/28f90226c40801a76fdff4daedbe7409.jpg");
        imageList.add("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-4556-wolf-minimalism-art-vector.jpg");
        imageList.add("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-6346-wolf-starry-sky-tree-moon.jpg");
        imageList.add("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-3917-city-vector-panorama.jpg");
        imageList.add("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-3304-lawn-forest-mountains.jpg");
        imageList.add("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-2293-tree-planet-stars-galaxy-art.jpg");
        imageList.add("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-8015-owl-bird-freddy-krueger-minimalism.jpg");
        imageList.add("https://www.dreampirates.in/wallpaper/vector/img/25-12-2019-3375-minimalism-origami-japan-rising-sun-wave.jpg");
        */

    }

    private fun setUpRecyclerView() {
        val Adapter = ListAdapter(imageList)
        val linearLayoutManager =  LinearLayoutManager(this)
        linearLayoutManager.orientation=RecyclerView.VERTICAL
        recyclerViewList.adapter= Adapter
        recyclerViewList.layoutManager=linearLayoutManager
    }

}