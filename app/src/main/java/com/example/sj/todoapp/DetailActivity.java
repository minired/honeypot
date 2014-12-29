package com.example.sj.todoapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

    static public String backbutoon = "500m내에 스터디 공간";
    static public int page  = 0;

    private void SetPage()
    {

        ImageView iv1 = (ImageView) findViewById(R.id.imageView13);
        ImageView iv2 = (ImageView) findViewById(R.id.imageView14);
        ImageView iv3 = (ImageView) findViewById(R.id.imageView15);
        ImageView iv4 = (ImageView) findViewById(R.id.imageView16);
        ImageView iv5 = (ImageView) findViewById(R.id.imageView17);
        switch(page)
        {
            case 0:
                iv1.setImageResource(R.drawable.store_header01);
                iv2.setImageResource(R.drawable.store_info);
                iv3.setImageResource(R.drawable.store_map);
                iv4.setImageResource(R.drawable.store_photo);
                iv5.setImageResource(R.drawable.store_review);
                break;
            case 1:
                iv1.setImageResource(R.drawable.store_header02);
                iv2.setImageResource(R.drawable.store_info02);
                iv3.setImageResource(R.drawable.store_map02);
                iv4.setImageResource(R.drawable.store_photo02);
                iv5.setImageResource(R.drawable.store_review02);
                break;
            case 2:
                iv1.setImageResource(R.drawable.store_header03);
                iv2.setImageResource(R.drawable.store_info03);
                iv3.setImageResource(R.drawable.store_map03);
                iv4.setImageResource(R.drawable.store_photo03);
                iv5.setImageResource(R.drawable.store_review03);
                break;
            case 3:
                iv1.setImageResource(R.drawable.store_header04);
                iv2.setImageResource(R.drawable.store_info04);
                iv3.setImageResource(R.drawable.store_map04);
                iv4.setImageResource(R.drawable.store_photo04);
                iv5.setImageResource(R.drawable.store_review04);
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        SetPage();

        TextView tv3 = (TextView)findViewById(R.id.textView1);
        tv3.setText(DetailActivity.backbutoon);
        Typeface fontRobo = Typeface.createFromAsset(getAssets(), "fonts/notosanskrmedium.otf");
        tv3.setTypeface(fontRobo);
        tv3.setTextColor(Color.parseColor("#ffffff"));
        tv3.setTextSize(18);

        tv3.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isClickable()) {
                    finish();
                }
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
