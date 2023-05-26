//301, 302, 303 띄우기
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

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassListViewHolder>{
    private ArrayList<String> mDataset;
    private final Activity activity;

    public static class ClassListViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout cardView;
        public ClassListViewHolder(LinearLayout v){
            super(v);
            cardView = v;
        }
    }

    public ClassAdapter(Activity activity, ArrayList<String> myDataset){
        mDataset = myDataset;
        this.activity = activity;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ClassAdapter.ClassListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LinearLayout cardView = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.class_info, parent, false);
        return new ClassListViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ClassListViewHolder holder, @SuppressLint("RecyclerView") int position){
        LinearLayout cardView = holder.cardView;
        Button button = cardView.findViewById(R.id.room_num);
        button.setText(mDataset.get(position));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, WifiInfoActivity.class);
                intent.putExtra("className", mDataset.get(position));
                activity.startActivity(intent);
            }
        });
    }

    public void  filterList(ArrayList<String> filteredList) {
        mDataset = filteredList;
        notifyDataSetChanged();
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
