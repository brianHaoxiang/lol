package com.haoxiaz.lol.api;


import com.google.gson.Gson;
import com.haoxiaz.lol.model.Champion;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Created by haoxiaz on 4/22/16.
 */
public class LolDataService {

    private static LolDataServiceClient services;

    public synchronized static LolDataServiceClient getInstance() {
        if (null == services) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://global.api.pvp.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            services = retrofit.create(LolDataServiceClient.class);
        }

        return services;
    }

}
