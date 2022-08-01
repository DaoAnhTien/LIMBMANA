package com.example.libmana_asi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.libmana_asi.Adapter.PhieumuonAdapter;
import com.example.libmana_asi.Dao.Dbdatabase;
import com.example.libmana_asi.Dao.ThuThuDAO;
import com.example.libmana_asi.Framment.AddUserFragment;
import com.example.libmana_asi.Framment.ChangePassFragment;
import com.example.libmana_asi.Framment.DoanhthuFragment;
import com.example.libmana_asi.Framment.LoaiSachFragment;
import com.example.libmana_asi.Framment.PhieumuonFragment;
import com.example.libmana_asi.Framment.SachFragment;
import com.example.libmana_asi.Framment.ThanhVienFragment;
import com.example.libmana_asi.Framment.Top10Fragment;
import com.example.libmana_asi.Model.ThuThu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mheaderview;
    TextView edUser;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dbdatabase dbdatabase= new Dbdatabase(getApplicationContext());
        drawer=findViewById(R.id.draw_layout);
        toolbar=findViewById(R.id.toolbar1);
        //set tooolbarr thay the cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab= getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);
        //show user in herder
        NavigationView nv= findViewById(R.id.nvView);
        mheaderview=nv.getHeaderView(0);
        edUser=mheaderview.findViewById(R.id.txtUser);
        Intent i=getIntent();
        String user =i.getStringExtra("user");
        thuThuDAO= new ThuThuDAO(getBaseContext());
        ThuThu thuThu= thuThuDAO.getID(user);
        String username=thuThu.getHoTen();
        edUser.setText("Wellcome: "+username+" ! ") ;
        //dung phieu muon lam Home
        FragmentManager fragmentManager=getSupportFragmentManager();
        PhieumuonFragment phieumuonFragment= new PhieumuonFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.flcontent,phieumuonFragment)
                .commit();
        //addmin cos quyen add user
        if(user.equalsIgnoreCase("admin")){
            nv.getMenu().findItem(R.id.Sub_addsuer).setVisible(true);
        }
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()) {
                    case R.id.nav_phieumuon:
                        setTitle("Quan li phieu muon");
                        PhieumuonFragment phieumuonFragment= new PhieumuonFragment();
                        manager.beginTransaction()
                                .replace(R.id.flcontent,phieumuonFragment)
                                .commit();
                        break;
                    case R.id.nav_loaisach:
                        setTitle("Quản lí loại Sách");
                        LoaiSachFragment loaiSachFragment= new LoaiSachFragment();
                        manager.beginTransaction()
                                .replace(R.id.flcontent,loaiSachFragment)
                                .commit();
                        break;
                    case R.id.nav_sach:
                        setTitle("Quan lí sách");
                        SachFragment sachFragment= new SachFragment();
                        manager.beginTransaction()
                                .replace(R.id.flcontent,sachFragment)
                                .commit();

                        break;
                    case R.id.nav_thanhvien:
                        setTitle("thành viên");
                        ThanhVienFragment thanhVienFragment= new ThanhVienFragment();
                        manager.beginTransaction()
                                .replace(R.id.flcontent,thanhVienFragment)
                                .commit();

                        break;
                    case R.id.Sub_Top:
                        setTitle("Top 10 mượn nhiều nhất");
                        Top10Fragment top10Fragment= new Top10Fragment();
                        manager.beginTransaction()
                                .replace(R.id.flcontent,top10Fragment)
                                .commit();

                        break;
                    case R.id.Sub_Doanhthu:
                        setTitle("Quan li Doanh Thu");
                        DoanhthuFragment doanhthuFragment= new DoanhthuFragment();
                        manager.beginTransaction()
                                .replace(R.id.flcontent,doanhthuFragment)
                                .commit();


                        break;
                    case R.id.Sub_addsuer:
                        setTitle("Thêm người Dùng");
                        AddUserFragment addUserFragment= new AddUserFragment();
                        manager.beginTransaction()
                                .replace(R.id.flcontent,addUserFragment)
                                .commit();
                        break;
                    case R.id.Sub_Pass:
                        setTitle("Đỏi mật Khẩu");
                        ChangePassFragment changePassFragment= new ChangePassFragment();
                        manager.beginTransaction()
                                .replace(R.id.flcontent,changePassFragment)
                                .commit();
                        break;
                    case R.id.Sub_logout:
                        Intent i= new Intent(MainActivity.this,LoginainActivity.class);
                        startActivity(i);
                        break;
                }
                drawer.closeDrawers();
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id== android.R.id.home);
        drawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}