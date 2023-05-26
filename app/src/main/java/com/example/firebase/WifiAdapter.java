//303 들어갔을 때 정보 띄우기
package com.example.firebase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WifiAdapter extends RecyclerView.Adapter<WifiAdapter.WifiListViewHolder>{
    private ArrayList<WifiInfo> mDataset;
    private final Activity activity;

    public static class WifiListViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout relativeLayout;
        public WifiListViewHolder(LinearLayout v){
            super(v);
            relativeLayout = v;
        }
    }

    public WifiAdapter(Activity activity, ArrayList<WifiInfo> myDataset){
        mDataset = myDataset;
        this.activity = activity;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public WifiAdapter.WifiListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LinearLayout relativeLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.wifi_info, parent, false);
        return new WifiListViewHolder(relativeLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final WifiListViewHolder holder, @SuppressLint("RecyclerView") int position){
        LinearLayout relativeLayout = holder.relativeLayout;
        TextView ssid = relativeLayout.findViewById((R.id.ssid));
        TextView bssid = relativeLayout.findViewById(R.id.bssid);
        TextView rssi = relativeLayout.findViewById(R.id.rssi);
        ssid.setText(mDataset.get(position).getSsid());
        bssid.setText(mDataset.get(position).getBssid());
        rssi.setText(mDataset.get(position).getRssi());
    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}