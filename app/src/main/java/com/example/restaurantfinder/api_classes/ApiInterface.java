package com.example.restaurantfinder.api_classes;

import com.example.restaurantfinder.modal.BookModal;
import com.example.restaurantfinder.modal.CartModal;
import com.example.restaurantfinder.modal.CollectionModal;
import com.example.restaurantfinder.modal.GetCartModal;
import com.example.restaurantfinder.modal.ProductModal;
import com.example.restaurantfinder.modal.UserModal;
import com.example.restaurantfinder.required_classes.SignUpResponse;
import com.example.restaurantfinder.required_classes.UserModalAno;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    // Node API for products
    @GET("product/getproduct")
    Call<List<ProductModal>> getProduct();

    // Node API for collections
    @GET("collections")
    Call<List<CollectionModal>> getCollection();

    //register user
    @POST("register/register_user")
    Call<Void> registerUser(@Body UserModal usersCUD);

    //for logging into the system
    @FormUrlEncoded
    @POST("register/login_user")
    Call<SignUpResponse> checkUser(@Field("email") String email, @Field("password") String password);

    @GET("register/me")
    Call<UserModalAno> getUserDetails(@Header("Authorization")String token);

    /*
    Add cart NodeAPI
     */
    @POST("cart/addcart")
    Call<Void> addCart(@Body CartModal postReviewModal);

    /*
    Get cart data by user id
     */
    @FormUrlEncoded
    @POST("products/getProductUnlimit")
    Call<List<ProductModal>> loadProduct(@Field("product") String product);

    // view cart by userid
    @FormUrlEncoded
    @POST("cart/getCartJoin")
    Call<List<GetCartModal>> loadCartSession(@Field("userid") String userid);

    // add book restaurant
    @POST("book/res")
    Call<Void> addBook(@Body BookModal bookModal);

}