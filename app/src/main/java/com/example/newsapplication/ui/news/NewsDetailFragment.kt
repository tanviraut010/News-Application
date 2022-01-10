package com.example.newsapplication.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentNewsDetailsBinding
import com.example.newsapplication.ui.utils.Utils
import com.example.newsapplication.ui.webview.WebViewFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news_details.*
import kotlinx.android.synthetic.main.item_news.ivThumbnail

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {

    private lateinit var fragmentNewsDetailsBinding: FragmentNewsDetailsBinding
    private var imageUrl: String? = ""
    private var title: String? = ""
    private var author: String? = ""
    private var date: String? = ""
    private var content: String? = ""
    private var webViewUrl: String? = ""

    companion object {
        fun newInstance(bundle: Bundle = Bundle()): NewsDetailFragment {
            val fragment = NewsDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        fragmentNewsDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_details, container, false)
        return fragmentNewsDetailsBinding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        imageUrl = arguments?.getString("imageUrl", "")
        title = arguments?.getString("title", "")
        author = arguments?.getString("author", "")
        date = arguments?.getString("date", "")
        content = arguments?.getString("content", "")
        webViewUrl = arguments?.getString("webViewUrl", "")

        Glide.with(ivThumbnail)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .fallback(R.drawable.ic_launcher_foreground)
            .into(fragmentNewsDetailsBinding.ivThumbnail)

        if (!title.isNullOrEmpty()) fragmentNewsDetailsBinding.tvTitle.text = title
        else fragmentNewsDetailsBinding.tvTitle.text = getString(R.string.no_title)

        if (!author.isNullOrEmpty()) fragmentNewsDetailsBinding.tvAuthor.text = author
        else fragmentNewsDetailsBinding.tvAuthor.text = getString(R.string.anonymous)

        fragmentNewsDetailsBinding.tvDate.text = date?.let { Utils.standardDateTime(it) }

        if (!content.isNullOrEmpty()) fragmentNewsDetailsBinding.tvContent.text = content
        else fragmentNewsDetailsBinding.tvContent.text = getString(R.string.no_content)

        val bundle = Bundle()
        bundle.putString("webUrl", webViewUrl)
        vLink.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, WebViewFragment.newInstance(bundle))
                .addToBackStack("").commit()
        }

    }
}