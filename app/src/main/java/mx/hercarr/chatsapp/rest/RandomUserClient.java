package mx.hercarr.chatsapp.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomUserClient {

    private static final String BASE_URL = "https://randomuser.me/";
    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;
    private static UserService userService;

    private RandomUserClient() {

    }

    static {
        setupOkHttp();
        setupRetrofit();
        setupServices();
    }

    private static void setupOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl originalUrl = originalRequest.url();

                HttpUrl url = originalUrl.newBuilder()
                        .addQueryParameter(Parameters.NO_INFO_PARAM, "")
                        .addQueryParameter(Parameters.EXCLUDE_PARAM, Parameters.LOGIN_VALUE)
                        .build();

                Request.Builder builder = originalRequest.newBuilder()
                        .url(url);

                Request request = builder.build();
                return  chain.proceed(request);
            }
        });
        okHttpClient = builder.build();
    }

    private static void setupRetrofit() {
        Gson gson = new GsonBuilder()
                        .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                                               .baseUrl(BASE_URL)
                                               .client(okHttpClient)
                                               .addConverterFactory(GsonConverterFactory.create(gson));
        retrofit = builder.build();
    }

    private static void  setupServices() {
        userService = retrofit.create(UserService.class);
    }

    public static UserService getUserService() {
        return userService;
    }

    public static class Parameters {

        public static final String NO_INFO_PARAM = "noinfo";
        public static final String EXCLUDE_PARAM = "exc";
        public static final String LOGIN_VALUE = "login";

    }

}