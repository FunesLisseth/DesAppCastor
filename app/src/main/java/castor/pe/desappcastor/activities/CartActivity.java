package castor.pe.desappcastor.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import castor.pe.desappcastor.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ListView categoriaListView = (ListView) findViewById(R.id.productsListView);

        String[] items = { "Servicio Corte Recto Tablero x Pza", "Aster  Tornillo autoroscante ap/am/pzd  3.5x50mm", "Maderba aglo mel  Laberinto sf2c 2140x2440x18mm",
                            "Aglomerado crudo", "Aglomerado crudo",  "Masol Liston PinoRadiata Premium  1/C4  2x2\"x10.5'", "Nordex hdf pint  Blanco 1c 1520x2440x3.0mm"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);

        categoriaListView.setAdapter(adapter);


        categoriaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                view.setSelected(true);

                Intent intent = new Intent(CartActivity.this, ProductDetailActivity.class);
                intent.putExtra("id", String.valueOf(position));
                CartActivity.this.startActivity(intent);

            }
        });
    }
}
