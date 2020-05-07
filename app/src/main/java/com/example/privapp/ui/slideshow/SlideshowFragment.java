package com.example.privapp.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.privapp.R;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private WebView webViewx;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        view = inflater.inflate(R.layout.fragment_slideshow, container, false);
        webViewx = (WebView) view.findViewById(R.id.webView);
        webViewx.setWebViewClient(new WebViewClient());
        webViewx.getSettings().setJavaScriptEnabled(true);
        webViewx.loadUrl("https://news.google.com/topics/CAAqJggKIiBDQkFTRWdvSUwyMHZNREl5ZUY4U0JtVnpMVFF4T1NnQVAB?oc=3&ceid=MX:es-419");
        return root;
    }
}