package com.example.ps24524_trantuananh_asm1.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps24524_trantuananh_asm1.Adapter.PhanLoaiAdapter;
import com.example.ps24524_trantuananh_asm1.Model.PhanLoai;
import com.example.ps24524_trantuananh_asm1.R;
import com.example.ps24524_trantuananh_asm1.database.PhanLoaiDAO;

import java.util.ArrayList;
import java.util.List;

public class KhoangChiFragment extends Fragment {
    RecyclerView recyclerViewKhoangChi;
    List<PhanLoai> listPhanLoai;
    PhanLoaiDAO phanLoaiDAO;
    PhanLoaiAdapter khoangChiAdapter;
    Button btnInsertLoaiChi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.khoangchi_layout, container, false);
        recyclerViewKhoangChi = view.findViewById(R.id.rcv_khoangchi);
        btnInsertLoaiChi = view.findViewById(R.id.button_insert_chi);
        listPhanLoai = new ArrayList<PhanLoai>();
        khoangChiAdapter = new PhanLoaiAdapter(new PhanLoaiAdapter.IClickItemUser() {
            @Override
            public void updatePhanLoai(PhanLoai phanLoai) {
                clickUpdateLoaiChi(phanLoai);
            }

            @Override
            public void deletePhanLoai(PhanLoai phanLoai) {
                clickDeleteLoaiChi(phanLoai);
            }
        });
        phanLoaiDAO = new PhanLoaiDAO(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewKhoangChi.setLayoutManager(linearLayoutManager);

        recyclerViewKhoangChi.setAdapter(khoangChiAdapter);
        recyclerViewKhoangChi.setItemAnimator(new DefaultItemAnimator());
        khoangChiAdapter.setData(listPhanLoai);

        btnInsertLoaiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInsertLoaiChi(new PhanLoai());
            }
        });

        LoadData();
        return view;
    }



    private void clickDeleteLoaiChi(PhanLoai phanLoai) {
        phanLoaiDAO.delete(phanLoai);
        LoadData();
        Toast.makeText(getContext(), "Xóa thảnh công", Toast.LENGTH_SHORT).show();
    }

    private void clickUpdateLoaiChi(PhanLoai phanLoai) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_phanloai_update);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;

        window.setAttributes(windowAttributes);

        EditText edtThemTenLoai = dialog.findViewById(R.id.edt_themtenloaithu);
        Button btnHuy = dialog.findViewById(R.id.btn_huy);
        Button btnThem = dialog.findViewById(R.id.btn_them);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoaiThu = edtThemTenLoai.getText().toString().trim();
                String trangThai = "chi";

                if (tenLoaiThu == null) {
                    return;
                }

                // id la cu, ten lop la moi
//                phanLoai.setMaLoai(1);
                phanLoai.setTenLoai(tenLoaiThu);
                phanLoai.setTrangThai(trangThai);
                phanLoaiDAO.updateTenLoai(phanLoai);

                Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();

                LoadData();
            }
        });

        dialog.show();
    }

    private void clickInsertLoaiChi(PhanLoai phanLoai) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_phanloai);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;

        window.setAttributes(windowAttributes);

        EditText edtThemTenLoai = dialog.findViewById(R.id.edt_tenloaithu);
        Button btnHuy = dialog.findViewById(R.id.btn_huy);
        Button btnThem = dialog.findViewById(R.id.btn_them);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoaiThu = edtThemTenLoai.getText().toString().trim();
                String trangThai = "chi";

                if (tenLoaiThu == null) {
                    return;
                }

                // id la cu, ten lop la moi
//                phanLoai.setMaLoai(1);
                phanLoai.setTenLoai(tenLoaiThu);
                phanLoai.setTrangThai(trangThai);
                phanLoaiDAO.taoPhanLoai(phanLoai);

                edtThemTenLoai.setText("");

                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                LoadData();
            }
        });

        dialog.show();
    }

    public void LoadData() {
        listPhanLoai = phanLoaiDAO.tenLoaiChi();
        khoangChiAdapter.setData(listPhanLoai);
    }
}
