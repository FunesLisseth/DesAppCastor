package castor.pe.desappcastor.activities;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import castor.pe.desappcastor.R;
import castor.pe.desappcastor.adapters.ProductAdapter;
import castor.pe.desappcastor.interfaces.ProductInterface;
import castor.pe.desappcastor.models.Product;
import castor.pe.desappcastor.utils.Constants;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailActivity extends AppCompatActivity {

    public static final String BASE_URL = Constants.ENDPOINT;
    private static final String TAG = "ProductActivity";

    TextView nameTextView;
    TextView brandTextView;
    ImageView productImageView;
    TextView dolarPriceTextView;
    TextView localPriceTextView;
    TextView descriptionTextView;
    TextView formatTextView;
    TextView thicknessTextView;
    Button addCartButton;
    Button showSimilarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        final String productId = getIntent().getExtras().getString("productId");

        final ImageView favoriteImageButton = (ImageView)findViewById(R.id.favoriteImageButton);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        brandTextView = (TextView) findViewById(R.id.brandTextView);
        productImageView = (ImageView) findViewById(R.id.productImageView);
        dolarPriceTextView = (TextView) findViewById(R.id.dolarPriceTextView);
        localPriceTextView = (TextView) findViewById(R.id.localPriceTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        formatTextView = (TextView) findViewById(R.id.formatTextView);
        thicknessTextView = (TextView) findViewById(R.id.thicknessTextView);
        addCartButton = (Button) findViewById(R.id.addCartButton);
        showSimilarButton = (Button) findViewById(R.id.showSimilarButton);

        //Toast.makeText(this, "" + productId, Toast.LENGTH_SHORT).show();

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

        final ProductInterface service = retrofit.create(ProductInterface.class);
        Call<Product> call = service.getProductById(productId);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, retrofit2.Response<Product> response) {
                Log.d(TAG, "response.code = " + response.code());

                if (response.isSuccessful()){
                    Log.d(TAG, "response body = " + new Gson().toJson(response.body()));

                    Product product = new Product();
                    product = response.body();

                    nameTextView.setText(product.getName());
                    brandTextView.setText(product.getBrand());
                    //productImageView;
                    dolarPriceTextView.setText("$ " + String.valueOf(product.getDolarPrice()));
                    localPriceTextView.setText("S/. " + String.valueOf(product.getLocalPrice()));
                    descriptionTextView.setText(product.getDescription());
                    formatTextView.setText(product.getFormat()+ " " + product.getFormatUnit());
                    thicknessTextView.setText(product.getThickness() + " " + product.getThicknessUnit());
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e(TAG, "OnFailure:" + t.getMessage());
            }
        });

        favoriteImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                service.setProductFavorite(1, productId);
                Drawable myDrawable = getResources().getDrawable(R.drawable.ic_favorite_black_24dp);
                favoriteImageButton.setImageDrawable(myDrawable);
            }
        });
    }
}
