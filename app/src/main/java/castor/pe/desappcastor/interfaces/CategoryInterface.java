package castor.pe.desappcastor.interfaces;


import castor.pe.desappcastor.models.Category;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Omar on 6/08/2016.
 */
public interface CategoryInterface {

    @GET("category/")
    Call<Category> getCategories();
}
