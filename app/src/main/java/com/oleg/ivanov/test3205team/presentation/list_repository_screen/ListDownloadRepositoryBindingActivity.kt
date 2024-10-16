package com.oleg.ivanov.test3205team.presentation.list_repository_screen

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.oleg.ivanov.test3205team.app.MyApplication
import com.oleg.ivanov.test3205team.databinding.ActivityListDownloadRepositoryBinding
import com.oleg.ivanov.test3205team.domain.data.DownloadLinkAndFile
import com.oleg.ivanov.test3205team.presentation.adapters.DownloadRepositoryListAdapter
import com.oleg.ivanov.test3205team.presentation.base.BaseActivity
import com.oleg.ivanov.test3205team.presentation.list_repository_screen.view_model.ListLoadedRepositoryViewModelImpl
import com.oleg.ivanov.test3205team.presentation.list_repository_screen.view_model.ListLoadedRepositoryViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListDownloadRepositoryBindingActivity : BaseActivity<ActivityListDownloadRepositoryBinding>(
    ActivityListDownloadRepositoryBinding::inflate
) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val listLoadedRepositoryViewModel: ListLoadedRepositoryViewModelImpl by lazy {
        ViewModelProvider(this, viewModelFactory)[ListLoadedRepositoryViewModelImpl::class.java]
    }

    init {
        MyApplication.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()

        render()

        listLoadedRepositoryViewModel.load()
    }

    private fun initRecyclerView() {
        binding.recyclerViewResult.layoutManager =
            LinearLayoutManager(this@ListDownloadRepositoryBindingActivity)
    }

    private fun render() {
        lifecycleScope.launch {
            listLoadedRepositoryViewModel.viewState.collect {
                when (it) {
                    is ListLoadedRepositoryViewState.Data -> {
                        updateRecyclerViewList(it.list)
                    }
                }
            }
        }
    }

    private fun updateRecyclerViewList(list: List<DownloadLinkAndFile>?) {
        binding.recyclerViewResult.adapter =
            DownloadRepositoryListAdapter(dataSource = list ?: emptyList())
        binding.recyclerViewResult.adapter?.notifyDataSetChanged()
    }

}