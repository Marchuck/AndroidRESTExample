package pl.marchuck.androidrestexample;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Project "AndroidRESTExample"
 * <p>
 * Created by Lukasz Marczak
 * on 07.12.2016.
 */

public class RestClient {

    String endpoint = "192.168.0.185/myrestapi/";//localhost

    private final RestAPI restAPI;

    public RestAPI getRestApi() {
        return restAPI;
    }

    public RestClient() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restAPI = retrofit.create(RestAPI.class);
    }


}
