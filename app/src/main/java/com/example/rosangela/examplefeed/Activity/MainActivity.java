package com.example.rosangela.examplefeed.Activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import com.example.rosangela.examplefeed.Adapter.AdapterBrewery;
import com.example.rosangela.examplefeed.Models.Brewery;
import com.example.rosangela.examplefeed.Models.BreweryCatalog;
import com.example.rosangela.examplefeed.R;
import com.example.rosangela.examplefeed.Services.BreweryService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    android.app.ActionBar actionbar;
    TextView textview;
    ActionBar.LayoutParams layoutparams;


    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("LISTA"));
        tabLayout.addTab(tabLayout.newTab().setText("MAPA"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(Color.DKGRAY, Color.WHITE);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_layout);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BreweryService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BreweryService service = retrofit.create(BreweryService.class);
        Call<BreweryCatalog> requestCatalog = service.listBrewery();

        requestCatalog.enqueue(new Callback<BreweryCatalog>() {
            @Override
            public void onResponse(Call<BreweryCatalog> call, Response<BreweryCatalog> response) {

                if (!response.isSuccessful()) {
                    Log.e("Erro2", "Erro:" + response.code());
                } else {
                    List<Brewery> catalog = response.body().getData();
                    recyclerView.setAdapter(new AdapterBrewery(catalog, getApplicationContext()));

                }
            }

            @Override
            public void onFailure(Call<BreweryCatalog> call, Throwable t) {

                Log.e("Erro", t.getMessage());
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_state);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


}