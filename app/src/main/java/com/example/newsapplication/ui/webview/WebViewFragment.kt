package com.example.newsapplication.ui.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentWebviewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewFragment : Fragment() {

    lateinit var fragmentWebviewBinding: FragmentWebviewBinding
    private var webViewUrl: String? = ""

    companion object {
        fun newInstance(bundle: Bundle = Bundle()): WebViewFragment {
            val fragment = WebViewFragment()
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
        fragmentWebviewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false)
        init()
        return fragmentWebviewBinding.root
    }

    private fun init() {
        webViewUrl = arguments?.getString("webUrl", "")
        fragmentWebviewBinding.webview.settings.javaScriptEnabled = true
        fragmentWebviewBinding.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return false
            }
        }
        webViewUrl?.let { fragmentWebviewBinding.webview.loadUrl(it) }
    }
}