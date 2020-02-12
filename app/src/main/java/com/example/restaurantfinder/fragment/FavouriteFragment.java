package com.example.restaurantfinder.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantfinder.R;
import com.example.restaurantfinder.adapter.ViewCartAdapter;
import com.example.restaurantfinder.api_classes.ApiInterface;
import com.example.restaurantfinder.api_classes.BaseURL;
import com.example.restaurantfinder.modal.GetCartModal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FavouriteFragment extends Fragment {

    RecyclerView recyclerView;
    public static String userID = null;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.cart_recyclerView);
        get_cart(userID);
        return view;
    }

    public void get_cart(String _catId){
        ApiInterface retrofitApiInterface = BaseURL.getRetrofit().create(ApiInterface.class);
        Call<List<GetCartModal>> modalClassCall = retrofitApiInterface.loadCartSession(_catId);

        modalClassCall.enqueue(new Callback<List<GetCartModal>>() {
            @Override
            public void onResponse(Call<List<GetCartModal>> call, Response<List<GetCartModal>>
                    response) {
                ViewCartAdapter recyclerviewAdapter = new ViewCartAdapter
                        (getActivity(),response.body());
                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(horizontalLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<GetCartModal>> call, Throwable t) {
            }
        });
    }
}