package com.newsapp.android.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.newsapp.android.databinding.ActivityMainBinding
import com.newsapp.android.di.component.ActivityComponent
import com.newsapp.android.ui.base.BaseActivity
import com.newsapp.android.ui.base.BaseViewModel
import com.newsapp.android.ui.sources.NewsSourceActivity
import com.newsapp.android.ui.topHeadlines.TopHeadlinesActivity


class MainActivity : BaseActivity<ActivityMainBinding,
        BaseViewModel>(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setListeners()
    }


    override fun injectDependencies(activityComponent: ActivityComponent) {

    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setUpUi() {

    }

    override fun setUpObserver() {

    }

    private fun setListeners() {

        binding.tvTopHeadlines.setOnClickListener(this)
        binding.tvNewsSources.setOnClickListener(this)
        binding.tvCountries.setOnClickListener(this)
        binding.tvLanguages.setOnClickListener(this)
        binding.tvSearch.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {

        when (p0?.id) {

            binding.tvTopHeadlines.id -> startActivity(
                Intent(
                    this,
                    TopHeadlinesActivity::class.java
                )
            )
            binding.tvNewsSources.id -> startActivity(Intent(this, NewsSourceActivity::class.java))

        }

    }
}