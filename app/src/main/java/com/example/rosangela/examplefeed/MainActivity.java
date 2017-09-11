package com.example.rosangela.examplefeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.rosangela.examplefeed.models.Brewery;
import com.example.rosangela.examplefeed.models.BreweryCatalog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<String> data;

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
                    BreweryCatalog catalog = response.body();
                    for (Brewery b : catalog.data) {
                        Log.e("Cervejarias", String.format("%s: %s", b.name, b.formattedAddress));
                        Log.e("", "---------");
                    }
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

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONArray jArray = obj.getJSONArray("data");
            data = new ArrayList<String>();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObject = jArray.getJSONObject(i);
                Log.e("Details-->", jObject.getString("nome"));


                data.add(i, jObject.getString("nome"));


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new AdapterState(data);
        recyclerView.setAdapter(adapter);

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("states.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
