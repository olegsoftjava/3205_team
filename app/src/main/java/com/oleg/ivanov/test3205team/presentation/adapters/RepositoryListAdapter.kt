package com.oleg.ivanov.test3205team.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleg.ivanov.test3205team.R
import com.oleg.ivanov.test3205team.app.AppSettings
import com.oleg.ivanov.test3205team.databinding.ItemRepositoryBinding
import com.oleg.ivanov.test3205team.presentation.ext_ui.animateUpDown
import com.oleg.ivanov.domain.domain.data.GitHubRepoModel

class RepositoryListAdapter(
    private val dataSource: List<com.oleg.ivanov.domain.domain.data.GitHubRepoModel>,
    private val click: (gitHubRepoModel: com.oleg.ivanov.domain.domain.data.GitHubRepoModel) -> Unit
) : RecyclerView.Adapter<RepositoryListAdapter.MessageViewHolder>() {

    private var linkList: MutableList<String>? = null

    fun setLinkList(linkList: List<String>?) {
        this.linkList = linkList?.toMutableList()
    }

    fun addLinkToList(link: String) {
        val validateLink = link.replace(AppSettings.PART_URL_FOR_DOWNLOAD, "")
        this.linkList?.add(validateLink)
        dataSource.forEachIndexed { index, element ->
            if (dataSource[index].htmlUrl == validateLink) {
                notifyItemChanged(index)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: MessageViewHolder, i: Int) {
        viewHolder.binding.apply {
            dataSource[i].let { data ->
                textName.text = data.nameRepository
                textDescription.text = data.description
                textLink.text = data.htmlUrl

                if (linkList?.firstOrNull { it == data.htmlUrl } == null) {
                    imageViewDownload.setImageResource(R.drawable.baseline_arrow_circle_down_24)
                    imageViewDownload.setOnClickListener {
                        viewHolder.binding.mainLayout.animateUpDown()
                        click(data)
                    }
                } else {
                    imageViewDownload.setImageResource(R.drawable.baseline_check_24)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    class MessageViewHolder(private val _binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        val binding get() = _binding
    }
}
