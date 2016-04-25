package com.haoxiaz.lol.api;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by haoxiaz on 4/22/16.
 */
public final class LolHttpClient {

    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static String get() {
        Request request = new Request.Builder()
                .url("https://global.api.pvp.net/api/lol/static-data/na/v1.2/champion?champData=blurb,image&api_key=f22328ae-229c-43eb-bda0-83c091d7cafa")
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
