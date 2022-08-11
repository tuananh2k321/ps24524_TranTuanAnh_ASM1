package com.example.ps24524_trantuananh_asm1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ps24524_trantuananh_asm1.Fragment.GioiThieuFragment;
import com.example.ps24524_trantuananh_asm1.Fragment.KhoangChiFragment;
import com.example.ps24524_trantuananh_asm1.Fragment.KhoangThuFragment;
import com.example.ps24524_trantuananh_asm1.Fragment.ThongKeFragment;
import com.example.ps24524_trantuananh_asm1.Model.PhanLoai;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ViewPager2 viewPager2;
    TabLayout tabLayout;

    private  static  final int FRAGMENT_KHOANGTHU = 0;
    private  static  final int FRAGMENT_KHOANGCHI = 1;
    private  static  final int FRAGMENT_THONGKE = 2;
    private  static  final int FRAGMENT_GIOITHIEU = 3;

    private int mCurrentFragment = FRAGMENT_KHOANGTHU;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView =  findViewById(R.id.navigation);
        // tablayout and viewpager2
        viewPager2 = findViewById(R.id.viewPagerTest);
        tabLayout = findViewById(R.id.tabLayoutTest);

        FragmentManager fm = getSupportFragmentManager();
        CustomFrmAdapter adapter = new CustomFrmAdapter(fm, getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Khoảng thu"));
        tabLayout.addTab(tabLayout.newTab().setText("Khoảng chi"));
        tabLayout.addTab(tabLayout.newTab().setText("Thống kê"));
        tabLayout.addTab(tabLayout.newTab().setText("Giới thiệu"));



        //Kết nối TabLayout với Bộ điều hợp
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        navigationView.getMenu().findItem(R.id.khoang_thu).setChecked(true);
                        navigationView.getMenu().findItem(R.id.khoang_chi).setChecked(false);
                        navigationView.getMenu().findItem(R.id.thong_ke).setChecked(false);
                        navigationView.getMenu().findItem(R.id.gioi_thieu).setChecked(false);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.khoang_chi).setChecked(true);
                        navigationView.getMenu().findItem(R.id.khoang_thu).setChecked(false);
                        navigationView.getMenu().findItem(R.id.thong_ke).setChecked(false);
                        navigationView.getMenu().findItem(R.id.gioi_thieu).setChecked(false);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.thong_ke).setChecked(true);
                        navigationView.getMenu().findItem(R.id.khoang_chi).setChecked(false);
                        navigationView.getMenu().findItem(R.id.khoang_thu).setChecked(false);
                        navigationView.getMenu().findItem(R.id.gioi_thieu).setChecked(false);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.gioi_thieu).setChecked(true);
                        navigationView.getMenu().findItem(R.id.thong_ke).setChecked(false);
                        navigationView.getMenu().findItem(R.id.khoang_chi).setChecked(false);
                        navigationView.getMenu().findItem(R.id.khoang_thu).setChecked(false);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Thay đổi Tab khi vuốt
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        //toolbar



// Update the action bar title with the TypefaceSpan instance


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        // frm khoang thu dc selected va hien len trc
        viewPager2.setCurrentItem(0);
        navigationView.getMenu().findItem(R.id.khoang_thu).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.khoang_thu) {
            viewPager2.setCurrentItem(0);
                mCurrentFragment = FRAGMENT_KHOANGTHU;
                navigationView.getMenu().findItem(R.id.khoang_thu).setChecked(true);
                navigationView.getMenu().findItem(R.id.khoang_chi).setChecked(false);
                navigationView.getMenu().findItem(R.id.thong_ke).setChecked(false);
                navigationView.getMenu().findItem(R.id.gioi_thieu).setChecked(false);


        }
        else if (id == R.id.khoang_chi) {
            if (mCurrentFragment != FRAGMENT_KHOANGCHI) {
                viewPager2.setCurrentItem(1);
                mCurrentFragment = FRAGMENT_KHOANGCHI;
                navigationView.getMenu().findItem(R.id.khoang_chi).setChecked(true);
                navigationView.getMenu().findItem(R.id.khoang_thu).setChecked(false);
                navigationView.getMenu().findItem(R.id.thong_ke).setChecked(false);
                navigationView.getMenu().findItem(R.id.gioi_thieu).setChecked(false);
            }
        }
        else if (id == R.id.thong_ke) {
            if (mCurrentFragment != FRAGMENT_THONGKE) {
                viewPager2.setCurrentItem(2);
                mCurrentFragment = FRAGMENT_THONGKE;
                navigationView.getMenu().findItem(R.id.thong_ke).setChecked(true);
                navigationView.getMenu().findItem(R.id.khoang_chi).setChecked(false);
                navigationView.getMenu().findItem(R.id.khoang_thu).setChecked(false);
                navigationView.getMenu().findItem(R.id.gioi_thieu).setChecked(false);
            }
        } else  if (id == R.id.gioi_thieu) {
            if (mCurrentFragment != FRAGMENT_GIOITHIEU) {
                viewPager2.setCurrentItem(3);
                mCurrentFragment = FRAGMENT_GIOITHIEU;
                navigationView.getMenu().findItem(R.id.gioi_thieu).setChecked(true);
                navigationView.getMenu().findItem(R.id.thong_ke).setChecked(false);
                navigationView.getMenu().findItem(R.id.khoang_chi).setChecked(false);
                navigationView.getMenu().findItem(R.id.khoang_thu).setChecked(false);
            }
        } else  if (id == R.id.thoat) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




}