package com.dy.easale.Adapters;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.dy.easale.Model.Artwork;
import com.dy.easale.R;

import java.util.ArrayList;

/**
 * Created by Nise on 6/11/2015.
 */
public class SaleAdapter extends ArrayAdapter<Artwork>
{
    private Context context;
    private ArrayList<Artwork> data;

    public SaleAdapter(Context context, ArrayList<Artwork> data)
    {
        super(context, R.layout.sale_row, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.sale_row, parent, false);
        ImageView imgIconView = (ImageView) row.findViewById(R.id.imgSaleIcon);
        TextView txtTitle = (TextView) row.findViewById(R.id.txtSaleTitle);
        TextView txtPrice = (TextView) row.findViewById(R.id.txtSalePrice);
        TextView txtCount = (TextView) row.findViewById(R.id.txtSalesCount);
        ImageButton btnIncrement = (ImageButton) row.findViewById(R.id.btnIncrement);
        ImageButton btnDecrement = (ImageButton) row.findViewById(R.id.btnDecrement);

        final Artwork sale = data.get(i);

        if (sale.GetSellCount() <= 0)
            btnDecrement.setEnabled(false);
        else
            btnDecrement.setEnabled(true);

        imgIconView.setImageURI(Uri.parse(data.get(i).getIcon()));
        txtTitle.setText(sale.getTitle());
        Log.d("mahiru", sale.getPrice() + "");
        txtPrice.setText(Integer.toString(sale.getPrice()));
        txtCount.setText(Integer.toString(sale.GetSellCount())); //TODO: Add Sale Number
        btnIncrement.setImageResource(R.drawable.ic_launcher);
        btnDecrement.setImageResource(R.drawable.ic_launcher);

        btnIncrement.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View v)
            {
                sale.SetSellCount(sale.GetSellCount() + 1);
                notifyDataSetChanged();
            }
        });

        btnDecrement.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sale.SetSellCount(sale.GetSellCount() - 1);
                notifyDataSetChanged();
            }
        });

        return row;
    }
}
