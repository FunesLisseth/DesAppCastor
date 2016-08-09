package castor.pe.desappcastor.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import castor.pe.desappcastor.R;
import castor.pe.desappcastor.adapters.CategoryAdapter;
import castor.pe.desappcastor.interfaces.CategoryInterface;
import castor.pe.desappcastor.models.Category;


import castor.pe.desappcastor.utils.Constants;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CategoryActivity extends AppCompatActivity {

    public static final String BASE_URL = Constants.ENDPOINT;

    private static final String TAG = "CategoryActivity";

    private RecyclerView recyclerView;
    private CategoryAdapter mAdapter;
    private List<Category> categories = new ArrayList<Category>();

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setupActionBar();

        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);

        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Interceptor.Chain chain) throws IOException {
                                Request request = chain.request().newBuilder()
                                        .addHeader("Accept", "Application/JSON").build();
                                return chain.proceed(request);
                            }
                        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoryInterface service = retrofit.create(CategoryInterface.class);
        Call<List<Category>> call = service.getCategories();

        call.enqueue(new Callback<List<Category>>() {
             @Override
             public void onResponse(Call<List<Category>> call, retrofit2.Response<List<Category>> response) {
                 Log.d(TAG, "response.code = " + response.code());

                 if (response.isSuccessful()){
                     Log.d(TAG, "response body = " + new Gson().toJson(response.body()));

                     categories = response.body();
                     mAdapter = new CategoryAdapter(categories);

                     mLayoutManager = new LinearLayoutManager(getApplicationContext());
                     recyclerView.setLayoutManager(mLayoutManager);
                     recyclerView.setItemAnimator(new DefaultItemAnimator());
                     recyclerView.setAdapter(mAdapter);
                 }
             }

             @Override
             public void onFailure(Call<List<Category>> call, Throwable t) {
                 Log.e(TAG, "OnFailure:" + t.getMessage());
             }
         });
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
