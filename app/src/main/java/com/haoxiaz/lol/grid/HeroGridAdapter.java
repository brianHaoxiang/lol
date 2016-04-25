package com.haoxiaz.lol.grid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.haoxiaz.lol.model.Champion;

import java.util.List;

/**
 * Created by haoxiaz on 4/24/16.
 */
public class HeroGridAdapter extends BaseAdapter {

    private List<Champion> champions;
    private Context context;

    public HeroGridAdapter(Context context, List<Champion> champions) {
        this.context = context;
        this.champions = champions;
    }

    public void setChampionList(List<Champion> list) {
        this.champions = list;
        notifyDataSetChanged();
    }

    public List<Champion> getChampionsList() {
        return champions;
    }

    @Override
    public int getCount() {
        return champions.size();
    }

    @Override
    public Champion getItem(int position) {
        return champions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return champions.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView item = new ImageView(context);
        item.setLayoutParams(new LinearLayout.LayoutParams(210,210));
        Glide.with(context).load("https://ddragon.leagueoflegends.com/cdn/6.8.1/img/champion/"+champions.get(position).getImage().getFull()).into(item);
        return item;
    }
}
