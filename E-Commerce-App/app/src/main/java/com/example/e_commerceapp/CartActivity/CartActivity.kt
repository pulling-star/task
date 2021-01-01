package com.example.e_commerceapp.CartActivity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.e_commerceapp.PrefConstant
import com.example.e_commerceapp.R
import com.example.e_commerceapp.StoreSession
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {
    lateinit var textViewAccordionQuantity:TextView
    lateinit var textViewDrumQuantity:TextView
    lateinit var textViewFluteQuantity:TextView
    lateinit var textViewGuitarQuantity:TextView
    lateinit var textViewPianoQuantity:TextView
    lateinit var textViewSaxophoneQuantity:TextView
    lateinit var textViewTrumpetQuantity:TextView
    lateinit var textViewViolinQuantity:TextView
    var count1=0
    var count2=0;
    var count3=0;
    var count4=0;
    var count5=0;
    var count6=0;
    var count7=0;
    var count8=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        bindViews()
        setUpSharedPreferences()
        setCartValue()
    }

    private fun setUpSharedPreferences() {
        StoreSession.init(this)
    }

    private fun bindViews() {
        textViewAccordionQuantity=findViewById(R.id.textViewAccordionQuantity)
        textViewDrumQuantity=findViewById(R.id.textViewDrumQuantity)
        textViewFluteQuantity=findViewById(R.id.textViewFluteQuantity)
        textViewGuitarQuantity=findViewById(R.id.textViewGuitarQuantity)
        textViewPianoQuantity=findViewById(R.id.textViewPianoQuantity)
        textViewSaxophoneQuantity=findViewById(R.id.textViewSaxophoneQuantity)
        textViewTrumpetQuantity=findViewById(R.id.textViewTrumpetQuantity)
        textViewViolinQuantity=findViewById(R.id.textViewViolinQuantity)
    }

    private fun setCartValue() {
        val intent1 = getIntent()
        if(intent1.getStringExtra("ACCORDION") == "accordion"){
            count1 = intent1.getIntExtra("ACCORDION_QUANTITY", 0)
            textViewAccordionQuantity.setText(count1.toString())
            saveAccordionQuantity(count1)
        }
        if(count1==0){
            val count= StoreSession.readString(PrefConstant.ACCORDION_QUANTITY)!!
            textViewAccordionQuantity.setText(count)
        }

        val intent2 = getIntent()
        if (intent2.getStringExtra("DRUM")=="drum"){
            count2 = intent2.getIntExtra("DRUM_QUANTITY",0)
            textViewDrumQuantity.setText(count2.toString())
            saveDrumQuantity(count2)
        }
        if(count2==0){
            val count = StoreSession.readString(PrefConstant.DRUM_QUANTITY)!!
            textViewDrumQuantity.setText(count)
        }

        val intent3 = getIntent()
        if (intent3.getStringExtra("FLUTE")=="flute"){
            count3 = intent2.getIntExtra("FLUTE_QUANTITY",0)
            textViewFluteQuantity.setText(count3.toString())
            saveFluteQuantity(count3)
        }
        if(count3==0){
            val count = StoreSession.readString(PrefConstant.FLUTE_QUANTITY)!!
            textViewFluteQuantity.setText(count)
        }

        val intent4 = getIntent()
        if (intent4.getStringExtra("GUITAR")=="guitar"){
            count4 = intent2.getIntExtra("GUITAR_QUANTITY",0)
            textViewGuitarQuantity.setText(count4.toString())
            saveGuitarQuantity(count4)
        }
        if(count4==0){
            val count = StoreSession.readString(PrefConstant.GUITAR_QUANTITY)!!
            textViewGuitarQuantity.setText(count)
        }

        val intent5 = getIntent()
        if (intent5.getStringExtra("PIANO")=="piano"){
            count5 = intent5.getIntExtra("PIANO_QUANTITY",0)
            textViewPianoQuantity.setText(count5.toString())
            savePianoQuantity(count5)
        }
        if(count5==0){
            val count = StoreSession.readString(PrefConstant.PIANO_QUANTITY)!!
            textViewPianoQuantity.setText(count)
        }

        val intent6 = getIntent()
        if (intent6.getStringExtra("SAXOPHONE")=="saxophone"){
            count6 = intent6.getIntExtra("SAXOPHONE_QUANTITY",0)
            textViewSaxophoneQuantity.setText(count6.toString())
            saveSaxophoneQuantity(count6)
        }
        if(count6==0){
            val count = StoreSession.readString(PrefConstant.SAXOPHONE_QUANTITY)!!
            textViewSaxophoneQuantity.setText(count)
        }

        val intent7 = getIntent()
        if (intent7.getStringExtra("TRUMPET")=="trumpet"){
            count7 = intent7.getIntExtra("TRUMPET_QUANTITY",0)
            textViewTrumpetQuantity.setText(count7.toString())
            saveTrumpetQuantity(count7)
        }
        if(count7==0){
            val count = StoreSession.readString(PrefConstant.TRUMPET_QUANTITY)!!
            textViewTrumpetQuantity.setText(count)
        }

        val intent8 = getIntent()
        if (intent8.getStringExtra("VIOLIN")=="violin"){
            count8 = intent8.getIntExtra("VIOLIN_QUANTITY",0)
            textViewViolinQuantity.setText(count8.toString())
            saveViolinQuantity(count8)
        }
        if(count8==0){
            val count = StoreSession.readString(PrefConstant.VIOLIN_QUANTITY)!!
            textViewViolinQuantity.setText(count)
        }
    }


    private fun saveAccordionQuantity(count:Int){
        StoreSession.write(PrefConstant.ACCORDION_QUANTITY,count)
    }

    private fun saveDrumQuantity(count:Int){
        StoreSession.write(PrefConstant.DRUM_QUANTITY,count)
    }

    private fun saveFluteQuantity(count:Int){
        StoreSession.write(PrefConstant.FLUTE_QUANTITY,count)
    }

    private fun saveGuitarQuantity(count:Int){
        StoreSession.write(PrefConstant.GUITAR_QUANTITY,count)
    }

    private fun savePianoQuantity(count:Int){
        StoreSession.write(PrefConstant.PIANO_QUANTITY,count)
    }

    private fun saveSaxophoneQuantity(count:Int){
        StoreSession.write(PrefConstant.SAXOPHONE_QUANTITY,count)
    }

    private fun saveTrumpetQuantity(count:Int){
        StoreSession.write(PrefConstant.TRUMPET_QUANTITY,count)
    }

    private fun saveViolinQuantity(count:Int){
        StoreSession.write(PrefConstant.VIOLIN_QUANTITY,count)
    }
}