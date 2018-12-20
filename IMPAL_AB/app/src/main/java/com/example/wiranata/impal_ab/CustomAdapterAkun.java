package com.example.wiranata.impal_ab;

import android.content.Context;
import android.media.Image;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterAkun extends BaseAdapter {

    private Context context;
    private ArrayList<AkunModel> akunModelArrayList;

    public CustomAdapterAkun(Context context, ArrayList<AkunModel> akunModelArrayList) {
        this.context = context;
        this.akunModelArrayList = akunModelArrayList;
    }

    @Override
    public int getCount() {
        return akunModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return akunModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return akunModelArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rowaccount,null,true);

//            holder.thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);
            holder.tvuname = (TextView) convertView.findViewById(R.id.employee);

            convertView.setTag(holder);
        }else {
            holder = (CustomAdapterAkun.ViewHolder)convertView.getTag();
        }

        holder.tvuname.setText(akunModelArrayList.get(position).getUsername());

        return convertView;
    }

    private class ViewHolder {
//        protected Image thumbnail;
        protected TextView tvuname;
    }
}
