package com.example.firebase;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Map extends View
{

    public Canvas canvas;
    private FirebaseFirestore firestore;
    private Bitmap image1;
    private Paint mPaint;

    public static int x , y;
    public Map(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }
    public Map(Context context){
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Log.e("tga","Mappppppppppppppppppppppppppppppppp" + x +" " + y);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();

//            firestore.collection("classrooms").document("402").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    if(task.isSuccessful()){


        if(x != 0)
        {

            path.moveTo(x*100,y*100);
            path.lineTo(x*100,y*100);
            path.lineTo(x*100+50,y*100+50);
            path.close();
            canvas.drawPath(path,paint);
        }
    }

}
