package com.haoxiaz.lol.entry;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.haoxiaz.lol.R;
import com.haoxiaz.lol.detail.HeroDetailFragment;
import com.haoxiaz.lol.grid.HeroGridFragment;
import com.haoxiaz.lol.model.Champion;

public class HomeActivity extends AppCompatActivity implements HeroGridFragment.OnHeroClickListener {

    HeroGridFragment gridFragment;
    HeroDetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        gridFragment = new HeroGridFragment();
        gridFragment.setOnHeroClickListener(this);
        detailFragment = new HeroDetailFragment();

        getFragmentManager().beginTransaction().add(R.id.grid_placeholder, gridFragment).commit();
        getFragmentManager().beginTransaction().add(R.id.detail_placeholder, detailFragment).commit();
    }

    @Override
    public void onHeroClick(Champion champion) {
        detailFragment.setHeroInformation(champion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.v("haoxiang", "change: "+newText);
                gridFragment.refreshGrid(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
