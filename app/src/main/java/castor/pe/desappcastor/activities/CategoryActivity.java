package castor.pe.desappcastor.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import castor.pe.desappcastor.R;
import castor.pe.desappcastor.adapters.CategoryAdapter;
import castor.pe.desappcastor.interfaces.CategoryInterface;
import castor.pe.desappcastor.models.Category;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CategoryActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://192.168.43.94:8081/castor/api/";
    private static final String TAG = "CategoryActivity";

    private RecyclerView mRecyclerView;

    private CategoryAdapter mAdapter;
    List<Category> Categories;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mRecyclerView =(RecyclerView) findViewById(R.id.recyclerView);

        OkHttpClient okClient = new OkHttpClient.Builder().addInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder().addHeader("Accept","Application/JSON").build();
                        return chain.proceed(request);
                    }
                }).build();

        Retrofit retrofitRef = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoryInterface service = retrofitRef.create(CategoryInterface.class);

        Call<Category> call = service.getCategories();

        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, retrofit2.Response<Category> response) {
                Log.d(TAG,"onResponse: " + response.code());

                if (response.isSuccessful()){

                    Log.d(TAG,"onResponse: " + response.body());

                    Categories = new ArrayList<Category>();
                    Category result = response.body();

                    Log.d(TAG, "response = " + new Gson().toJson(result));

                    Categories = result.getItems();
                    mAdapter = new CategoryAdapter(Categories);

                    //Attach Adapter to RecyclerView
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mRecyclerView.setHasFixedSize(true);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });

        setupActionBar();

        //final ListView categoriaListView = (ListView) findViewById(R.id.categoriaListView);

        //RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://localhost:8081/castor/api/category/").build();
        /*RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://192.168.43.94:8081/castor/api").build();

        CategoryService service = restAdapter.create(CategoryService.class);

        //String dni=strReturnDNI;

        service.getCategory(new Callback<List<Category>>() {
            @Override
            public void success(List<Category> category, Response response) {
                AdapterCategory adapter = new AdapterCategory(CategoryActivity.this, category);
                categoriaListView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(CategoryActivity.this, "Error en la conexión del servicio", Toast.LENGTH_LONG).show();

            }
        });*/

        /*String[] items = { "Tableros", "Maderas", "Laminados", "Cantos", "Herrajes", "Acabados", "Pegamentos", "Equipamentos", "Herramientas", "Servicios" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);

        categoriaListView.setAdapter(adapter);*/


        /*categoriaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                view.setSelected(true);

                Intent intent = new Intent(CategoryActivity.this, ProductListActivity.class);
                CategoryActivity.this.startActivity(intent);

            }
        });*/

    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /*private class AdapterCategory extends ArrayAdapter<Category> {

        private List<Category> listCategory;

        public AdapterCategory(Context context, List<Category> categories){

            super(context, R.layout.adapter_listview_text, categories);
            listCategory = categories;
        }

        public View getView(int position, View contentView, ViewGroup parent){
            LayoutInflater inflater = LayoutInflater.from(getContext());

            View item = inflater.inflate(R.layout.adapter_listview_text, null);

            *//*String estado="";
            switch (listaSolicitudes.get(position).getEstado()){
                case "1":
                    estado="Pendiente";
                    break;
                case "2":
                    estado="Aprobado";
                    break;
                case "3":
                    estado="Rechazado";
                    break;
                case "4":
                    estado="Cancelado";
                    break;
                case "5":
                    estado="Reenviado";
                    break;
            }*//*

            TextView tv = (TextView) item.findViewById(R.id.tv);
            tv.setText("Nro solicitud: " + listCategory.get(position).getName());

            *//*TextView txtPlaca = (TextView) item.findViewById(R.id.txtPlaca);
            txtPlaca.setText("Placa: " + listaSolicitudes.get(position).getMatricula().toUpperCase());

            TextView txtEstado = (TextView) item.findViewById(R.id.txtEstado);
            txtEstado.setText(estado);*//*

            return item;

        }


    }*/
}
