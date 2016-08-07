package castor.pe.desappcastor.adapters;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import castor.pe.desappcastor.R;
import castor.pe.desappcastor.models.Product;

public class ProductAdapter extends ArrayAdapter<Product> {

    private final Context context;
    private final ArrayList<Product> modelsArrayList;

    public ProductAdapter(Context context, ArrayList<Product> modelsArrayList) {

        super(context, R.layout.adapter_product_item, modelsArrayList);

        this.context = context;
        this.modelsArrayList = modelsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater

        View rowView = null;
        if(!modelsArrayList.get(position).isGroupHeader()){
            rowView = inflater.inflate(R.layout.adapter_product_item, parent, false);

            ImageView imgView = (ImageView) rowView.findViewById(R.id.productImage);
            TextView titleView = (TextView) rowView.findViewById(R.id.productTitle);
            TextView detailView = (TextView) rowView.findViewById(R.id.productDetail);

            imgView.setImageResource(modelsArrayList.get(position).getImage());
            titleView.setText(modelsArrayList.get(position).getTitle());
            titleView.setTag(modelsArrayList.get(position).getId());
            detailView.setText(modelsArrayList.get(position).getDetail());
        }
        else{
            rowView = inflater.inflate(R.layout.adapter_product_header, parent, false);
            TextView titleView = (TextView) rowView.findViewById(R.id.header);
            titleView.setText(modelsArrayList.get(position).getTitle());
        }

        // 5. retrn rowView
        return rowView;
    }

}