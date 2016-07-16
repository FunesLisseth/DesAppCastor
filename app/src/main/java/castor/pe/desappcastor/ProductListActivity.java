package castor.pe.desappcastor;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;



public class ProductListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        setupActionBar();

        ListView categoriaListView = (ListView) findViewById(R.id.productsListView);

        String[] items = { "Aglomerado crudo", "Aglomerado crudo", "Aglomerado crudo", "Aglomerado crudo", "Aglomerado crudo",
                           "Aglomerado crudo", "Aglomerado crudo", "Aglomerado crudo", "Aglomerado crudo", "Aglomerado crudo",
                           "Aglomerado crudo", "Aglomerado crudo", "Aglomerado crudo" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);

        categoriaListView.setAdapter(adapter);


        categoriaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                view.setSelected(true);

                Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                ProductListActivity.this.startActivity(intent);

            }
        });

        //*/ 1. pass context and data to the custom adapter
        //AdapterProduct adapter = new AdapterProduct(this, generateData());

        // if extending Activity
        // 2. Get ListView from activity_main.xml
        //ListView listView = (ListView) findViewById(R.id.listView);

        // 3. setListAdapter
        //listView.setAdapter(adapter);
        //if extending Activity
        //setListAdapter(adapter);*/
    }

    /*private ArrayList<Product> generateData(){
        ArrayList<Product> models = new ArrayList<Product>();

        models.add(new Product("Group Title"));
        models.add(new Product(R.drawable.image_product_thumb,"Menu Item 1","1"));
        models.add(new Product(R.drawable.image_product_thumb,"Menu Item 2","2"));
        models.add(new Product(R.drawable.image_product_thumb,"Menu Item 3","12"));

        return models;
    }*/

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
