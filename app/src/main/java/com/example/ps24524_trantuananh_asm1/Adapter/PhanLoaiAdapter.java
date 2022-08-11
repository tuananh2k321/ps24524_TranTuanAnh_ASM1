package com.example.ps24524_trantuananh_asm1.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps24524_trantuananh_asm1.Fragment.KhoangThuFragment;
import com.example.ps24524_trantuananh_asm1.MainActivity;
import com.example.ps24524_trantuananh_asm1.Model.PhanLoai;
import com.example.ps24524_trantuananh_asm1.R;

import java.util.List;


public class PhanLoaiAdapter extends RecyclerView.Adapter<PhanLoaiAdapter.PhanLoaiViewHolder>{
    private List<PhanLoai> listPhanLoai;
    Context context;
    private IClickItemUser iClickItemUser;
    public interface IClickItemUser {
        void updatePhanLoai(PhanLoai phanLoai);

        void deletePhanLoai(PhanLoai phanLoai);
    }

    public PhanLoaiAdapter(IClickItemUser iClickItemUser) {
        this.iClickItemUser = iClickItemUser;
    }

    public void setData(List<PhanLoai> list) {
        this.listPhanLoai = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhanLoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_layout, parent, false);
        return new PhanLoaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhanLoaiViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final PhanLoai phanLoai = listPhanLoai.get(position);
        if (phanLoai == null) {
            return;
        }

        holder.tvTenLoai.setText(listPhanLoai.get(position).getTenLoai());

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemUser.updatePhanLoai(phanLoai);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemUser.deletePhanLoai(phanLoai);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listPhanLoai != null) {
            return listPhanLoai.size();
        }
        return 0;
    }

    public class PhanLoaiViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenLoai;
        private Button btnUpdate, btnDelete;

        public PhanLoaiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenLoai = itemView.findViewById(R.id.tvTenLoai);
            btnUpdate = itemView.findViewById(R.id.btn_uodate);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
