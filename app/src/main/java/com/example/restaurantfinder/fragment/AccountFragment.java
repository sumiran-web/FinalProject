package com.example.restaurantfinder.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.restaurantfinder.R;
import com.example.restaurantfinder.api_classes.ApiInterface;
import com.example.restaurantfinder.api_classes.BaseURL;
import com.example.restaurantfinder.parser.Url;
import com.example.restaurantfinder.required_classes.UserModalAno;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    public static String full_name = null;
    TextView txtFirstName, txtSecondName, txtEmail, txtPhone, txtAddress;
    UserModalAno UserModalAno;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        txtFirstName = view.findViewById(R.id.name);
        txtEmail = view.findViewById(R.id.email);

        reqUser();
        
        return view;
    }

    private void reqUser(){
        ApiInterface interfaces = BaseURL.getRetrofit().create(ApiInterface.class);
        Call<UserModalAno> userCall = interfaces.getUserDetails(Url.token);
//        Toast.makeText(getActivity(), "" + Url.token, Toast.LENGTH_SHORT).show();
        userCall.enqueue(new Callback<UserModalAno>() {
            @Override
            public void onResponse(Call<UserModalAno> call, Response<UserModalAno> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                UserModalAno = response.body();
                txtFirstName.setText(response.body().getFname());
                txtEmail.setText(response.body().getEmail());
                txtAddress.setText(response.body().getAddress());
                txtPhone.setText(response.body().getPhone());
            }

            @Override
            public void onFailure(Call<UserModalAno> call, Throwable t) {
                Log.d("My message", "onFaliure" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
