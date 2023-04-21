package com.newsapp.android.ui.languages

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle

import com.newsapp.android.data.model.languages.Language
import com.newsapp.android.databinding.ActivityLanguageBinding
import com.newsapp.android.di.component.ActivityComponent
import com.newsapp.android.ui.base.BaseActivity
import com.newsapp.android.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class LanguageActivity : BaseActivity<ActivityLanguageBinding, LanguageViewModel>() {

    @Inject
    lateinit var adapter: LanguageAdapter

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getViewBinding(): ActivityLanguageBinding {
        return ActivityLanguageBinding.inflate(layoutInflater)
    }

    override fun setUpUi() {
        binding.recyclerView.adapter = adapter
    }

    override fun setUpObserver() {
        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.languageList.collect {

                    when (it) {

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }

                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE

                            renderList(it.data)
                        }

                        is UiState.Error -> {
                            binding.recyclerView.visibility = View.GONE
                            Toast.makeText(this@LanguageActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(it: List<Language>) {

        adapter.addData(it)
        adapter.notifyDataSetChanged()

    }
}