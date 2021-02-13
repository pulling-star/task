package com.example.newsapp.WebView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.example.newsapp.R

class WebViewFragment : Fragment() {
    lateinit var webView:WebView
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_web_view, container, false)
        webView = v.findViewById(R.id.webView)
        progressBar = v.findViewById(R.id.progressBar)
        val args = WebViewFragmentArgs.fromBundle(requireArguments())
        val url = args.url
        webView.loadUrl(url)
        progressBar.visibility = View.GONE
        webView.settings.domStorageEnabled = true
        webView.settings.loadsImagesAutomatically = true;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        };
        webView.webViewClient = WebViewClient()
        return v
    }



}