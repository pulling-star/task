package com.example.transparentstatusbar

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class OnBoardingTwoFragment:Fragment() {
    lateinit var buttonBack:Button
    lateinit var buttonDone:Button
    lateinit var onOptionClick: OnOptionClick

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onOptionClick = context as OnOptionClick
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.second_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
    }

    private fun bindView(view: View) {
        buttonBack = view.findViewById(R.id.buttonBack)
        buttonDone = view.findViewById(R.id.buttonDone)
        ClickListeners()
    }

    private fun ClickListeners() {
        buttonBack.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                onOptionClick.onOptionBack()
            }
        })
        buttonDone.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                onOptionClick.onOptionDone()
            }
        })
    }

    interface OnOptionClick{
        fun onOptionBack()
        fun onOptionDone()
    }
}