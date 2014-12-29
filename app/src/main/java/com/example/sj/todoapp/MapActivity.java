package com.example.sj.todoapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/////

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.NMapView.OnMapStateChangeListener;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager.OnCalloutOverlayListener;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapResourceProvider;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class MapActivity extends NMapActivity implements NMapView.OnMapStateChangeListener, NMapView.OnMapViewTouchEventListener {

    public static  final String API_KEY = "e51c33527bc494097afac405209c17a9";
    NMapView mMapView = null;
    NMapController mMapController = null;
    FrameLayout MapContainer;


    public static String Title = "이 노트북,\n3분 내로 내려놓을께요";

    //// geocode
    String lat = "";  //위도
    String lon = "";  //경도
    String murl = ""; //연결할 URL
    String mapxml = ""; //받아온 xml
    NMapResourceProvider mMapViewerResourceProvider;
    NMapOverlayManager mOverlayManager;
    String addr_kor = "에이블스퀘어 조용히 노트북 하기 좋은 공간";
    String addr_eng = "";
    ///////////////////////////////////// api
    ///////////////////

    private ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

        @Override
        public String handleResponse(HttpResponse response) throws IOException {

            int status = response.getStatusLine().getStatusCode(); // HTTP 상태코드

            if (status == HttpStatus.SC_OK) { // 200 인경우 성공
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                ClientProtocolException e = new ClientProtocolException("Unexpected response status: " + status);
                throw e; // 상태코드 200이 아닌경우 예외발생
            }

        }

    };

    String MyLat = "";
    String MyLon = "";
    String MyArea = "";
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

            String targetUrl = "https://apis.sktelecom.com/v1/zonepoi/pois";

            String responseBody = null;
            HttpClient httpclient = new DefaultHttpClient();

            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>(2);
                params.add(new BasicNameValuePair("latitude","37.4993235"));
                params.add(new BasicNameValuePair("longitude","127.0285745"));

                String queryParams = URLEncodedUtils.format(params, HTTP.UTF_8); //Parameter 구성 (인코딩 UTF-8)

                URI uri = new URI(targetUrl + "?" + queryParams); //ex) http://test.com/test.do?id=1 형태 구성
                HttpGet httpget = new HttpGet(uri);
                // 헤더 값 셋팅
                httpget.setHeader("TDCProjectKey", "9d57e48e-89d2-4779-8d1b-5a05ee05ff0c");
                httpget.setHeader("Content-Type", "application/json; charset=UTF-8");
                responseBody = httpclient.execute(httpget, responseHandler);

                String LatStr = "\"latitude\":";
                String LonStr = "\"longitude\":";
                String AreaStr = "\"addr\":\"";
                String AreaEndStr = "\",\"tel";

                int latindex = responseBody.indexOf(LatStr);
                int lonindex = responseBody.indexOf(LonStr);

                int AreaStartindex = responseBody.indexOf(AreaStr);
                int AreaEndindex = responseBody.indexOf(AreaEndStr);

                MyArea = responseBody.substring(AreaStartindex+AreaStr.length(), AreaEndindex);

                if(latindex != -1)
                {
                    MyLat = responseBody.substring(latindex+LatStr.length(), latindex+LatStr.length()+10);

                }
                if(lonindex != -1)
                {
                    MyLon = responseBody.substring(lonindex+LonStr.length(), lonindex+LonStr.length()+10);

                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }





            JSONObject jsonObj = new JSONObject();

            //요청할 json DATA 샘플
            // {"name":"kim", "age":18, "addr":"서울시 을지로 11번가 T 타워"}



            //jsonObj.put("name", "kim");
            //jsonObj.put("age", 18);
            //jsonObj.put("addr", "서울시 을지로 11번가 T 타워 ");


            // 헤더에 포함 시킬 value
            String MDN = "01011112222";
            String Token="1234567890";

            //post 방식일때
            //restTest network = new restTest();
            //String responceBody = network.requestPost(targetUrl, MDN, Token,  jsonObj);


        }


    };


    //////////////////////////////////// api




    void getGeocode()
    {

        murl = "http://openapi.map.naver.com/api/geocode.php?key=90febb6f08833b41bb448afea2b8d816&encoding=utf-8&coord=LatLng&query=";
//murl = "http://openapi.map.naver.com/api/geocode.php?key=90febb6f08833b41bb448afea2b8d816&encoding=utf-8&coord=WGS84&query=";

        try {
            String addr = URLEncoder.encode(addr_kor, "UTF-8");
            URL mapXmlUrl = new URL(murl+addr);  //URL연결하고 받아오고 하는 부분들은 import가 필요하다. java.net.*
            HttpURLConnection urlConn = (HttpURLConnection) mapXmlUrl.openConnection();
            urlConn.setDoOutput(true);
            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("charset", "utf-8");

            int len = urlConn.getContentLength();  //받아오는 xml의 길이

            if (len > 0) {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                String inputLine = "";
                while ((inputLine = br.readLine()) != null) {
                    mapxml += inputLine;  //한글자씩 읽어옵니다
                }
                if (mapxml != null) {
                    if (mapxml.indexOf("</item>") > -1) {   //item이 있으면 좌표를 받아와야지
                        int first = 1;
                        lon = mapxml.substring(mapxml.indexOf("<x>") + 3, mapxml.indexOf("</x>")); //경도 잘라오기
                        lat = mapxml.substring(mapxml.indexOf("<y>") + 3, mapxml.indexOf("</y>")); //위도 잘라오기
                        //Toast.makeText(getApplicationContext(), lon + "-" + lat, Toast.LENGTH_SHORT).show();
                    }
                }

                br.close();  //버퍼리더 닫기
            }
        }
        catch (Exception e)
        {


        }
    }
    ///

    private void setaddr()
    {
        addr_kor = "서울특별시 강남구 역삼동 819-1";//MyActivity3.Addr_kor;
        //addr_eng = MyActivity3.Addr_eng;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);



        /////
        if(android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mRunnable.run();
        ////



        TextView tv2 = (TextView)findViewById(R.id.textView2);

        TextView btn = (TextView)findViewById(R.id.textView);
        Typeface fontRobo = Typeface.createFromAsset(getAssets(), "fonts/notosanskrmedium.otf");
        btn.setTypeface(fontRobo);
        btn.setTextColor(Color.parseColor("#000000"));
        btn.setTextSize(21);

        tv2.setTypeface(fontRobo);
        tv2.setTextColor(Color.parseColor("#000000"));
        tv2.setTextSize(13);

        TextView tv3 = (TextView)findViewById(R.id.textView3);
        tv3.setTypeface(fontRobo);
        tv3.setTextColor(Color.parseColor("#000000"));
        tv3.setTextSize(15);

        //////////
        btn.setText(MapActivity.Title);
        //////////



        btn.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isClickable()) {
              finish();
                }
            }

        });

        tv3.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isClickable()) {
                    Intent intentSubActivity = new Intent(MapActivity.this, PopActivity.class );
                    startActivity(intentSubActivity);
                }
            }

        });






// 네이버 지도를 넣기 위한 LinearLayout 컴포넌트
        MapContainer = (FrameLayout) findViewById(R.id.FrameLayoutMap);

// 네이버 지도 객체 생성
        mMapView = new NMapView(this);
        //mMapView = (NMapView)findViewById(R.id.mapView);

// 지도 객체로부터 컨트롤러 추출
        mMapController = mMapView.getMapController();

// 네이버 지도 객체에 APIKEY 지정
        mMapView.setApiKey(API_KEY);

// 생성된 네이버 지도 객체를 LinearLayout에 추가시킨다.
        MapContainer.addView(mMapView);

// 지도를 터치할 수 있도록 옵션 활성화
        mMapView.setClickable(true);

// use map controller to zoom in/out, pan and set map center, zoom level etc.
        mMapController = mMapView.getMapController();

        lon = "127.0285590";
        lat = "37.4992480";
        //Exception
        if (addr_kor == "" || lon == "" || lat == ""){
            Toast.makeText(getApplicationContext(), "Sorry, It can't find the map information.", Toast.LENGTH_SHORT).show();
            return;
        }


        //mMapController.setMapCenter(new NGeoPoint(Double.parseDouble(lon), Double.parseDouble(lat)), 11);

        try {

            //mMapController.setMapCenter(new NGeoPoint(126.978371, 34.5666091), 11);
            //Toast.makeText(getApplicationContext(), lon + "-" + lat, Toast.LENGTH_SHORT).show();
            mMapController.setMapCenter(new NGeoPoint(Float.parseFloat(lon), Float.parseFloat(lat)), 11);

            //create a NMapOverlayManager
            mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
            mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);

            // 오버레이들을 관리하기 위한 id값 생성
            int markerId = NMapPOIflagType.PIN;

            // 표시할 위치 데이터를 지정한다. -- 마지막 인자가 오버레이를 인식하기 위한 id값
            NMapPOIdata poiData = new NMapPOIdata(0, mMapViewerResourceProvider);//1 >0
            poiData.beginPOIdata(0);//초기값 : 2

            //세종
            poiData.addPOIitem(Float.parseFloat(lon), Float.parseFloat(lat), addr_kor, markerId, 0);


            //poiData.addPOIitem(127.061, 37.51, "위치2", markerId, 0); < 초기값. 지우지 말것 !!
            poiData.endPOIdata();


            //////////////////////
            poiData.addPOIitem(Float.parseFloat("127.0285160"), Float.parseFloat("37.4986080"), "와라와라 점심에는 술이 아닌 밥을 파는 이자카야", markerId, 1);
            poiData.addPOIitem(Float.parseFloat("127.0265130"), Float.parseFloat("37.5023960"), "가로비 정성이 넘치는 재료에 싼 가격! 채식뷔페 최강자", markerId, 1);
            poiData.addPOIitem(Float.parseFloat("127.0296080"), Float.parseFloat("37.4972890"), "아이디어팩토리 넓은 테이블을 갖춘 코워킹 스페이스", markerId, 1);
           if(MyLon != "" && MyLat != "")
               poiData.addPOIitem(Float.parseFloat(MyLon), Float.parseFloat(MyLat),"이 곳은 "+ MyArea +"입니다", markerId, 1); // my position

            /////////

            // 위치 데이터를 사용하여 오버레이 생성
            NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);

            mOverlayManager.createPOIdataOverlay(poiData, null);

            // id값이 0으로 지정된 모든 오버레이가 표시되고 있는 위치로 지도의 중심과 ZOOM을 재설정
            poiDataOverlay.showAllPOIdata(12); //0
        }
        catch (Exception e)
        {

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
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

    ////map

    @Override
    public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
        if (nMapError == null) { // success
            //mMapController.setMapCenter(new NGeoPoint(116.978371, 37.5666091), 11);
            //  mMapController.setMapCenter(
            //        new NGeoPoint(Double.parseDouble(lat), Double.parseDouble(lon)), 11);
        } else { // fail
            //android.util.Log.e("NMAP", "onMapInitHandler: error="
            //      + nMapError.toString());
        }
        // mMapController.setMapCenter(new NGeoPoint(Double.parseDouble(lon), Double.parseDouble(lat)), 11);
    }

    @Override
    public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

    }

    @Override
    public void onMapCenterChangeFine(NMapView nMapView) {

    }

    @Override
    public void onZoomLevelChange(NMapView nMapView, int i) {

    }

    @Override
    public void onAnimationStateChange(NMapView nMapView, int i, int i2) {

    }

    @Override
    public void onLongPress(NMapView nMapView, MotionEvent motionEvent) {

    }

    @Override
    public void onLongPressCanceled(NMapView nMapView) {

    }

    @Override
    public void onTouchDown(NMapView nMapView, MotionEvent motionEvent) {

    }

    @Override
    public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {

    }

    @Override
    public void onScroll(NMapView nMapView, MotionEvent motionEvent, MotionEvent motionEvent2) {

    }

    @Override
    public void onSingleTapUp(NMapView nMapView, MotionEvent motionEvent) {

    }

}
