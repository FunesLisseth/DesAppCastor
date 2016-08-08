package castor.pe.desappcastor.interfaces;

import java.util.List;

import castor.pe.desappcastor.models.Offer;
import castor.pe.desappcastor.models.Product;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Omar on 8/08/2016.
 */
public interface OfferInterface {

    @GET("product/offer/{id}")
    Call<List<Offer>> getOffers();

    static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8081/castor/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
