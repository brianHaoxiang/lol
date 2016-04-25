package com.haoxiaz.lol.api;

import com.haoxiaz.lol.model.ChampionMap;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by haoxiaz on 4/22/16.
 */
public interface LolDataServiceClient {


    @GET("/api/lol/static-data/{region}/v1.2/champion")
    Call<ChampionMap> getChampions(@Path("region") String region, @Query("champData") String champData, @Query("api_key") String apiKey);
}
