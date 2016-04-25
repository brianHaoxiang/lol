package com.haoxiaz.lol.grid;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.haoxiaz.lol.R;
import com.haoxiaz.lol.api.LolDataService;
import com.haoxiaz.lol.api.RiotKey;
import com.haoxiaz.lol.model.Champion;
import com.haoxiaz.lol.model.ChampionMap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by haoxiaz on 4/22/16.
 */
public class HeroGridFragment extends Fragment {

    public interface OnHeroClickListener {
        void onHeroClick(Champion champion);
    }

    GridView heroGrid;
    HeroGridAdapter heroAdapter;
    List<Champion> champions;
    private OnHeroClickListener onHeroClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("haoxiang","HeroGrid create view");
        View layout = inflater.inflate(R.layout.hero_grid_layout, container, false);
        heroGrid = (GridView) layout.findViewById(R.id.hero_grid_view);
        heroGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onHeroClickListener.onHeroClick(heroAdapter.getItem(position));
            }
        });
        getData();
        return layout;
    }

    private void getData() {
        Call<ChampionMap> call = LolDataService.getInstance().getChampions("na", "blurb,image", RiotKey.RIOT_SERVICES_API_KEY);

        call.enqueue(new Callback<ChampionMap>() {
            @Override
            public void onResponse(Call<ChampionMap> call, Response<ChampionMap> response) {
                champions = new ArrayList<>();
                for (String key : response.body().data.keySet()) {
                    champions.add(response.body().data.get(key));
                }
                heroAdapter = new HeroGridAdapter(getActivity(), champions);
                heroGrid.setAdapter(heroAdapter);
                onHeroClickListener.onHeroClick(heroAdapter.getItem(0));
            }

            @Override
            public void onFailure(Call<ChampionMap> call, Throwable t) {

            }
        });
    }

    public void setOnHeroClickListener(OnHeroClickListener onHeroClickListener) {
        this.onHeroClickListener = onHeroClickListener;
    }

    public void refreshGrid(final String search) {
        if (search == null || search.isEmpty()) {
            heroAdapter.setChampionList(champions);
            return;
        }

        List<Champion> newList = new ArrayList<>();
        for (Champion champion : champions) {
            if (champion.getName().toLowerCase().contains(search)) {
                newList.add(champion);
            }
        }

        heroAdapter.setChampionList(newList);
    }
}
