package com.example.wiranata.impal_ab;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterMenu extends BaseAdapter {

    private Context context;
    private ArrayList<MenuModel> menuModelArrayList;

    public CustomAdapterMenu(Context context, ArrayList<MenuModel> menuModelArrayList) {
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
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rowmain,null,true);

            holder.tvnama = (TextView) convertView.findViewById(R.id.menu);
            holder.tvharga = (TextView) convertView.findViewById(R.id.harga);
            holder.etjumlah = (EditText) convertView.findViewById(R.id.count);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvnama.setText(menuModelArrayList.get(position).getNama());
        holder.tvharga.setText("Rp " + Integer.toString(menuModelArrayList.get(position).getHarga()));
        holder.etjumlah.setText(Integer.toString(menuModelArrayList.get(position).getJumlah()));

        holder.etjumlah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (holder.etjumlah.getText().length() == 0)
                    menuModelArrayList.get(position).setJumlah(0);
                else
                    menuModelArrayList.get(position).setJumlah(Integer.parseInt(holder.etjumlah.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return convertView;
    }

    private class ViewHolder {
        protected TextView tvnama, tvharga;
        protected EditText etjumlah;
    }
}
