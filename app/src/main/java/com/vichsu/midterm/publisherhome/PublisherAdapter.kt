package com.vichsu.midterm.publisherhome

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vichsu.midterm.data.ArticleObject
import com.vichsu.midterm.databinding.ItemArticleBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class PublisherAdapter : ListAdapter<ArticleObject,PublisherAdapter.ArticleViewHolder>(object :DiffUtil.ItemCallback<ArticleObject>(){
    override fun areContentsTheSame(oldItem: ArticleObject, newItem: ArticleObject): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: ArticleObject, newItem: ArticleObject): Boolean {
        return oldItem.id == newItem.id
    }
}) {

    inner class ArticleViewHolder(val binding:ItemArticleBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item:ArticleObject){

            binding.apply {
                textTitle.text = item.title
                textAuthor.text = item.author.name
                textCategory.text = item.category
                val newColor = Color.argb(255, Random.nextInt(256),Random.nextInt(256),Random.nextInt(256))
                textCategory.setTextColor(newColor)
//                textCreated.text = item.createdTime.toString()
                textCreated.text = getDate(item.createdTime,"yyyy/MM/dd hh:mm")
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

    fun getDate(milliSeconds: Long, dateFormat: String?): String? {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat)

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }
}