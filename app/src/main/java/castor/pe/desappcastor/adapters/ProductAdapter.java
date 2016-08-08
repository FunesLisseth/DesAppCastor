package castor.pe.desappcastor.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import castor.pe.desappcastor.R;
import castor.pe.desappcastor.activities.ProductDetailActivity;
import castor.pe.desappcastor.models.Product;

/**
 * Created by Omar on 8/08/2016.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private List<Product> products;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.MyViewHolder holder, final int position) {

        holder.titleTextView.setText(products.get(position).getName());
        holder.brandTextView.setText(products.get(position).getBrand());
        holder.priceTextView.setText(String.valueOf(products.get(position).getLocalPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("ProductAdapter", "" + products.get(position).getId());

                Intent intent = new Intent(view.getContext(),ProductDetailActivity.class);
                intent.putExtra("productId",String.valueOf(products.get(position).getId()));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView brandTextView;
        TextView priceTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            brandTextView = (TextView) itemView.findViewById(R.id.brandTextView);
            priceTextView = (TextView) itemView.findViewById(R.id.priceTextView);
        }
    }
}
