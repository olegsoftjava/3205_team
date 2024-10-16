package com.oleg.ivanov.test3205team.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleg.ivanov.test3205team.R
import com.oleg.ivanov.test3205team.databinding.ItemDownloadRepositoryBinding

class DownloadRepositoryListAdapter(
    private val dataSource: List<com.oleg.ivanov.domain.domain.data.DownloadLinkAndFileDomain>,
) : RecyclerView.Adapter<DownloadRepositoryListAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            ItemDownloadRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: MessageViewHolder, i: Int) {
        viewHolder.binding.apply {
            dataSource[i].let { data ->
                userName.text = data.userName
                repositoryName.text = data.repositoryName
                if (data.comleted && !data.error) {
                    imageViewStatusLoading.setImageResource(R.drawable.baseline_check_24)
                }
                if (!data.comleted && !data.error) {
                    imageViewStatusLoading.setImageResource(R.drawable.baseline_arrow_downward_24)
                }
                if (data.error) {
                    imageViewStatusLoading.setImageResource(R.drawable.baseline_close_24)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    class MessageViewHolder(private val _binding: ItemDownloadRepositoryBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        val binding get() = _binding
    }
}
