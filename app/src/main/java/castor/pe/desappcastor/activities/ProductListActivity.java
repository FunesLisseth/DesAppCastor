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
import castor.pe.desappcastor.adapters.ProductAdapter;
import castor.pe.desappcastor.interfaces.ProductInterface;
import castor.pe.desappcastor.models.Product;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductListActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://192.168.1.202:8081/castor/api/";
    private static final String TAG = "ProductActivity";

    private RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    private List<Product> products = new ArrayList<Product>();

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
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

        ProductInterface service = retrofit.create(ProductInterface.class);
        Call<List<Product>> call = service.getProducyByCategory("2");

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, retrofit2.Response<List<Product>> response) {
                Log.d(TAG, "response.code = " + response.code());

                if (response.isSuccessful()){
                    Log.d(TAG, "response body = " + new Gson().toJson(response.body()));

                    products = response.body();
                    mAdapter = new ProductAdapter(products);

                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, "OnFailure:" + t.getMessage());
            }
        });





    }



    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
