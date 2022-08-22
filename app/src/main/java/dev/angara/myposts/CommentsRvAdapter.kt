package dev.angara.myposts

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.angara.myposts.databinding.CommentListItemBinding


class CommentsRvAdapter(var commentlist: List<Comment>): RecyclerView.Adapter<CommentsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        var binding = CommentListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return commentlist.size
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        var currentComment = commentlist.get(position)
        with(holder.binding){
            tvPostId.text = currentComment.postId.toString()
            tvID.text = currentComment.id.toString()
            tvName.text = currentComment.name
            tvEmail.text = currentComment.email
            tvBody2.text = currentComment.body
        }
    }
}
class  CommentsViewHolder(var binding : CommentListItemBinding): RecyclerView.ViewHolder(binding.root)