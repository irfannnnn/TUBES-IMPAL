package com.example.wiranata.impal_ab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterTransaksi extends BaseAdapter {
    private Context context;
    private ArrayList<TransaksiModel> transaksiModelArrayList;

    public CustomAdapterTransaksi(Context context, ArrayList<TransaksiModel> transaksiModelArrayList) {
        this.context = context;
        this.transaksiModelArrayList = transaksiModelArrayList;
    }

    @Override
    public int getCount() {
        return transaksiModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return transaksiModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return transaksiModelArrayList.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rowtransaksi,null,true);

            holder.tvtanggal = (TextView) convertView.findViewById(R.id.tanggal);
            holder.tvtotal = (TextView) convertView.findViewById(R.id.Totaltransaksi);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvtanggal.setText(transaksiModelArrayList.get(position).getTanggal());
        holder.tvtotal.setText("Rp " + Integer.toString(transaksiModelArrayList.get(position).getTotal()));

        return convertView;
    }

    private class ViewHolder {
        protected TextView tvtanggal, tvtotal;
    }
}
