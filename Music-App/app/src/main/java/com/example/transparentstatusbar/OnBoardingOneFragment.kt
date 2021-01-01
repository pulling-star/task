package com.example.transparentstatusbar

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.first_fragment.*

class OnBoardingOneFragment : Fragment(){
    lateinit var buttonNext :Button
    lateinit var onNextClick : OnNextCLick

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onNextClick = context as OnNextCLick
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
    }

    private fun bindView(view: View) {
        buttonNext = view.findViewById<Button>(R.id.buttonNext)
        clickListeners()
    }

    private fun clickListeners() {
        buttonNext.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                onNextClick.onClick()
            }

        })
    }
    interface OnNextCLick{
        fun onClick()
    }
}