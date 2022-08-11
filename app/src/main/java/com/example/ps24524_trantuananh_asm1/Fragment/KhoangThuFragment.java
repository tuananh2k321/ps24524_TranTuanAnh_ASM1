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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps24524_trantuananh_asm1.Adapter.PhanLoaiAdapter;
import com.example.ps24524_trantuananh_asm1.Model.PhanLoai;
import com.example.ps24524_trantuananh_asm1.R;
import com.example.ps24524_trantuananh_asm1.database.PhanLoaiDAO;

import java.util.ArrayList;
import java.util.List;

public class KhoangThuFragment extends Fragment {
    private RecyclerView recyclerViewKhoangThu;
    private PhanLoaiAdapter khoangThuAdapter;
    private List<PhanLoai> listPhanLoai;
    final FragmentActivity c = getActivity();
    Button btnInsertThu;
    private PhanLoaiDAO phanLoaiDAO ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.khoangthu_layout, container, false);
        btnInsertThu = view.findViewById(R.id.button_insert_thu);

        khoangThuAdapter = new PhanLoaiAdapter(new PhanLoaiAdapter.IClickItemUser() {
            @Override
            public void updatePhanLoai(PhanLoai phanLoai) {
                clickUpdateLoaiThu(phanLoai);
            }

            @Override
            public void deletePhanLoai(PhanLoai phanLoai) {
                clickDeleteLoaiThu(phanLoai);
            }
        });
        listPhanLoai  = new ArrayList<PhanLoai>();
        phanLoaiDAO = new PhanLoaiDAO(getContext());
        recyclerViewKhoangThu = view.findViewById(R.id.rcv_khoangthu);
        // 2. set layoutManger
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(c, RecyclerView.VERTICAL, false);
        recyclerViewKhoangThu.setLayoutManager(linearLayoutManager);
        // 4. set adapter
        recyclerViewKhoangThu.setAdapter(khoangThuAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerViewKhoangThu.setItemAnimator(new DefaultItemAnimator());
        khoangThuAdapter.setData(listPhanLoai);

        btnInsertThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInsertLoaiThu(new PhanLoai());
            }
        });

        LoadData();

        return view;
    }

    private void clickDeleteLoaiThu(PhanLoai phanLoai) {
        phanLoaiDAO.delete(phanLoai);
        LoadData();
        Toast.makeText(getContext(), "Xóa thảnh công", Toast.LENGTH_SHORT).show();
    }

    private void clickUpdateLoaiThu(PhanLoai phanLoai) {
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
                String trangThai = "thu";

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

    private void clickInsertLoaiThu(PhanLoai phanLoai) {
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
                String trangThai = "thu";

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
        listPhanLoai = phanLoaiDAO.tenLoaiThu();
        khoangThuAdapter.setData(listPhanLoai);
    }

}
