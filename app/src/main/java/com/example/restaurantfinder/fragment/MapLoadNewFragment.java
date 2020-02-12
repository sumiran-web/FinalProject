package com.example.restaurantfinder.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.restaurantfinder.R;

public class MapLoadNewFragment extends Fragment {

    private WebView mWebView;
    // static map
    String loadMap = "https://goo.gl/maps/E8bwi8p975E6b1aN8";


    public MapLoadNewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map_load_new, container, false);
        mWebView =  view.findViewById(R.id.mapView);
        mWebView.loadUrl(loadMap);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());
        return view;
    }

}
