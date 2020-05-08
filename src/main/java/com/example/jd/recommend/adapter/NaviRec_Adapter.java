package com.example.jd.recommend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jd.R;

import java.util.ArrayList;

public class NaviRec_Adapter extends RecyclerView.Adapter<NaviRec_Adapter.ViewHolder> {
    private Context context;
    private ArrayList<String> titles;

    public NaviRec_Adapter(Context context, ArrayList<String> titles) {
        this.context = context;
        this.titles = titles;
    }

    @NonNull
    @Override
    public NaviRec_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.navi_rec, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull NaviRec_Adapter.ViewHolder holder, int position) {
        String s = titles.get(position);
        holder.name.setText(s);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
