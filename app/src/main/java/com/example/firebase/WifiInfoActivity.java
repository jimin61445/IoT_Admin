package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class WifiInfoActivity extends AppCompatActivity {
    RecyclerView view;
    WifiAdapter wifiAdapter;
    RecyclerView.LayoutManager linearLayoutManager;
    ArrayList<WifiInfo> wifis = new ArrayList<WifiInfo>();

    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_info);

        Intent getIntent = getIntent();
        name = getIntent.getStringExtra("className");
        view = findViewById(R.id.classWifi);
        wifiAdapter = new WifiAdapter(this, wifis);
        linearLayoutManager = new LinearLayoutManager(this);
        view.setLayoutManager(linearLayoutManager);
        view.setAdapter(wifiAdapter);
        setData();
    }

    public void setData(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("classrooms").document(name).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    String ssid, bssid, rssi;
                    ArrayList<Object> get = (ArrayList<Object>) documentSnapshot.getData().get("RSSI");

                    if(get == null){
                        Toast.makeText(WifiInfoActivity.this, "정보 스캔하세요",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for (int i = 0;i<get.size();i++){
                        HashMap<String, String> map = (HashMap <String, String>) get.get(i);
                        ssid = map.get("ssid");
                        bssid = map.get("bssid");
                        rssi = map.get("rssi");

                        wifis.add(new WifiInfo(ssid, bssid, rssi));
                    }
                    wifiAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}