package com.vichsu.midterm.publisherhome

import android.util.Log
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
    val article : LiveData<List<ArticleObject>> get() = _article

    val db = Firebase.firestore
    val articleData = Firebase.firestore.collection("articles")

    init {

        articleData.addSnapshotListener { value, error ->
            val dataList = mutableListOf<ArticleObject>()
            value?.documents?.forEach {

                val newData = it.data

                val authorData = newData?.get("author") as Map<String,String>

                val author = Author(authorData.get("email")!!,authorData.get("id")!!,authorData.get("name")!!)
                val newArticle = ArticleObject(author
                    ,newData?.get("title") as String
                    ,newData?.get("content") as String
                    ,newData?.get("createdTime") as Long
                    ,newData?.get("id") as String
                ,newData?.get("category") as String
                )

                dataList.add(newArticle)
                _article.value = dataList



            }

        }

    }




}