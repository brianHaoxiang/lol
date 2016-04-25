package com.haoxiaz.lol.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoxiaz.lol.R;

/**
 * Created by haoxiaz on 4/24/16.
 */
public class ChampionDetailActivity extends AppCompatActivity {

    private static final String BASE = ChampionDetailActivity.class.getSimpleName();
    public static final String CHAMPION_ID = BASE + ".id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page_layout);

        ((TextView) findViewById(R.id.champion_name_detail)).setText(getIntent().getStringExtra("name"));
        ((TextView) findViewById(R.id.champion_blurb_detail)).setText(getIntent().getStringExtra("blurb"));

        ImageView icon = (ImageView) findViewById(R.id.champion_icon);
        Glide.with(this).load(getIntent().getStringExtra("image")).into(icon);
    }
}
