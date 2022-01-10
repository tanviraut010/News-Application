package com.example.newsapplication.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*


@AndroidEntryPoint
class NewsFragment : Fragment(), OnItemClick {

    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        fragmentNewsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return fragmentNewsBinding.root
    }

    override fun onStart() {
        super.onStart()
        makeApiCall()
        fragmentNewsBinding.executePendingBindings()
        newsAdapter = NewsAdapter(this)
        rvNewsList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvNewsList.adapter = newsAdapter
    }

    private fun makeApiCall(): NewsViewModel {
        val viewModel = ViewModelProvider(
            this
        ).get(NewsViewModel::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner) {
            progressbar.visibility = View.GONE
            if (it != null) {
                //update the adapter
                newsAdapter.setArticleList(it)
                newsAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(requireContext(), "Error in fetching data", Toast.LENGTH_LONG)
                    .show()
            }
        }
        viewModel.loadListOfData()

        return viewModel
    }

    fun getAdapter(): NewsAdapter {
        return newsAdapter
    }

    override fun onItemClickListener(
        imageUrl: String?,
        title: String?,
        author: String?,
        date: String?,
        content: String?,
        webViewUrl: String?
    ) {
        val bundle = Bundle()
        bundle.putString("imageUrl", imageUrl)
        bundle.putString("title", title)
        bundle.putString("author", author)
        bundle.putString("date", date)
        bundle.putString("content", content)
        bundle.putString("webViewUrl", webViewUrl)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, NewsDetailFragment.newInstance(bundle))
            .addToBackStack("").commit()
    }
}