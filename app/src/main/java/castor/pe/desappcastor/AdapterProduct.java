package castor.pe.desappcastor;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import castor.pe.desappcastor.Product;
import castor.pe.desappcastor.R;

public class AdapterProduct extends ArrayAdapter<Product> {

    private final Context context;
    private final ArrayList<Product> modelsArrayList;

    public AdapterProduct(Context context, ArrayList<Product> modelsArrayList) {

        super(context, R.layout.product_item, modelsArrayList);

        this.context = context;
        this.modelsArrayList = modelsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater

        View rowView = null;

        // if(!modelsArrayList.get(position).isGroupHeader()){
            rowView = inflater.inflate(R.layout.product_item, parent, false);

            // 3. Get icon,title & counter views from the rowView
            ImageView imgView = (ImageView) rowView.findViewById(R.id.item_icon);
            TextView titleView = (TextView) rowView.findViewById(R.id.item_title);
            TextView counterView = (TextView) rowView.findViewById(R.id.item_counter);

            // 4. Set the text for textView
            imgView.setImageResource(modelsArrayList.get(position).getIcon());
            titleView.setText(modelsArrayList.get(position).getTitle());
            counterView.setText(modelsArrayList.get(position).getCounter());
        /*}
        else{
            rowView = inflater.inflate(R.layout.product_header, parent, false);
            TextView titleView = (TextView) rowView.findViewById(R.id.header);
            //titleView.setText(modelsArrayList.get(position).getTitle());


        //} */

        // 5. retrn rowView
        return rowView;
    }
}