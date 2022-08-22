package dev.angara.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.angara.myposts.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    var postId = 0
    lateinit var binding: ActivityCommentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtainPostId()
        fetchPostById()
        setUpToolbar()
        fetchCommentsById()
    }

    fun obtainPostId() {
        postId = intent.extras?.getInt("POST_ID") ?: 0
    }

    fun fetchPostById() {
        val apiClient = APIClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getPostById(postId)
        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    var post = response.body()
                    binding.tvPostTitle.text = post?.title
                    binding.tvPostBody.text = post?.body
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun setUpToolbar() {
        setSupportActionBar((binding.tbhome))
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun fetchCommentsById() {
        var client = APIClient.buildApiClient(ApiInterface::class.java)
        var request = client.getComments()
        request.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful){
                    var comments = response.body()?: emptyList()
                    displayComments(comments)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(baseContext,t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
    fun displayComments(commentList: List<Comment>){
        val commentsAdapter = CommentsRvAdapter(commentList)
        binding.rvComments.layoutManager = LinearLayoutManager(this)
        binding.rvComments.adapter= commentsAdapter
    }
}
