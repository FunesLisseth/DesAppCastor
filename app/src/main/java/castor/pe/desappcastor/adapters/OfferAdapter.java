package castor.pe.desappcastor.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import castor.pe.desappcastor.R;
import castor.pe.desappcastor.activities.ProductDetailActivity;
import castor.pe.desappcastor.models.Offer;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {


    private final List<Offer> items;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView price;
        public ImageView image;

        public ViewHolder(View v) {
            super(v);

            title = (TextView) v.findViewById(R.id.offer_title);
            price = (TextView) v.findViewById(R.id.offer_price);
            image = (ImageView) v.findViewById(R.id.offer_image);
        }
    }


    public OfferAdapter(List<Offer> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_offer_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        Offer item = items.get(i);

        /*Glide.with(viewHolder.itemView.getContext())
                .load("")
                .centerCrop()
                .into(viewHolder.image);*/
        viewHolder.title.setText(item.getName());
        viewHolder.price.setText("S/. " + item.getLocalPrice());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("OfferAdapter", "" + items.get(i).getId());
                Intent intent = new Intent(view.getContext(),ProductDetailActivity.class);
                intent.putExtra("productId",String.valueOf(items.get(i).getId()));
                view.getContext().startActivity(intent);
            }
        });

    }
}
