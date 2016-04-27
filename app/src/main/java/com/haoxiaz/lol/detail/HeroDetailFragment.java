package com.haoxiaz.lol.detail;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haoxiaz.lol.R;
import com.haoxiaz.lol.model.Champion;

/**
 * Created by haoxiaz on 4/22/16.
 */
public class HeroDetailFragment extends Fragment {

    LinearLayout detailView;
    Champion champion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        detailView = (LinearLayout) inflater.inflate(R.layout.hero_detail_layout, container, false);
        detailView.findViewById(R.id.detail_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent startDetail = new Intent(getActivity(), ChampionDetailActivity.class);
                startDetail.putExtra(ChampionDetailActivity.CHAMPION_ID, champion.getId());
                startDetail.putExtra("name",champion.getName());
                startDetail.putExtra("blurb",champion.getBlurb());
                startDetail.putExtra("image","https://ddragon.leagueoflegends.com/cdn/6.8.1/img/champion/"+champion.getImage().getFull());
                startActivity(startDetail);
            }
        });
        return detailView;
    }

    public void setHeroInformation(Champion champion) {
        if (champion == null) {
            detailView.setVisibility(View.GONE);
            return;
        }
        detailView.setVisibility(View.VISIBLE);
        this.champion = champion;
        TextView name = (TextView) detailView.findViewById(R.id.champion_name);
        name.setText(champion.getName());
        TextView blurb = (TextView) detailView.findViewById(R.id.champion_blurb);
        blurb.setText(champion.getBlurb());
    }
}
