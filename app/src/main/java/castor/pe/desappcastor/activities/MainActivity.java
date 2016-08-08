package castor.pe.desappcastor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import castor.pe.desappcastor.adapters.OfferAdapter;
import castor.pe.desappcastor.R;

import castor.pe.desappcastor.utils.Constants;

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


    private NavigationView navigationView;
    private FloatingActionButton fab;

    private TextView profileTextView;
    private TextView userNameTextView;

    // Storage Access Class
    SharedPreferences sharedPreferences;

    public static final String BASE_URL = "http://192.168.1.202:8081/castor/api/";
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

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREF_KEY, MODE_PRIVATE);
        String userType = sharedPreferences.getString(Constants.PREF_USER_TYPE, "");

        //profileTextView = (TextView)navigationView.getHeaderView(0).findViewById(R.id.profileTextView);
        //userNameTextView = (TextView)navigationView.getHeaderView(0).findViewById(R.id.userNameTextView);

        if( userType!=null&&!userType.equals("") ){
            if( userType.equals("1") ){//vendedor
                showOptionsSeller(navigationView);
                //profileTextView.setText("Seller");
            }else if( userType.equals("2") ){//cliente
                showOptionsClient(navigationView);
                //profileTextView.setText("Client");
            }

            String firstName = sharedPreferences.getString(Constants.PREF_USER_FIRSTNAME, "");
            String lastName = sharedPreferences.getString(Constants.PREF_USER_LASTNAME, "");

            //userNameTextView.setText(firstName+" "+lastName);

        }else{
            //profileTextView.setText("Public");
            //userNameTextView.setText("");
            showOptionsPublic(navigationView);
        }

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


            startActivity(new Intent(this, PreferenceActivity.class));

            //Intent intent = new Intent(MainActivity.this, PreferenceActivity.class);
            //MainActivity.this.startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else if (id == R.id.nav_categories) {
            startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
        } else if (id == R.id.nav_account) {
            startActivity(new Intent(getApplicationContext(), AccountActivity.class));
        } else if (id == R.id.nav_favorite) {
            //startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
        } else if (id == R.id.nav_order) {
            //startActivity(new Intent(getApplicationContext(), OrderActivity.class));
        } else if (id == R.id.nav_shopping_cart) {
            //startActivity(new Intent(getApplicationContext(), ShoppingActivity.class));
        } else if (id == R.id.nav_sign_in) {
            startActivity(new Intent(getApplicationContext(), SigninActivity.class));
        } else if (id == R.id.nav_sign_off) {
            sharedPreferences = this.getSharedPreferences(Constants.SHARED_PREF_KEY, MODE_PRIVATE);
            SharedPreferences.Editor e = sharedPreferences.edit();
            e.remove(Constants.PREF_USER_ID);
            e.remove(Constants.PREF_USER_TYPE);
            e.remove(Constants.PREF_USER_FIRSTNAME);
            e.remove(Constants.PREF_USER_LASTNAME);
            e.commit();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(getApplicationContext(), GroupActivity.class));
        }

        // Setear título actual
        setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void showOptionsClient(NavigationView navigationView){
        navigationView.getMenu().findItem(R.id.nav_account).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_favorite).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_order).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_sign_in).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_sign_off).setVisible(true);
    }

    private void showOptionsSeller(NavigationView navigationView){
        navigationView.getMenu().findItem(R.id.nav_account).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_favorite).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_order).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_sign_in).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_sign_off).setVisible(true);
    }

    private void showOptionsPublic(NavigationView navigationView){
        navigationView.getMenu().findItem(R.id.nav_account).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_favorite).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_order).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_sign_in).setVisible(true);
        navigationView.getMenu().findItem(R.id.nav_sign_off).setVisible(false);
    }
}
