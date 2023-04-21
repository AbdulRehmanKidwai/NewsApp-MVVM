package com.newsapp.android.ui.search

import android.view.View
import android.widget.SearchView.GONE
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.newsapp.android.databinding.ActivitySearchBinding
import com.newsapp.android.di.component.ActivityComponent
import com.newsapp.android.ui.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>() {

    @Inject
    lateinit var adapter: SearchPagingAdapter

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getViewBinding(): ActivitySearchBinding {
        return ActivitySearchBinding.inflate(layoutInflater)
    }


    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    override fun setUpUi() {
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch() {
            binding.searchView.getQueryTextChangeStateFlow()
                .debounce(200)
                .filter {

                    val validQuery = it.isNotEmpty()

                    if (!validQuery) {
                        binding.progressBar.visibility = View.GONE
                        adapter.submitData(lifecycle, PagingData.empty())
                        binding.recyclerView.visibility = View.VISIBLE
                    } else {
                        binding.progressBar.visibility = View.VISIBLE

                    }

                    return@filter validQuery

                }
                .flowOn(Dispatchers.Main)
                .distinctUntilChanged()
                .flatMapLatest {
                    return@flatMapLatest viewModel.fetchResult(it)
                        .catch { e ->

//                            emitAll(flowOf(emptyList()))//Ask$ why emitAll not working?
                            adapter.submitData(lifecycle, PagingData.empty())
                            //Should I pass empty list here?
                        }
                }
                .flowOn(Dispatchers.IO)
                .collect {


                    binding.progressBar.visibility = GONE
                    binding.recyclerView.visibility = View.VISIBLE
//                    renderList(it)
                    adapter.submitData(lifecycle, it)

                }
        }
    }

    override fun setUpObserver() {

    }


    private fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

        val query = MutableStateFlow("")

        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                query.value = newText
                return true
            }
        })

        return query

    }
}