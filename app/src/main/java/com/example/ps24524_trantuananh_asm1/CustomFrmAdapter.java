package com.example.ps24524_trantuananh_asm1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ps24524_trantuananh_asm1.Fragment.GioiThieuFragment;
import com.example.ps24524_trantuananh_asm1.Fragment.KhoangChiFragment;
import com.example.ps24524_trantuananh_asm1.Fragment.KhoangThuFragment;
import com.example.ps24524_trantuananh_asm1.Fragment.ThongKeFragment;

public class CustomFrmAdapter extends FragmentStateAdapter {
    public CustomFrmAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new KhoangThuFragment();
            case 1:
                return new KhoangChiFragment();
            case 2:
                return new ThongKeFragment();
            case 3:
                return new GioiThieuFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
