package com.bcs421.homework7;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ListView listView;
    ListAdapter listAdapter;
    Button btnCompute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.edittext);
        listView=findViewById(R.id.listView);
        btnCompute=findViewById(R.id.btnCompute);

        editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE)
                {

                    try
                    {

                        if(editText.getText().toString().isEmpty())
                        {
                            listAdapter=null;
                            listView.setAdapter(null);
                        }
                        else
                        {
                            String[] arr =editText.getText().toString().split(",");
                            Intent i = new Intent(MainActivity.this,BarActivity.class);
                            i.putExtra("Grades", arr);
                            startActivity(i);
                            listAdapter=new ListAdapter(MainActivity.this,arr);
                            listView.setAdapter(listAdapter);
                        }

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listAdapter!=null)
                {

                    Integer [] list=listAdapter.getList();

                    Intent intent = new Intent(MainActivity.this,BarActivity.class);
                    intent.putExtra("Values", list);
                    startActivity(intent);
                }
            }
        });

    }

    private void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    class ListAdapter extends BaseAdapter{

        String arr[];
        Context context;
        LayoutInflater layoutInflater;
        Integer[] list;
        public  ListAdapter(Context context, String arr[])
        {
            this.arr=arr;
            this.list=new Integer[arr.length];
            this.context=context;
            layoutInflater=LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return arr.length;
        }

        @Override
        public Object getItem(int i) {
            return arr[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
        public Integer[] getList()
        {
            return  this.list;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {

            
            View inflate = layoutInflater.inflate(R.layout.lyt_item, null);
            EditText editText=inflate.findViewById(R.id.edittext);
            editText.setHint("Enter the no of "+arr[i]+" Students");
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    if(editText.getText().toString().isEmpty())
                    {
                        list[i]=0;
                    }
                    else
                    {
                        list[i]=Integer.parseInt(editText.getText().toString());
                    }

                }
            });
            return inflate;
        }
    }
    /*
    class BarActivity2 extends View{

        Map<String, Float> data = new HashMap<>();
        private float barWidth = 60f;
        private float barSpace = 20f;
        private Paint mPaint;

        public BarActivity2(Context context, AttributeSet attrs) {
            super(context, attrs);

            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            // mRandom = new Random();

            data.put("A", 0.20f);
            data.put("A-", 0.12f);
            data.put("F", 0.1f);

        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            /// makes the x and y lines
            float sw = canvas.getWidth();
            float sh = canvas.getHeight();
            canvas.drawARGB(255, 255, 255, 0);
            mPaint.setColor(Color.BLUE);
            canvas.drawLine(20, sh * 7 / 8, sw - 20, sh * 7 / 8, mPaint);
            canvas.drawLine(20, 0, 10, sh-0, mPaint);

            mPaint.setColor(Color.RED);
            canvas.drawRect(sw / 8, sh * (7 / 8f - (float) data.get("A")), sw / 8 + barWidth,
                    //display how hight the bar will go
                    7 * sh / 8, mPaint);
            mPaint.setColor(Color.GREEN);
            canvas.drawRect(sw / 8 + 1 * (barSpace + barWidth), sh * (7 / 8f - (float) data.get("A")),
                    sw / 8 + barWidth + (barSpace + barWidth), 7 * sh / 8, mPaint);
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

     */

}