package dev.angara.myposts

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>

    @GET("/posts/{postsId}")
    fun getPostById(@Path("postsId")postId:Int): Call<Post>

    @GET("/comments")
    fun getComments(): Call<List<Comment>>

    @GET("/comments/{commentId}")
    fun getcommentsById(@Path("commentsId")commentId:Int): Call<Comment>
}