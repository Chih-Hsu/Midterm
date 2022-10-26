package com.vichsu.midterm.publisherhome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vichsu.midterm.data.ArticleObject
import com.vichsu.midterm.data.Author
import java.util.*

class PublisherViewModel : ViewModel() {

    private var _article = MutableLiveData<List<ArticleObject>>()
    val article: LiveData<List<ArticleObject>> get() = _article

    private val articleData = Firebase.firestore.collection("articles")

    init {

        loadData()

    }

    fun loadData() {
        articleData.addSnapshotListener { value, error ->
            //初始化data
            val dataList = mutableListOf<ArticleObject>()
            value?.documents?.forEach { item ->

                val newData = item.data
                newData?.let {
                    val authorData = it.get("author") as Map<String, String>
                    val author = Author(
                        authorData["email"].toString(),
                        authorData["id"].toString(),
                        authorData["name"].toString()
                    )
                    val newArticle = ArticleObject(
                        author,
                        it["title"].toString(),
                        it["content"].toString(),
                        it["createdTime"] as Long,
                        it["id"].toString(),
                        it["category"].toString()
                    )
                    dataList.add(newArticle)
                    dataList.sortWith(Comparator { item1, item2 ->
                        item2.createdTime.compareTo(
                            item1.createdTime
                        )
                    })
                    _article.value = dataList
                }
            }

        }

    }


}