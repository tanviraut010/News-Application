package com.example.newsapplication.ui.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.newsapplication.R
import com.example.newsapplication.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    lateinit var activityNewsBinding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNewsBinding = DataBindingUtil.setContentView(this, R.layout.activity_news)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, NewsFragment()).commit()
    }
}