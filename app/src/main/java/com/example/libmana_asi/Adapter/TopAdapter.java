package com.example.libmana_asi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.libmana_asi.Framment.Top10Fragment;
import com.example.libmana_asi.Model.Top;
import com.example.libmana_asi.R;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    Top10Fragment fragment;
    ArrayList<Top> list;
    TextView tvsach, tvSoluong;
    ImageView img;

    public TopAdapter(@NonNull Context context, Top10Fragment fragment, ArrayList<Top> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.top_item,null);
        }
        final Top item= list.get(position);
        if(item!=null){
            tvsach=v.findViewById(R.id.tvsachT);
            tvsach.setText("Sách: "+item.getTenSach());
            tvSoluong=v.findViewById(R.id.tvSL);
            tvSoluong.setText("Số lượng"+item.getSoLuong());
        }
        return v;
    }
}
