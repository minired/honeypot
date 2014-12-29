package com.example.sj.todoapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Mainact extends ActionBarActivity {


    // 리스트뷰 선언
    private ListView listview;
    // 데이터를 연결할 Adapter
    DataAdapter adapter;

    // 데이터를 담을 자료구조
    ArrayList<CData> alist;


    // 리스트뷰 선언
    private ListView listview2;
    // 데이터를 연결할 Adapter
    DataAdapter adapter2;

    // 데이터를 담을 자료구조
    ArrayList<CData> alist2;


    // 리스트뷰 선언
    private ListView listview3;
    // 데이터를 연결할 Adapter
    DataAdapter2 adapter3;

    // 데이터를 담을 자료구조
    ArrayList<CData> alist3;



    // 리스트뷰 선언
    private ListView listview4;
    // 데이터를 연결할 Adapter
    DataAdapter3 adapter4;

    // 데이터를 담을 자료구조
    ArrayList<CData> alist4;


    private class DataAdapter extends ArrayAdapter<CData> {
        // 레이아웃 XML을 읽어들이기 위한 객체
        private LayoutInflater mInflater;

        public DataAdapter(Context context, ArrayList<CData> object) {

            // 상위 클래스의 초기화 과정
            // context, 0, 자료구조
            super(context, 0, object);
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        // 보여지는 스타일을 자신이 만든 xml로 보이기 위한 구문
        @Override
        public View getView(int position, View v, ViewGroup parent) {
            View view = null;

            // 현재 리스트의 하나의 항목에 보일 컨트롤 얻기

            if (v == null) {

                // XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
                view = mInflater.inflate(R.layout.listview_item, null);
            } else {

                view = v;
            }

            // 자료를 받는다.
            final CData data = this.getItem(position);

            if (data != null) {
                // 화면 출력


                ImageView iv = (ImageView) view.findViewById(R.id.imageView4);

                if(position%2 == 0)
                    iv.setBackgroundColor(Color.parseColor("#fff301"));
                else
                    iv.setBackgroundColor(Color.parseColor("#fff782"));

                // 이미지뷰에 뿌려질 해당 이미지값을 연결 즉 세번째 인수값
                iv.setImageResource(data.getData());

            }

            return view;

        }

    }



    private class DataAdapter2 extends ArrayAdapter<CData> {
        // 레이아웃 XML을 읽어들이기 위한 객체
        private LayoutInflater mInflater;

        public DataAdapter2(Context context, ArrayList<CData> object) {

            // 상위 클래스의 초기화 과정
            // context, 0, 자료구조
            super(context, 0, object);
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        // 보여지는 스타일을 자신이 만든 xml로 보이기 위한 구문
        @Override
        public View getView(int position, View v, ViewGroup parent) {
            View view = null;

            // 현재 리스트의 하나의 항목에 보일 컨트롤 얻기

            if (v == null) {

                // XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
                view = mInflater.inflate(R.layout.listview_item, null);
            } else {

                view = v;
            }

            // 자료를 받는다.
            final CData data = this.getItem(position);

            if (data != null) {
                // 화면 출력


                ImageView iv = (ImageView) view.findViewById(R.id.imageView4);

                if(position%2 == 0)
                    iv.setBackgroundColor(Color.parseColor("#2ea3f0"));
                else
                    iv.setBackgroundColor(Color.parseColor("#2ea3f0"));

                // 이미지뷰에 뿌려질 해당 이미지값을 연결 즉 세번째 인수값
                iv.setImageResource(data.getData());

            }

            return view;

        }

    }


    private class DataAdapter3 extends ArrayAdapter<CData> {
        // 레이아웃 XML을 읽어들이기 위한 객체
        private LayoutInflater mInflater;

        public DataAdapter3(Context context, ArrayList<CData> object) {

            // 상위 클래스의 초기화 과정
            // context, 0, 자료구조
            super(context, 0, object);
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        // 보여지는 스타일을 자신이 만든 xml로 보이기 위한 구문
        @Override
        public View getView(int position, View v, ViewGroup parent) {
            View view = null;

            // 현재 리스트의 하나의 항목에 보일 컨트롤 얻기

            if (v == null) {

                // XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
                view = mInflater.inflate(R.layout.listview_item2, null);
            } else {

                view = v;
            }

            // 자료를 받는다.
            final CData data = this.getItem(position);

            if (data != null) {
                // 화면 출력



                ImageView iv = (ImageView) view.findViewById(R.id.imageView6);

                if(position%2 == 0)
                    iv.setBackgroundColor(Color.parseColor("#2ea3f0"));
                else
                    iv.setBackgroundColor(Color.parseColor("#2ea3f0"));

                // 이미지뷰에 뿌려질 해당 이미지값을 연결 즉 세번째 인수값
                iv.setImageResource(data.getData());

            }

            return view;

        }

    }

    // CData안에 받은 값을 직접 할당

    class CData {

        private int m_szData;

        public CData(Context context, int p_szData) {
            m_szData = p_szData;

        }

        public int getData() {
            return m_szData;
        }
    }


    TabHost tabHost;


    private ArrayAdapter<CData> arrayAdapter ;
    private ListView listView ;

    private ArrayAdapter<CData> arrayAdapter2 ;
    private ListView listView2 ;

    private TabSpec spec1;
    private TabSpec spec2;
    private TabSpec spec3;
    private TabSpec spec4;


    private void SetActiveTextColor(int index)
    {
        txtTab1.setTextColor(Color.WHITE);
        txtTab2.setTextColor(Color.WHITE);
        txtTab3.setTextColor(Color.WHITE);
        txtTab4.setTextColor(Color.WHITE);
        switch(index) {
            case 1:
                txtTab1.setTextColor(Color.parseColor("#5d5d5d"));
                break;
            case 2:
                txtTab2.setTextColor(Color.parseColor("#5d5d5d"));
                break;
            case 3:
                txtTab3.setTextColor(Color.parseColor("#fef200"));
                break;
            case 4:
                txtTab4.setTextColor(Color.parseColor("#5d5d5d"));
                break;
        }
    }


    private void ChangeTabIcon(int index)
    {
        ImageView ivmain = (ImageView) findViewById(R.id.imageView2);
        switch(index) {
                case 1:
                    ivmain.setImageResource(R.drawable.main01);
                    break;
                case 2:
                    ivmain.setImageResource(R.drawable.main02);
                    break;
                case 3:
                    ivmain.setImageResource(R.drawable.main03);
                    break;
                case 4:
                    ivmain.setImageResource(R.drawable.main04);
                    break;

        }

    }

    private void ChagneTabColor(int index, TabHost host) {


        for(int i =1; i < 5; ++i) {
            if(i == index) {
                if(i==3)
                    tabHost.getTabWidget().getChildAt(i-1)
                            .setBackgroundColor(Color.parseColor("#2fa2f2"));
                else
                    tabHost.getTabWidget().getChildAt(i-1)
                            .setBackgroundColor(Color.parseColor("#fef200"));
            }
            else
                tabHost.getTabWidget().getChildAt(i-1)
                        .setBackgroundColor(Color.parseColor("#5d5d5d"));
        }
    }

    static TextView txtTab1;
    static TextView txtTab2;
    static TextView txtTab3;
    static TextView txtTab4;

    Boolean firstMapClick = false;

    private void SetFont()
    {
        txtTab1 = new TextView(this);
        txtTab1.setText("밥");
        //txtTab.setPadding(8, 9, 8, 9);
        txtTab1.setTextColor(Color.parseColor("#5d5d5d"));
        txtTab1.setTextSize(20);
        Typeface fontRobo = Typeface.createFromAsset(getAssets(), "fonts/notosanskrbold.otf");
        txtTab1.setTypeface(fontRobo);
        txtTab1.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        txtTab2 = new TextView(this);
        txtTab2.setText("스터디");
        //txtTab.setPadding(8, 9, 8, 9);
        txtTab2.setTextColor(Color.WHITE);
        txtTab2.setTextSize(20);
        txtTab2.setTypeface(fontRobo);
        txtTab2.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        txtTab3 = new TextView(this);
        txtTab3.setText("HOT");
        //txtTab.setPadding(8, 9, 8, 9);
        txtTab3.setTextColor(Color.WHITE);
        txtTab3.setTextSize(20);
        txtTab3.setTypeface(fontRobo);
        txtTab3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        txtTab4 = new TextView(this);
        txtTab4.setText("쿠폰");
        //txtTab.setPadding(8, 9, 8, 9);
        txtTab4.setTextColor(Color.WHITE);
        txtTab4.setTextSize(20);
        txtTab4.setTypeface(fontRobo);
        txtTab4.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

    }


    void SetBannerfont()
    {
        TextView btn = (TextView)findViewById(R.id.textView4);
        Typeface fontRobo = Typeface.createFromAsset(getAssets(), "fonts/notosanskrbold.otf");
        btn.setTypeface(fontRobo);
        btn.setTextColor(Color.parseColor("#fff200"));
        btn.setTextSize(19);
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        String currentDateandTime = sdf.format(new Date());

        String hour = currentDateandTime.substring(0,2);
        String minute = currentDateandTime.substring(2,4);

        String DayName = "";

        int h = Integer.parseInt(hour);
        if(h < 5)
            DayName = "새벽";
        else if (h<12)
            DayName = "오전";
        else if (h<16)
            DayName = "낮";
        else if (h < 20)
            DayName = "저녁";
        else if (h < 24)
            DayName = "밤";

        if(h > 12) h = h - 12;


        hour = Integer.toString(h);

        btn.setText(DayName +" " + hour+"시 "+minute + "분");


        TextView btn2 = (TextView)findViewById(R.id.textView5);
        btn2.setTypeface(fontRobo);
        btn2.setTextColor(Color.parseColor("#ffffff"));
        btn2.setTextSize(33);


        TextView btn3 = (TextView)findViewById(R.id.textView10);
        btn3.setTypeface(fontRobo);
        btn3.setTextColor(Color.parseColor("#ffffff"));
        btn3.setTextSize(15);




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        setContentView(R.layout.activity_mainact);




        SetBannerfont();
        SetFont();
        tabHost=(TabHost)findViewById(R.id.tabHost2);
        tabHost.setup();
        Resources res = getResources();
        spec1=tabHost.newTabSpec("1");
        spec1.setContent(R.id.linearLayout);
        spec1.setIndicator(txtTab1);


        spec2=tabHost.newTabSpec("2");
        spec2.setIndicator(txtTab2);
        spec2.setContent(R.id.linearLayout2);


        spec3=tabHost.newTabSpec("3");
        spec3.setContent(R.id.linearLayout3);
        spec3.setIndicator(txtTab3);

        spec4=tabHost.newTabSpec("4");
        spec4.setContent(R.id.linearLayout4);
        spec4.setIndicator(txtTab4);

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);
        tabHost.addTab(spec4);

        tabHost.getTabWidget().getChildAt(0)
                .setBackgroundColor(Color.parseColor("#fef200"));
        tabHost.getTabWidget().getChildAt(1)
                .setBackgroundColor(Color.parseColor("#5d5d5d"));
        tabHost.getTabWidget().getChildAt(2)
                .setBackgroundColor(Color.parseColor("#5d5d5d"));
        tabHost.getTabWidget().getChildAt(3)
                .setBackgroundColor(Color.parseColor("#5d5d5d"));

        tabHost.getTabWidget().getChildAt(0).setPadding(0,0,0,0);
        tabHost.getTabWidget().getChildAt(1).setPadding(0,0,0,0);
        tabHost.getTabWidget().getChildAt(2).setPadding(0,0,0,0);
        tabHost.getTabWidget().getChildAt(3).setPadding(0,0,0,0);
        ///// tabhost click event
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                ChagneTabColor(Integer.parseInt(tabId), tabHost);
                ChangeTabIcon(Integer.parseInt(tabId));
                SetActiveTextColor(Integer.parseInt(tabId));


            }
        });

        ///////
        /*
        // 선언한 리스트뷰에 사용할 리스뷰연결
        listview = (ListView) findViewById(R.id.listView);
        // 객체를 생성합니다
        alist = new ArrayList<CData>();

        // 데이터를 받기위해 데이터어댑터 객체 선언
        adapter = new DataAdapter(this, alist);

        // 리스트뷰에 어댑터 연결
        listview.setAdapter(adapter);
        // ArrayAdapter를 통해서 ArrayList에 자료 저장
        // 하나의 String값을 저장하던 것을 CData클래스의 객체를 저장하던것으로 변경
        // CData 객체는 생성자에 리스트 표시 텍스트뷰1의 내용과 텍스트뷰2의 내용 그리고 사진이미지값을 어댑터에 연결

        // CData클래스를 만들때의 순서대로 해당 인수값을 입력
        // 한줄 한줄이 리스트뷰에 뿌려질 한줄 한줄이라고 생각하면 됩니다.
        adapter.add(new CData(getApplicationContext(), R.drawable.bab01));
        adapter.add(new CData(getApplicationContext(), R.drawable.bab02));





        // 선언한 리스트뷰에 사용할 리스뷰연결
        listview2 = (ListView) findViewById(R.id.listView2);
        // 객체를 생성합니다
        alist2 = new ArrayList<CData>();

        // 데이터를 받기위해 데이터어댑터 객체 선언
        adapter2 = new DataAdapter(this, alist2);

        // 리스트뷰에 어댑터 연결
        listview2.setAdapter(adapter2);
        // ArrayAdapter를 통해서 ArrayList에 자료 저장
        // 하나의 String값을 저장하던 것을 CData클래스의 객체를 저장하던것으로 변경
        // CData 객체는 생성자에 리스트 표시 텍스트뷰1의 내용과 텍스트뷰2의 내용 그리고 사진이미지값을 어댑터에 연결

        // CData클래스를 만들때의 순서대로 해당 인수값을 입력
        // 한줄 한줄이 리스트뷰에 뿌려질 한줄 한줄이라고 생각하면 됩니다.
        adapter2.add(new CData(getApplicationContext(), R.drawable.study01));
        adapter2.add(new CData(getApplicationContext(), R.drawable.study02));



        //////////////


        // 선언한 리스트뷰에 사용할 리스뷰연결
        listview3 = (ListView) findViewById(R.id.listView3);
        // 객체를 생성합니다
        alist3 = new ArrayList<CData>();

        // 데이터를 받기위해 데이터어댑터 객체 선언
        adapter3 = new DataAdapter2(this, alist3);

        // 리스트뷰에 어댑터 연결
        listview3.setAdapter(adapter3);
        // ArrayAdapter를 통해서 ArrayList에 자료 저장
        // 하나의 String값을 저장하던 것을 CData클래스의 객체를 저장하던것으로 변경
        // CData 객체는 생성자에 리스트 표시 텍스트뷰1의 내용과 텍스트뷰2의 내용 그리고 사진이미지값을 어댑터에 연결

        // CData클래스를 만들때의 순서대로 해당 인수값을 입력
        // 한줄 한줄이 리스트뷰에 뿌려질 한줄 한줄이라고 생각하면 됩니다.
        adapter3.add(new CData(getApplicationContext(), R.drawable.t3ba1));
        adapter3.add(new CData(getApplicationContext(), R.drawable.t3ba2));
        adapter3.add(new CData(getApplicationContext(), R.drawable.t3ba3));
*/
/*
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                // TODO 아이템 클릭시에 구현할 내용은 여기에.
                if(firstMapClick) return;
                firstMapClick = true;

                DetailActivity.backbutoon = "내 주위 밥집 리스트";
                Intent intentSubActivity = new Intent(Mainact.this, MapActivity.class );
                startActivity(intentSubActivity);
                firstMapClick = false;
            }
        });

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                // TODO 아이템 클릭시에 구현할 내용은 여기에.
                if(firstMapClick) return;
                firstMapClick = true;

                DetailActivity.backbutoon = "500m내에 스터디 공간";
                Intent intentSubActivity = new Intent(Mainact.this, MapActivity.class );
                startActivity(intentSubActivity);
                firstMapClick = false;
            }
        });

        listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                // TODO 아이템 클릭시에 구현할 내용은 여기에.
                if(firstMapClick) return;
                firstMapClick = true;

                DetailActivity.backbutoon = "500m내에 스터디 공간";
                Intent intentSubActivity = new Intent(Mainact.this, PopActivity.class );
                startActivity(intentSubActivity);
                firstMapClick = false;
            }
        });
        */

        ImageView btn1 = (ImageView)findViewById(R.id.imageView20);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MapActivity.Title = "12시인데\n밥은 먹고 스터디 가자";
                DetailActivity.backbutoon = "500m내에 스터디 공간";
                Intent intentSubActivity = new Intent(Mainact.this, MapActivity.class );
                startActivity(intentSubActivity);

            }
        });
        ImageView btn2 = (ImageView)findViewById(R.id.imageView21);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapActivity.Title = "강남역에서\n가장 가까운 김밥집 대공개";
                DetailActivity.backbutoon = "500m내에 스터디 공간";
                Intent intentSubActivity = new Intent(Mainact.this, MapActivity.class );
                startActivity(intentSubActivity);

            }
        });
        ImageView btn3 = (ImageView)findViewById(R.id.imageView22);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapActivity.Title = "이 노트북,\n3분 내로 내려놓을께요";
                DetailActivity.backbutoon = "500m내에 스터디 공간";
                Intent intentSubActivity = new Intent(Mainact.this, MapActivity.class );
                startActivity(intentSubActivity);

            }
        });
        ImageView btn4 = (ImageView)findViewById(R.id.imageView23);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapActivity.Title = "열명 이상\n때거지로 가도 받아줄거니";
                DetailActivity.backbutoon = "500m내에 스터디 공간";
                Intent intentSubActivity = new Intent(Mainact.this, MapActivity.class );
                startActivity(intentSubActivity);

            }
        });
        ImageView btn5 = (ImageView)findViewById(R.id.imageView24);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // DetailActivity.backbutoon = "500m내에 스터디 공간";
                Intent intentSubActivity = new Intent(Mainact.this, DetailActivity2.class );
                startActivity(intentSubActivity);

            }
        });
        ImageView btn6 = (ImageView)findViewById(R.id.imageView25);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DetailActivity.backbutoon = "500m내에 스터디 공간";
                Intent intentSubActivity = new Intent(Mainact.this, DetailActivity2.class );
                startActivity(intentSubActivity);
            }
        });

        ImageView btn7 = (ImageView)findViewById(R.id.imageView26);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DetailActivity.backbutoon = "500m내에 스터디 공간";
                Intent intentSubActivity = new Intent(Mainact.this, DetailActivity2.class );
                startActivity(intentSubActivity);
            }
        });



        ScrollView sv = (ScrollView) findViewById(R.id.scrollView2);
        ((ScrollView) findViewById(R.id.scrollView2)).post(new Runnable() {
            public void run() {
                ((ScrollView) findViewById(R.id.scrollView2)).fullScroll(View.FOCUS_UP);
            }
        });
        sv.scrollTo(0, 0);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mainact, menu);
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
