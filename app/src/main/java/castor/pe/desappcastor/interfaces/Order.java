package castor.pe.desappcastor.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Omar on 8/08/2016.
 */
public interface Order {

    @GET("order/client/{id}")
    Call<Order> getOrderByClient(@Path("id") String id);

    @GET("order/{id}")
    Call<Order> getOrderById(@Path("id") String id);

    @POST("order")
    Call<Order> saveOrder(@Path("id") String id);

    //http://localhost:8081/castor/api/order/

}
