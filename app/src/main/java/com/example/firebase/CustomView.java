package com.example.firebase;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import android.content.Context;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CustomView extends View
{
    public CustomView(Context context, AttributeSet attrs)
    {

        super(context, attrs);

        Button scanner = findViewById(R.id.scanner);
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("tga","ghhhhhhhhhhhh");
            }
        });
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Paint MyPaint = new Paint();
        MyPaint.setStrokeWidth(10f);
        MyPaint.setStyle(Paint.Style.STROKE);
        MyPaint.setColor(Color.WHITE);
        Path path = new Path();
        path.moveTo(450,500);
        path.lineTo(450,500);
        path.lineTo(400,600);
        path.lineTo(300,600);
        path.lineTo(400,700);
        path.lineTo(350,800);
        path.lineTo(450,700);
        path.lineTo(550,800);
        path.lineTo(500,700);
        path.lineTo(600,600);
        path.lineTo(500,600);
        path.close();
        canvas.drawPath(path,MyPaint);
    }
}
