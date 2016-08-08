package castor.pe.desappcastor.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import castor.pe.desappcastor.models.Offer;

/**
 * Created by user on 29/07/2016.
 */
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
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Offer item = items.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getImage())
                .centerCrop()
                .into(viewHolder.image);
        viewHolder.title.setText(item.getTitle());
        viewHolder.price.setText("$" + item.getTitle());

    }
}
