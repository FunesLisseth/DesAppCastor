package castor.pe.desappcastor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import castor.pe.desappcastor.adapters.OfferAdapter;
import castor.pe.desappcastor.R;
import castor.pe.desappcastor.interfaces.OfferInterface;
import castor.pe.desappcastor.models.Offer;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String BASE_URL = "http://127.0.0.1:8081/castor/api/";
    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private List<Offer> offers = new ArrayList<Offer>();

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //new IntentIntegrator(MainActivity.this).initiateScan();
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setPrompt("Coloque el codigo QR del producto en el centro");
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMain);


        OfferInterface offerService = OfferInterface.retrofit.create(OfferInterface.class);
        final Call<List<Offer>> call = offerService.getOffers();

        call.enqueue(new Callback<List<Offer>>() {
            @Override
            public void onResponse(Call<List<Offer>> call, retrofit2.Response<List<Offer>> response) {
                Log.d(TAG, "response.code = " + response.code());
                if (response.isSuccessful()){
                    Log.d(TAG, "response body = " + new Gson().toJson(response.body()));
                    offers = response.body();
                    OfferAdapter offerAdapter = new OfferAdapter(offers);
                    recyclerView.setAdapter(offerAdapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                }
            }
            @Override
            public void onFailure(Call<List<Offer>> call, Throwable t) {
                Log.e(TAG, "OnFailure:" + t.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                intent.putExtra("productId", result.getContents());
                startActivity(intent);

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_category) {

            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
            MainActivity.this.startActivity(intent);

        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_orders) {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            MainActivity.this.startActivity(intent);

        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(intent);

        } else if (id == R.id.nav_recently_viewed) {

        } else if (id == R.id.nav_fav) {

        } else if (id == R.id.nav_search) {

        } else if (id == R.id.nav_group) {
            Intent intent = new Intent(MainActivity.this, GroupActivity.class);
            MainActivity.this.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
