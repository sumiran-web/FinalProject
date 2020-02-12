package com.example.restaurantfinder.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.example.restaurantfinder.R;
import com.example.restaurantfinder.adapter.MyAdapter;
import com.example.restaurantfinder.adapter.RecyclerviewAdapter;
import com.example.restaurantfinder.adapter.RecyclerviewProductAdapter;
import com.example.restaurantfinder.modal.CollectionModal;
import com.example.restaurantfinder.modal.ProductModal;
import com.example.restaurantfinder.api_classes.ApiInterface;
import com.example.restaurantfinder.api_classes.BaseURL;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView recyclerViewCollection, recyclerViewProduct;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] slideImage= {R.drawable.aa,R.drawable.bb, R.drawable.cc,
            R.drawable.dd,R.drawable.ee,R.drawable.ff};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.colorWhite));// set status bac

        recyclerViewProduct = view.findViewById(R.id.recyclerView_product);
        recyclerViewProduct.setNestedScrollingEnabled(false);

        for(int i=0;i<slideImage.length;i++)
            XMENArray.add(slideImage[i]);

        mPager = view.findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(getActivity(),XMENArray));
        CircleIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == slideImage.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
        getProduct();
        return view;
    }

    // restaurant json
    public void getProduct(){
        ApiInterface retrofitApiInterface = BaseURL.getRetrofit().create(ApiInterface.class);
        Call<List<ProductModal>> productModalCall = retrofitApiInterface.getProduct();
        productModalCall.enqueue(new Callback<List<ProductModal>>() {
            @Override
            public void onResponse(Call<List<ProductModal>> call, Response<List<ProductModal>> response) {
                RecyclerviewProductAdapter recyclerviewAdapter = new RecyclerviewProductAdapter(getActivity(),response.body());
                RecyclerView.LayoutManager mlayoutManager = new GridLayoutManager(getContext(), 2);
                recyclerViewProduct.setLayoutManager(mlayoutManager);
                recyclerViewProduct.setHasFixedSize(true);
                recyclerViewProduct.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<ProductModal>> call, Throwable t) {
                
            }
        });
    }
}