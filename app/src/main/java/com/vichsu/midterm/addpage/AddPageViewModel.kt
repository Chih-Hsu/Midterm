package com.vichsu.midterm.addpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vichsu.midterm.data.Author
import java.util.*

class AddPageViewModel : ViewModel() {


    private val db = Firebase.firestore

    private var _navigationUp = MutableLiveData<Boolean>()
    val navigationUp: LiveData<Boolean> get() = _navigationUp

    private var _userRegistered = MutableLiveData<Boolean>()
    val userRegistered: LiveData<Boolean> get() = _userRegistered

    private var title: String = ""
    private var category: String = ""
    private var content: String = ""

    fun setTitle(newTitle: String) {
        title = newTitle
    }

    fun setCategory(newCategory: String) {
        category = newCategory
    }

    fun setContent(newContent: String) {
        content = newContent
    }

    fun addData(author: Author) {
        checkUser(author)
        if (userRegistered.value == true) {
            val articles = FirebaseFirestore.getInstance()
                .collection("articles")
            val document = articles.document()

            val data = hashMapOf(
                "author" to hashMapOf(
                    "email" to author.email,
                    "id" to author.id,
                    "name" to author.name
                ),
                "title" to title,
                "content" to content,
                "createdTime" to Calendar.getInstance()
                    .timeInMillis,
                "id" to document.id,
                "category" to category
            )
            document.set(data)
            _navigationUp.value = true
        }
    }

    fun doneNavigationUp() {
        _navigationUp.value = false
    }

    private fun checkUser(author: Author) {
        db.collection("author").whereEqualTo("id", author.id).get().addOnSuccessListener {
            _userRegistered.value = !it.isEmpty

        }.addOnFailureListener {
            _userRegistered.value = false
        }
    }
}