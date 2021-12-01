package com.bcs421.homework7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BarActivity extends View  {
    //hash map
    Map<String, Float> data = new HashMap<>();
    private float barWidth = 60f;
    private float barSpace = 20f;
    private Paint mPaint;

    Intent i = Intent.getIntent("Grade");
    String[] myGrades = i.getStringArrayExtra("Grade");
    Intent intent = Intent.getIntent("Values");
    int[] myValues = intent.getIntArrayExtra("Values");


    public BarActivity(Context context, AttributeSet attrs) throws URISyntaxException  {
        super(context, attrs);


        // mRandom = new Random();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /// makes the x and y lines
        float sw = canvas.getWidth();
        float sh = canvas.getHeight();
        int sum = 0;

        canvas.drawARGB(255, 255, 255, 0);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(20, sh * 7 / 8, sw - 20, sh * 7 / 8, mPaint);
        canvas.drawLine(20, 0, 10, sh-0, mPaint);

        ArrayList<BarActivity> students = new ArrayList<>();
        for (int i = 0; i <=myGrades.length;i++)
        {
            data.put(myGrades[i],myValues[i] + 0.0f);
            myValues[i] += sum;
           // students.add(new BarActivity(i,myValues[i]));


        }


        mPaint.setColor(Color.RED);
        canvas.drawRect(sw / 8, sh * (7 / 8f - (float) data.get("A")), sw / 8 + barWidth,7 * sh / 8, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(sw / 8 + 1 * (barSpace + barWidth), sh * (7 / 8f - (float) data.get("A")),sw / 8 + barWidth + (barSpace + barWidth), 7 * sh / 8, mPaint);
        mPaint.setTextSize(50);
       // canvas.drawRect("A",sw / 8 + 15, 7 * sh / 8 + 80, mPaint);



        canvas.drawText("A",150,1750,mPaint);
        canvas.drawText("B",230,1750,mPaint);
        canvas.drawText("C",320,1750,mPaint);
        canvas.drawText("D",410,1750,mPaint);
        canvas.drawText("F",500,1750,mPaint);

        canvas.drawRect(sw/8 + 4 * (barSpace +barWidth),sh * (7 / 8f - (float) data.get("B")),
              sw/8 + barWidth + (barSpace + barWidth), 7 * sh / 8, mPaint);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(sw / 8 + 2 * (barSpace + barWidth), sh * (7 / 8f - (float) data.get("F")),
                sw / 8 + barWidth + 2 * (barSpace + barWidth),
                7 * sh / 8, mPaint);
    }

}