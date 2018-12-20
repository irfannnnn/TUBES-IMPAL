package com.example.wiranata.impal_ab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterManMenu extends BaseAdapter {
    private Context context;
    private ArrayList<MenuModel> menuModelArrayList;

    public CustomAdapterManMenu(Context context, ArrayList<MenuModel> menuModelArrayList) {
        this.context = context;
        this.menuModelArrayList = menuModelArrayList;
    }

    @Override
    public int getCount() {
        return menuModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return menuModelArrayList.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rowmenu,null,true);

            holder.tvnama = (TextView) convertView.findViewById(R.id.menu);
            holder.tvharga = (TextView) convertView.findViewById(R.id.harga);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvnama.setText(menuModelArrayList.get(position).getNama());
        holder.tvharga.setText("Rp " + Integer.toString(menuModelArrayList.get(position).getHarga()));

        return convertView;
    }

    private class ViewHolder {
        protected TextView tvnama, tvharga;
    }
}
