package mx.hercarr.chatsapp.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {

    @GET("api/")
    Call<UserResponse> findUsers(@Query("results") Integer results);

}