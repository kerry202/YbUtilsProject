package com.youyicheng.KaoLiao.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.OrangeApp;

import java.util.ArrayList;

public class MyBaseAdapter extends BaseAdapter {
    private ArrayList<String> list;

    public MyBaseAdapter(ArrayList<String> list) {
        this.list = list;
    }

    public ArrayList<String> getList() {
        return list;
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = convertView.inflate(OrangeApp.mContext,R.layout.evaluate_grid_item,null);
            holder.evaluate_tv = convertView.findViewById(R.id.textview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String str = (String) getItem(position);
        holder.evaluate_tv.setText(str);
        return convertView;
    }

    private final class ViewHolder {
        private TextView evaluate_tv;
    }
}
