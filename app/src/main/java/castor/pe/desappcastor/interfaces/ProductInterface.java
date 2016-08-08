package castor.pe.desappcastor.interfaces;

import java.util.List;

import castor.pe.desappcastor.models.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Omar on 8/08/2016.
 */
public interface ProductInterface {

    @GET("product/{id}")
    Call<Product> getProductById(@Path("id") String id);

    @GET("product/category/{id}")
    Call<List<Product>> getProducyByCategory(@Path("id") String id);

    @POST("product/favorite/")
    void setProductFavorite(@Path("clientId") int clienteId, @Path("productId") String productId);

}
