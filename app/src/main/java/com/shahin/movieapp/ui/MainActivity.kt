package com.shahin.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.shahin.movieapp.R
import com.shahin.movieapp.app.MainApplication
import com.shahin.movieapp.databinding.ActivityMainBinding
import com.shahin.movieapp.di.MainActivitySubComponent

class MainActivity : AppCompatActivity() {

    lateinit var mainActivitySubComponent: MainActivitySubComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        mainActivitySubComponent =
            (applicationContext as MainApplication).applicationGraph.mainComponent().create()

        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

    }

}