package com.example.firebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdministerActivity extends AppCompatActivity {
    RecyclerView view;
    ClassAdapter classAdapter;
    RecyclerView.LayoutManager linearLayoutManager;
    ArrayList<String> roomNum = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administer);
        init();
        view = findViewById(R.id.classAdapter);
        classAdapter = new ClassAdapter(this, roomNum);
        linearLayoutManager = new LinearLayoutManager(this);
        view.setLayoutManager(linearLayoutManager);
        view.setAdapter(classAdapter);
        Button add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        String name = "40";
        String name2 = "4";
        String name3 = "50";
        String name4 = "5";
        for(int i =1;i<=9;i++)
        {
            roomNum.add(name + i);
        }
        for(int i =10;i<=19;i++)
        {
            roomNum.add(name2 + i);
        }
        for(int i =1;i<=9;i++)
        {
            roomNum.add(name3 + i);
        }
        for(int i =10;i<=19;i++)
        {
            roomNum.add(name4 + i);
        }
        roomNum.add("1번 계단");

    }
}
