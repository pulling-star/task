package com.example.moviesapp.helpers

import android.view.View

typealias OnRecyclerViewItemClickListener<model> = (view : View, position : Int, model : model) -> Unit