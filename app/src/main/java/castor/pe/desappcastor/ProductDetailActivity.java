package castor.pe.desappcastor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        String id = getIntent().getExtras().getString("id");

        Toast.makeText(this, "OHHHH!!: " + id, Toast.LENGTH_LONG).show();

    }
}
