package castor.pe.desappcastor.interfaces;

import java.util.List;

import castor.pe.desappcastor.models.Category;
import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by Omar on 6/08/2016.
 */
public interface CategoryInterface {

    @GET("category")
    Call<List<Category>> getCategories();

}
