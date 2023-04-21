package com.newsapp.android.ui.sources

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.newsapp.android.data.model.sources.Source
import com.newsapp.android.databinding.ActivityNewsSourceBinding
import com.newsapp.android.di.component.ActivityComponent
import com.newsapp.android.ui.base.BaseActivity
import com.newsapp.android.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourceActivity : BaseActivity<ActivityNewsSourceBinding, NewsSourceViewModel>() {

    @Inject
    lateinit var adapter: NewsSourceAdapter

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getViewBinding(): ActivityNewsSourceBinding {
        return ActivityNewsSourceBinding.inflate(layoutInflater)
    }

    override fun setUpUi() {
        binding.recyclerView.adapter = adapter
    }

    override fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.sourceList.collect {

                    when (it) {

                        is UiState.Success -> {

                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE

                            renderList(it.data)
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE

                            Toast.makeText(this@NewsSourceActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                        //Ask$ else statement to isme bh nh h
                    }
                }
            }
        }

    }

    private fun renderList(sourceList: List<Source>) {

        adapter.addData(sourceList)
        adapter.notifyDataSetChanged()

    }

}