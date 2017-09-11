package com.example.rosangela.examplefeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.rosangela.examplefeed.models.Brewery;
import com.example.rosangela.examplefeed.models.BreweryCatalog;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    recyclerView.setAdapter(new AdapterState(catalog, getApplicationContext()));

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
