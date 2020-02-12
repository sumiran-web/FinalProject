package com.example.restaurantfinder.required_classes;

import com.example.restaurantfinder.activity.RestaurantDetailActivity;
import com.example.restaurantfinder.api_classes.ApiInterface;
import com.example.restaurantfinder.fragment.AccountFragment;
import com.example.restaurantfinder.fragment.FavouriteFragment;
import com.example.restaurantfinder.parser.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBll {
    boolean isSuccess = false;

    public boolean checkuser(String email, String password) {

        ApiInterface ApiInterface = Url.getInstance().create(ApiInterface.class);
        Call<SignUpResponse> usersCall = ApiInterface.checkUser(email, password);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {
                RestaurantDetailActivity.userID = loginResponse.body().get_id();
                FavouriteFragment.userID = loginResponse.body().get_id();
                AccountFragment.full_name = loginResponse.body().getFullname();
                System.out.println("UserID " + loginResponse.body().get_id());
                Url.token += loginResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
