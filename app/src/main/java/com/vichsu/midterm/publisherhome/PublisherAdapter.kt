package com.vichsu.midterm.publisherhome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vichsu.midterm.data.ArticleObject
import com.vichsu.midterm.databinding.ItemArticleBinding

class PublisherAdapter : ListAdapter<ArticleObject,PublisherAdapter.ArticleViewHolder>(object :DiffUtil.ItemCallback<ArticleObject>(){
    override fun areContentsTheSame(oldItem: ArticleObject, newItem: ArticleObject): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: ArticleObject, newItem: ArticleObject): Boolean {
        return oldItem.id == newItem.id
    }
}) {

    class ArticleViewHolder(val binding:ItemArticleBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item:ArticleObject){
            binding.apply {
                textTitle.text = item.title
                textAuthor.text = item.author.name
                textCategory.text = item.category
                textCreated.text = item.createdTime.toString()
                textContent.text = item.content
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = ItemArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}