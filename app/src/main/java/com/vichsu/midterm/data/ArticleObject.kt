package com.vichsu.midterm.data


data class ArticleObject(
    val author:Author,
    val title:String,
    val content:String,
    val createdTime:Long,
    val id:String,
    val category:String

) {
}


data class Author(
    val email:String,
    val id:String,
    val name:String
)
