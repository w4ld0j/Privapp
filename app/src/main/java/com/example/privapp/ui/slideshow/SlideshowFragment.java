package com.example.privapp.ui.slideshow;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.privapp.R;

import java.security.Key;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private WebView miVisorWeb;
    private String url = "https://news.google.com/topics/CAAqJggKIiBDQkFTRWdvSUwyMHZNREl5ZUY4U0JtVnpMVFF4T1NnQVAB?oc=3&ceid=MX:es-419";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);

        //Inflate the layout for this fragment xdd
        View v = inflater.inflate(R.layout.fragment_slideshow, container, false);

        miVisorWeb = (WebView) v.findViewById(R.id.visorWeb);
        final WebSettings ajustesVisorWeb = miVisorWeb.getSettings();
        ajustesVisorWeb.setJavaScriptEnabled(true);
        /*miVisorWeb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                miVisorWeb.loadUrl(url);
                return true;
            }
        });*/
        miVisorWeb.setWebViewClient(new WebViewClient());
        miVisorWeb.loadUrl(url);

        //Boton volver (funciona para activities y fragmentos xd)
        miVisorWeb.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) v;
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack())
                            {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }
                return false;
            }
        });

        return v;
    }

}