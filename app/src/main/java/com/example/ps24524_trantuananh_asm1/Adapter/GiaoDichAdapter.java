package com.example.ps24524_trantuananh_asm1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps24524_trantuananh_asm1.Model.GiaoDich;
import com.example.ps24524_trantuananh_asm1.R;

import java.util.ArrayList;
import java.util.List;

public class GiaoDichAdapter extends RecyclerView.Adapter<GiaoDichAdapter.GiaoDichViewHolder>{
    private List<GiaoDich> listGiaoDich;
    Context context;



    @NonNull
    @Override
    public GiaoDichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thongke_layout,parent, false);
        return new GiaoDichViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiaoDichViewHolder holder, int position) {
        GiaoDich giaoDich = listGiaoDich.get(position);

        if (giaoDich == null) {
            return;
        }
    }

    @Override
    public int getItemCount() {
        if (listGiaoDich != null) {
            return listGiaoDich.size();
        }
        return 0;
    }

    public class GiaoDichViewHolder extends RecyclerView.ViewHolder {
        public GiaoDichViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
