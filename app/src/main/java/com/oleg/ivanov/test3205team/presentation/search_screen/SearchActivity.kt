package com.oleg.ivanov.test3205team.presentation.search_screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.oleg.ivanov.test3205team.R
import com.oleg.ivanov.data.AppSettings
import com.oleg.ivanov.test3205team.app.MyApplication
import com.oleg.ivanov.test3205team.databinding.ActivitySearchBinding
import com.oleg.ivanov.test3205team.presentation.adapters.RepositoryListAdapter
import com.oleg.ivanov.test3205team.presentation.base.BaseActivity
import com.oleg.ivanov.test3205team.presentation.ext_ui.animateLeftRight
import com.oleg.ivanov.test3205team.presentation.ext_ui.animateSpeedWayFromDownToUp
import com.oleg.ivanov.test3205team.presentation.ext_ui.animateSpeedWayFromRightToLeft
import com.oleg.ivanov.test3205team.presentation.ext_ui.hideKeyboard
import com.oleg.ivanov.test3205team.presentation.list_repository_screen.ListDownloadRepositoryBindingActivity
import com.oleg.ivanov.test3205team.presentation.search_screen.view_model.SearchViewModelImpl
import com.oleg.ivanov.test3205team.presentation.search_screen.view_model.SearchViewState
import com.oleg.ivanov.test3205team.util.FileDownloader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 */
class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate) {

    private val fileDownloader by lazy { FileDownloader(this, {}, {}, null) }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val searchViewModel: SearchViewModelImpl by lazy {
        ViewModelProvider(this, viewModelFactory)[SearchViewModelImpl::class.java]
    }

    init {
        MyApplication.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        render()

        initRecyclerView()

        initClickListener()

        binding.buttonSearch.animateSpeedWayFromRightToLeft()
        binding.textUserNameInputLayout.animateSpeedWayFromDownToUp()

        showOkDialog(
            this,
            getString(R.string.info), getString(R.string.info_description)
        )

        binding.editTextUserName.setText("olegsoftjava")

    }

    private fun initRecyclerView() {
        binding.recyclerViewResult.layoutManager = LinearLayoutManager(this@SearchActivity)
    }

    private fun render() {
        lifecycleScope.launch {
            searchViewModel.viewState.collect {
                when (it) {
                    is SearchViewState.SearchData -> {
                        binding.editTextUserName.hideKeyboard()
                        updateRecyclerViewList(list = it.list, linkList = it.loadedLinks)
                    }

                    is SearchViewState.SearchError -> {
                        withContext(Dispatchers.Main) {
                            binding.textUserNameInputLayout.animateLeftRight()
                            Toast.makeText(
                                this@SearchActivity,
                                it.errorModel.description,
                                Toast.LENGTH_SHORT
                            ).show()
                            updateRecyclerViewList(list = null, linkList = null)
                        }
                    }

                    is SearchViewState.SearchStartLoad -> {
                        updateLoading(loadingActive = true)
                    }

                    is SearchViewState.SearchEndLoad -> {
                        updateLoading(loadingActive = false)
                    }

                    is SearchViewState.DownloadData -> {
                        Log.i("TEST_1_1", "===> it.link=${it.link}")
                        it.link?.let { link ->
                            (binding.recyclerViewResult.adapter as RepositoryListAdapter).addLinkToList(
                                link
                            )
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateRecyclerViewList(
        list: List<com.oleg.ivanov.domain.domain.data.GitHubRepoModelDomain>?,
        linkList: List<String>?
    ) {
        binding.recyclerViewResult.adapter =
            RepositoryListAdapter(dataSource = list ?: emptyList()) {
                fileDownloader.download(
                    uri = it.htmlUrl + AppSettings.PART_URL_FOR_DOWNLOAD,
                    fileName = it.nameRepository,
                    userName = it.owner?.userName?:"",
                    repositoryName = it.nameRepository
                )
            }.apply {
                setLinkList(linkList ?: emptyList())
            }
        binding.recyclerViewResult.adapter?.notifyDataSetChanged()
    }

    private fun updateLoading(loadingActive: Boolean) {
        binding.progressBar.isInvisible = !loadingActive
        binding.buttonSearch.isInvisible = loadingActive
    }

    private fun initClickListener() {
        binding.buttonSearch.setOnClickListener {
            if (binding.editTextUserName.text?.isEmpty() == true) {
                binding.textUserNameInputLayout.animateLeftRight()
            } else {
                searchViewModel.search(binding.editTextUserName.text.toString())
            }
        }

        binding.buttonOpenFileDownload.setOnClickListener {
            startActivity(
                Intent(
                    this@SearchActivity,
                    ListDownloadRepositoryBindingActivity::class.java
                )
            )
        }
    }

    private fun showOkDialog(context: Context, title: String, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)

        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}