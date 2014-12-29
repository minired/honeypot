package com.example.sj.todoapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class PopActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        TextView tv3 = (TextView)findViewById(R.id.textView1);
        Typeface fontRobo = Typeface.createFromAsset(getAssets(), "fonts/notosanskrmedium.otf");
        tv3.setTypeface(fontRobo);
        tv3.setTextColor(Color.parseColor("#000000"));
        tv3.setTextSize(15);

        tv3.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isClickable()) {
                    finish();
                }
            }

        });


        ImageView btn = (ImageView)findViewById(R.id.imageView12);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.page = 0;
                Intent intentSubActivity = new Intent(PopActivity.this, DetailActivity.class );
                startActivity(intentSubActivity);
            }
        });

        ImageView btn2 = (ImageView)findViewById(R.id.imageView13);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.page = 1;
                Intent intentSubActivity = new Intent(PopActivity.this, DetailActivity.class );
                startActivity(intentSubActivity);
            }
        });

        ImageView btn3 = (ImageView)findViewById(R.id.imageView14);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.page = 2;
                Intent intentSubActivity = new Intent(PopActivity.this, DetailActivity.class );
                startActivity(intentSubActivity);
            }
        });
        ImageView btn4 = (ImageView)findViewById(R.id.imageView15);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.page = 3;
                Intent intentSubActivity = new Intent(PopActivity.this, DetailActivity.class );
                startActivity(intentSubActivity);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
