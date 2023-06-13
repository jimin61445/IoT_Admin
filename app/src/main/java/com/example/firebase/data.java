package com.example.firebase;

import android.util.Log;

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
                        }
                    }
                }
            }
        });
    }

}
