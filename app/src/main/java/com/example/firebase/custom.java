package com.example.firebase;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class custom extends AppCompatActivity {

    private FirebaseFirestore firestore;
    String a,b;
    private TextView scanResultText;
    private WifiManager wifiManager;
    private EditText mEditTextLocation;
    private List<ScanResult> scanResultList;
    boolean isPermitted = false;
    final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    public ArrayList<WifiInfo> wifiList = new ArrayList<>();
//    HashMap<String, Integer> map = new HashMap<String, Integer>();
//
//    HashMap<String, Integer> tmp = new HashMap<String, Integer>();

    ArrayList<Object> amap = new ArrayList<Object>();

    Map DBmap;
    private Bitmap image1;
    Button button;
    Canvas canvas;
    public static int x, y;

    String mac;
    int rssi;
    private Paint mPaint;
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("tga","ggggggggggggggggggggggggggggggggggggggg");
        super.onCreate(savedInstanceState);
        Log.e("tga","ghhhhhhhhhhhhhhhhhhhhhh");
        setContentView(R.layout.activity_user);
        Log.e("tga","jjjjjjjjjjjjjjjjjjjjjj");
        firestore = FirebaseFirestore.getInstance();

        button = findViewById(R.id.scanner);

        button.setOnClickListener(t);

        requestRuntimePermission();

        scanResultText = (TextView)findViewById(R.id.result);
//        mEditTextLocation = (EditText)findViewById(R.id.activit_main_location_edittext);
        scanResultText.setMovementMethod(new ScrollingMovementMethod());
        wifiManager = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);

        // wifi가 활성화되어있는지 확인 후 꺼져 있으면 켠다
        if(wifiManager.isWifiEnabled() == false)
            wifiManager.setWifiEnabled(true);

        if(isPermitted) {
            // wifi 스캔 시작
            wifiManager.startScan();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Location access 권한이 없습니다..", Toast.LENGTH_LONG).show();
        }
        /////////////////////////////////////////
        CollectionReference collectionReference = firestore.collection("classrooms");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot documentSnapshots : task.getResult()){
                        if(documentSnapshots.getData().get("x") != null && documentSnapshots.getData().get("y") != null){
                            x = Integer.parseInt(documentSnapshots.getData().get("x").toString());
                            y = Integer.parseInt(documentSnapshots.getData().get("y").toString());
                            Log.e("tga", "eeeeeeeeeeee" + x + " " + y);
//                                canvas.drawBitmap(image1, 0, 0, null);
//                                canvas.drawRect(x*100, y*100, x*100+10, y*100+10, mPaint); //캔버스에 빨간 사각형 그리기
                        }
                    }
                }
            }
        });

    }


    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                /*if(mRSSICount == 5) { getWifiInfo(); getWifiInfoAvg();} else*/
                getWifiInfo();
            }
        }
    };


    private void getWifiInfo() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        scanResultList = wifiManager.getScanResults();
        for (int i = 0; i < scanResultList.size(); i++) {
            ScanResult result = scanResultList.get(i);
        }
        scanResultText.append("번 위치에서 " + "===========\n");
        for (int i = 0; i < scanResultList.size(); i++) {
            ScanResult result = scanResultList.get(i);
            wifiList.add(new WifiInfo(result.SSID, result.BSSID, String.valueOf(result.level)));
        }


        for (int k = 0; k < 10; k++) {
            WifiInfo now = wifiList.get(k);
            scanResultText.append((k + 1)+ "\t\t" + "SSID :" + now.getSsid() + " " + "\t\t BSSID nn:  " + now.Bssid + "\t RSSI: " + now.getRssi() + " dBm\n");
        }

        scanResultText.append("===================================\n");
    }
//    private void getWifiInfo() {
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        scanResultList = wifiManager.getScanResults();
//        for (int i = 0; i < scanResultList.size(); i++) {
//            ScanResult result = scanResultList.get(i);
//        }
//        scanResultText.append("번 위치에서 " + "===========\n");
//        for (int i = 0; i < scanResultList.size(); i++) {
//            ScanResult result = scanResultList.get(i);
//            wifiList.add(new WifiInfo(result.SSID, result.BSSID, String.valueOf(result.level)));
//
//        }
//
//        //데이터 다 넣고난 후
//        ValueComparator vc = new ValueComparator(wifiList);
//        TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>(vc);
//        sortedMap.putAll(wifiList);
//        Iterator<java.util.Map.Entry<String, Integer>> it = sortedMap.entrySet().iterator();
//        for (int k = 0; k < 10; k++) {
//            String mBssidKey = it.next().getKey();
//            scanResultText.append((k + 1) + "\t\t BSSID nn:  " + mBssidKey + "\t RSSI: " + wifiList.get(mBssidKey) + " dBm\n");
//            map.put(mBssidKey, wifiList.get(mBssidKey));
//        }
//
//        for (HashMap.Entry<String, Integer> pair : map.entrySet()) {
//            a = pair.getKey().toString();
//            b = pair.getValue().toString();
//            Log.e("tgt" , a + " " + b);
//        }
//        firestore = FirebaseFirestore.getInstance();
//        //firestore.collection("classrooms").document(mEditTextLocation.getText().toString()).update("RSSI", map);
//
//        scanResultText.append("===================================\n");
//    }

    @Override
    protected void onResume() {
        super.onResume();
        // wifi scan 결과가 나왔을 때 전송되는 broadcast를 받기 위해
        // IntentFilter 객체를 생성하고 이를 이용하여 BroadcastReceiver 객체를 등록한다
        IntentFilter filter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }


    View.OnClickListener t = new View.OnClickListener(){
        public void onClick(View view){
            Map mv = (Map) findViewById(R.id.cv);
            Log.e("tga","bbbbbbbbbbbbbbbbbbbbbbbb");
            Log.e("tga","qqqqqqqqqqqqqqqqqq" + x);
            DBmap.x = x;
            DBmap.y = y;


            Toast.makeText(custom.this, "WiFi scan start!!", Toast.LENGTH_LONG).show();
            if(isPermitted) {
                // wifi 스캔 시작
                wifiManager.startScan();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Location access 권한이 없습니다..", Toast.LENGTH_LONG).show();
            }
            //DB 꺼내오기
//            CollectionReference collectionReference = firestore.collection("classrooms");
//            collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if(task.isSuccessful()){
//                        for(QueryDocumentSnapshot documentSnapshots : task.getResult()){
//                            if(documentSnapshots.getData().get("RSSI") != null){
//                                amap= (ArrayList<Object>) documentSnapshots.getData().get("RSSI");
//                                for(int i =0;i<amap.size();i++)
//                                {
//                                    for(int j = 0;j<wifiList.size();j++)
//                                    {
//                                        HashMap<String, String> mamap = (HashMap<String, String>) amap.get(i);
//                                        //HashMap<String, String> wmap = (HashMap<String, String>) wifiList.get(j);
//                                        String bssid = mamap.get("bssid");
//                                        String rssi = mamap.get("rssi");
//                                        Log.e("tga","rsssssssssssssssssssssssssssssssssss" + bssid + "sssssssss" + "rssi");
//                                        //String bssid_wifi = wifiList.get(i).get("bssid");
////                                        if (wifiList.get(i).get.equals("ba:6c:74:2b:23:2e")) {
////
////                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            });



            mv.invalidate();
        }
    };

    private void requestRuntimePermission() {
        //*******************************************************************
        // Runtime permission check
        //*******************************************************************
        if (ContextCompat.checkSelfPermission(custom.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(custom.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(custom.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        } else {
            // ACCESS_FINE_LOCATION 권한이 있는 것
            isPermitted = true;
        }
        //*********************************************************************
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // read_external_storage-related task you need to do.

                    // ACCESS_FINE_LOCATION 권한을 얻음
                    isPermitted = true;

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    // 권한을 얻지 못 하였으므로 location 요청 작업을 수행할 수 없다
                    // 적절히 대처한다
                    isPermitted = false;

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}



