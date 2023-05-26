package com.example.firebase;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class data {
    private FirebaseFirestore firestore;
    public static int x, y;

    public data(){
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

}
