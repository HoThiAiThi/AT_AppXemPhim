package com.example.bt_cuoiky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LoaiPhimAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<LoaiPhim> loaiPhimList;

    public LoaiPhimAdapter(Context context, int layout, List<LoaiPhim> loaiPhimList) {
        this.context = context;
        this.layout = layout;
        this.loaiPhimList = loaiPhimList;
    }

    @Override
    public int getCount() {
        return loaiPhimList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView hinh;
        TextView ten;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.hinh = (ImageView) view.findViewById(R.id.iv_loaiphim);
            viewHolder.ten = (TextView) view.findViewById(R.id.tv_loaiphim);
            view.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) view.getTag();
        }
        LoaiPhim loaiPhim = loaiPhimList.get(i);
        viewHolder.ten.setText(loaiPhim.getTen());
        viewHolder.hinh.setImageResource(loaiPhim.getAnh());
        return view;
    }
}
