package com.example.newsapplication.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.example.newsapplication.data.Article
import com.example.newsapplication.databinding.ItemNewsBinding
import com.example.newsapplication.ui.utils.Utils
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(private val onItemClick: OnItemClick) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var articleList = ArrayList<Article>()

    fun setArticleList(list: ArrayList<Article>) {
        this.articleList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        articleList[position].let { holder.bind(it) }
        holder.itemView.cslContainer.setOnClickListener {
            onItemClick.onItemClickListener(
                articleList[position].urlToImage,
                articleList[position].title,
                articleList[position].author,
                articleList[position].publishedAt,
                articleList[position].content,
                articleList[position].url
            )
        }
    }

    override fun getItemCount(): Int = articleList.size

    class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Article) {
            binding.article = data
            binding.executePendingBindings()

            binding.tvDate.text = data.publishedAt?.let { Utils.standardDateTime(it) }
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(ivThumbnail: ImageView, url: String) {
            Glide.with(ivThumbnail)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(ivThumbnail)
        }

    }
}