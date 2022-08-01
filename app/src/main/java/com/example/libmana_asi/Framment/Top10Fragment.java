package com.example.libmana_asi.Framment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.libmana_asi.Adapter.TopAdapter;
import com.example.libmana_asi.Dao.PhieuMuonDAO;
import com.example.libmana_asi.Dao.ThongKeDAO;
import com.example.libmana_asi.Model.Top;
import com.example.libmana_asi.R;

import java.util.ArrayList;

public class Top10Fragment extends Fragment {
    ListView lvtop;
    ArrayList<Top> list;
    TopAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  v=inflater.inflate(R.layout.fragment_top10, container, false);
        lvtop=v.findViewById(R.id.lvTop);
        ThongKeDAO thongKeDAO= new ThongKeDAO(getActivity());
        list= (ArrayList<Top>)thongKeDAO.getTOP();
        adapter= new TopAdapter(getActivity(),this,list);
        lvtop.setAdapter(adapter);
        return v;
    }
}