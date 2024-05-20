package com.example.lab03_ontap.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab03_ontap.R;
import com.example.lab03_ontap.model.NhanVien;

import java.util.ArrayList;

public class NhanVienAdapter extends BaseAdapter {
    //Bien cau hinh
    private Context context;
    private ArrayList<NhanVien> nhanVienArrayList;

    public NhanVienAdapter(Context context, ArrayList<NhanVien> nhanVienArrayList) {
        this.context = context;
        this.nhanVienArrayList = nhanVienArrayList;
    }

    @Override
    public int getCount() {
        return nhanVienArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return nhanVienArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = converView;
        try {
            if(view == null ) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_listview, null);
            }

            // Lay id trong item_lv
            TextView maNV = view.findViewById(R.id.tvMaNV);
            TextView hoVaTen = view.findViewById(R.id.tvHoVaTen);
            TextView ngaySinh = view.findViewById(R.id.tvNgaySinh);
            TextView gioiTinh = view.findViewById(R.id.tvGioiTinh);

            //Lay doi tuon nhan vien
            NhanVien nhanVien = nhanVienArrayList.get(position);

            //Set cac gia tri cho text view
            maNV.setText(nhanVien.getMaNV());
            hoVaTen.setText(nhanVien.getHoVaTen());
            String ngaythangthoigian = nhanVien.getNgaySinh().toString();
            String ngaythangnam = ngaythangthoigian.substring(0,10);
            ngaySinh.setText(ngaythangnam);
            gioiTinh.setText(nhanVien.getGioiTinh());

        } catch (Exception e) {
            Log.e("TAG", "MESSAGE: " + e.getMessage());
        }
        return view;
    }

}
