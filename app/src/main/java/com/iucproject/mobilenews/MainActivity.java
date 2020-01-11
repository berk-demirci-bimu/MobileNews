package com.iucproject.mobilenews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iucproject.mobilenews.adaptor.Adapter;
import com.iucproject.mobilenews.adaptor.DatasAdapter;
import com.iucproject.mobilenews.api.ApiClient;
import com.iucproject.mobilenews.articles.Article;
import com.iucproject.mobilenews.articles.NewsAPI;
import com.iucproject.mobilenews.interfaces.GETInterface;
import com.iucproject.mobilenews.roomDB.Datas;
import com.iucproject.mobilenews.roomDB.DatasViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private DatasViewModel dvm;
    final String API_KEY = "3af163598ec84d738a401f6f82ddafbc";
    RecyclerView recyclerView;
    Adapter adapter;
    List<Article> articles = new ArrayList<>();
    private static final String TAG = "MainActivity";
    private static String genre = "";
    private static String query = "";
    InputMethodManager inputManager;
    Button b;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Eğer herhangi bir metne veya görüntüye tıklanırsa yeni bir akitiveye gitmeli. Geri tuşu backstack olmalı
        getSupportActionBar().hide();


        t = findViewById(R.id.search_field);
        b = findViewById(R.id.search_button);
        b.setOnClickListener(this);
        //Buttons
        inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        Button b1 = findViewById(R.id.economy);
        Button b2 = findViewById(R.id.science);
        Button b3 = findViewById(R.id.tech);
        Button b4 = findViewById(R.id.life);
        Button b5 = findViewById(R.id.health);
        Button b6 = findViewById(R.id.sports);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        recyclerView = findViewById(R.id.recycler);
        String country = getCountry();
        getJSON(country,API_KEY);
        //if(recyclerView != null)
            //Toast.makeText(this,"AAAA  " + recyclerView.toString(),Toast.LENGTH_LONG).show();
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);


    }




    public void getJSON(String country,String api_key) {

        GETInterface get = ApiClient.getInstance().create(GETInterface.class);
        final Call<NewsAPI> newsAPICall = get.news("tr",genre,query,api_key);
     //   final Call<NewsAPI> newsAPICall = get.news("tr",API_KEY);

        newsAPICall.enqueue(new Callback<NewsAPI>() {
            @Override
            public void onResponse(Call<NewsAPI> call, Response<NewsAPI> response) {

                if(!response.body().getArticles().isEmpty()) {
                if (response.body().getStatus().equals("ok")) {
                    //Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new com.iucproject.mobilenews.adaptor.Adapter(articles, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }   }
                else {
                    Toast.makeText(MainActivity.this, "Makale bulunamadı.!!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<NewsAPI> call, Throwable t) {
                //Bağlantı olmadığı için ROM'daki veri çekildi.
                Toast.makeText(MainActivity.this, "BAĞLANTI HATASI!!!", Toast.LENGTH_LONG).show();
                final DatasAdapter datasAdapter = new DatasAdapter();
                // recyclerView.setAdapter(datasAdapter);
                dvm = ViewModelProviders.of(MainActivity.this).get(DatasViewModel.class);
                dvm.getAllDatas().observe(MainActivity.this, new Observer<List<Datas>>() {
                    @Override
                    public void onChanged(List<Datas> datas) {
                        datasAdapter.setDatas(datas);
                    }
                });

                recyclerView.setAdapter(datasAdapter);
                Log.e(TAG,"Connection Error!!! \n\n\n\n\n");
               // Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
            }
        });
    }
    public String getCountry() {
        String country;
        //Locale locale = Locale.getDefault();
        //country = locale.getCountry();
        country = "tr";
    return country.toLowerCase();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.search_button:
                query = t.getText().toString();
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                Toast.makeText(MainActivity.this,"Sorgunuz= " + query,Toast.LENGTH_SHORT).show();
                getJSON("tr",API_KEY);
                break;
            case R.id.economy:
                genre = "business";
                Toast.makeText(MainActivity.this,"Ekonomi Seçildi",Toast.LENGTH_SHORT).show();
                getJSON("tr",API_KEY);
                break;
            case R.id.science:
                genre = "science";
                Toast.makeText(MainActivity.this,"Bilim Seçildi",Toast.LENGTH_SHORT).show();
                getJSON("tr",API_KEY);
                break;
            case R.id.sports:
                genre = "sports";
                Toast.makeText(MainActivity.this,"Spor Seçildi",Toast.LENGTH_SHORT).show();
                getJSON("tr",API_KEY);
                break;
            case R.id.life:
                genre = "entertainment";
                Toast.makeText(MainActivity.this,"Hayat Seçildi",Toast.LENGTH_SHORT).show();
                getJSON("tr",API_KEY);
                break;
            case R.id.tech:
                genre = "technology";
                Toast.makeText(MainActivity.this,"Teknoloji Seçildi",Toast.LENGTH_SHORT).show();
                getJSON("tr",API_KEY);
                break;
            case R.id.health:
                genre = "health";
                Toast.makeText(MainActivity.this,"Sağlık Seçildi",Toast.LENGTH_SHORT).show();
                getJSON("tr",API_KEY);
                break;
        }
    }
}

