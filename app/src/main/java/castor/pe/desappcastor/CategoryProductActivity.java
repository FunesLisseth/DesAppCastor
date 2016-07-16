package castor.pe.desappcastor;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CategoryProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);

        setupActionBar();

        ListView categoriaListView = (ListView) findViewById(R.id.categoriaListView);

        String[] items = { "Tableros", "Maderas", "Laminados", "Cantos", "Herrajes", "Acabados", "Pegamentos", "Equipamentos", "Herramientas", "Servicios" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);

        categoriaListView.setAdapter(adapter);


        categoriaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                view.setSelected(true);

                Intent intent = new Intent(CategoryProductActivity.this, ProductListActivity.class);
                CategoryProductActivity.this.startActivity(intent);

            }
        });

    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
