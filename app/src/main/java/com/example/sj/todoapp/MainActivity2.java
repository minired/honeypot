package com.example.sj.todoapp;

import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.skt.geofence.AppData;
import com.skt.geofence.GeoFenceSyncState;
import com.skt.geofence.agent.service.AgentManager;
import com.skt.geofence.agent.service.AgentManager.GeoFenceAgentException;
import com.skt.geofence.agent.service.GeoFenceAgentListener;


///////////////////////////////////
import com.example.sj.todoapp.R;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;



public class MainActivity2 extends ActionBarActivity {



    private static Button mBtnAdd,mBtnGet,mBtnGetAll,mBtnRemove,mBtnUpdate,mBtnSyncDB,mBrnTutorial,mBrnCheckEnviroment;
    private static boolean onServiceConnected_flag = false;
    private static EditText mLat,mLon;

    static AgentManager mAgentManager = null;
    private static RadioGroup mRdg;
    private String mPakacgeName;
    private Context mContext;
    private int mIndex = 1;
    private String TAG = "Todoapp";
    JSONStringer jParser = new JSONStringer();

    //private TextView get_id_text,get_text;

    private static boolean add_store_group_flag = false;
    private static boolean add_store_flag = false;
    private static boolean add_event_group_flag = false;
    private static boolean add_event_flag = false;

    private static boolean get_store_group_flag = false;
    private static boolean get_store_flag = false;
    private static boolean get_event_group_flag = false;
    private static boolean get_event_flag = false;

    private static String Store_Group_ID = null;
    private static String Store_ID = null;
    private static String Event_Group_ID = null;
    private static String Event_ID = null;

    private static String latitude = null, Longitude = null;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        if(android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mRunnable.run();


        mContext = this;
        mPakacgeName = this.getClass().getPackage().getName();

        findViewById(R.id.add_api).setOnClickListener(mClickListener);

        mBtnAdd = (Button) findViewById(R.id.add_api);
        mBtnAdd.setOnClickListener(mClickListener);

        mBtnGet = (Button) findViewById(R.id.get_api);
        mBtnGet.setOnClickListener(mClickListener);

        mBtnGetAll = (Button) findViewById(R.id.get_all_api);
        mBtnGetAll.setOnClickListener(mClickListener);

        mBtnRemove = (Button) findViewById(R.id.delete_api);
        mBtnRemove.setOnClickListener(mClickListener);

        mBtnUpdate = (Button) findViewById(R.id.update_api);
        mBtnUpdate.setOnClickListener(mClickListener);

        mBtnSyncDB = (Button) findViewById(R.id.sync_db);
        mBtnSyncDB.setOnClickListener(mClickListener);

        mBrnTutorial = (Button) findViewById(R.id.tutorial);
        //mBrnTutorial.setOnClickListener(mClickListener);

        mBrnCheckEnviroment = (Button) findViewById(R.id.check_sample);
        //mBrnCheckEnviroment.setOnClickListener(mClickListener);

        mLat = (EditText) findViewById(R.id.latitude);
        mLon = (EditText) findViewById(R.id.longitude);

        mLat.setVisibility(View.GONE);
        mLon.setVisibility(View.GONE);

        mRdg = (RadioGroup) this.findViewById(R.id.rdo_poc);
        mRdg.setOnCheckedChangeListener(mCheckedChangeListener);

        mLat.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
                latitude = mLat.getText().toString();
            }
        });
        mLon.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
                Longitude = mLon.getText().toString();
            }
        });

        Toast.makeText(getApplicationContext(), "잠시만 기다리세요.초기화중입니다 ", Toast.LENGTH_SHORT).show();

        beforeOnconnect();

        if (mAgentManager == null) {
            mAgentManager = AgentManager.getInstance();
        }

        if (mAgentManager != null) {
            try {
                mAgentManager.initialize(this, mAgentListener);
            } catch (GeoFenceAgentException e) {
                e.printStackTrace();
            }
        }









    }

    @Override
    protected void onResume() {

        super.onResume();
    }


    public void beforeOnconnect() {

        add_setflag_currnet_action(10);//flag clear

        mRdg.setEnabled(false);
        mBtnAdd.setEnabled(false);
        mBtnGet.setEnabled(false);
        mBtnGetAll.setEnabled(false);
        mBtnRemove.setEnabled(false);
        mBtnUpdate.setEnabled(false);

        mLat.setVisibility(View.GONE);
        mLon.setVisibility(View.GONE);

        mBrnTutorial.setEnabled(false);
        mBtnSyncDB.setEnabled(false);
        //mBrnCheckEnviroment.setEnabled(true);
    }
    public void beforeTutorial() {

        add_setflag_currnet_action(10);//flag clear

        mRdg.setEnabled(false);
        mBtnAdd.setEnabled(false);
        mBtnGet.setEnabled(false);
        mBtnGetAll.setEnabled(false);
        mBtnRemove.setEnabled(false);
        mBtnUpdate.setEnabled(false);

        mLat.setVisibility(View.GONE);
        mLon.setVisibility(View.GONE);

        mBrnTutorial.setEnabled(true);
        mBtnSyncDB.setEnabled(true);
        mBrnCheckEnviroment.setEnabled(false);
    }

    public boolean validate_sample_sate(){
        //data available 확인
        if ( ! isOnline()){
            Toast.makeText(getApplicationContext(), "네크웍이 연결되지 않았습니다. 확인후 다시 시도하세요 ", Toast.LENGTH_SHORT).show();
            return false;
        }


        // 와이파이 ON/OFF확인
        //if(! isWifiTurnON()){
        //	 Toast.makeText(getApplicationContext(), "WIFI 가 ON 되지 않았습니다. On 후 다시 시도하세요 ", Toast.LENGTH_SHORT).show();
        //	 return false;
        //}

        Log.d(TAG,"GeofenceService apk install check");
        //GeofenceService apk 가 install 되어있는지 확인
        if ( ! (isServiceRunning("com.skt.geofence.GeoFenceAgent")
                &&	isServiceRunning("com.skt.geofence.GeoFenceService")
        )){
            Toast.makeText(getApplicationContext(), "GeoFenceService 가 인스톨되지 않았습니다.", Toast.LENGTH_LONG).show();
            return false;
        }

        //Log.d(TAG,"just test apk install check");
        //int statusCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        // if (statusCode == ConnectionResult.SUCCESS) {
        //OK
        // }

        Log.d(TAG,"GooglePlayService apk install check");
        //타사폰 or simcard 없는 SKT 폰 or WIFI-only 폰의 경우 Google service player 가 install 되어있는지 확인
        if( !checkSktImsi() ) { //타사폰 or simcard 없는 SKT 폰 or WIFI-only 폰
            /*
            final int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
            if (result != ConnectionResult.SUCCESS) {
                Toast.makeText(getApplicationContext(), "Google Play service 가 인스톨되지 않았습니다.", Toast.LENGTH_LONG).show();
                //Log.d(TAG,"This device has no Google Play service! please check the device OS version");
                return false;
            }
            */
        }

        if ( onServiceConnected_flag == false ){
            Toast.makeText(getApplicationContext(), "GeoFense Service 연결이 아직 되지 않았습니다. 조금후에 다시 시도해보세요." +
                    "만약 계속 연결이 되지 않는다면 리부팅을 하세요", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    protected boolean checkSktImsi() {
        TelephonyManager mTelManager;
        mTelManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);

        String imsi;

        if (mTelManager == null) {
            imsi = "0";
        } else {
            imsi = mTelManager.getSubscriberId();
            if (imsi == null) {
                imsi = "0";
            }
        }

        boolean isSktImsi = imsi.startsWith("45005");

        if (isSktImsi == true) {
            return true;
        } else {
            return false;
        }
    }

    //service available check
    public Boolean isServiceRunning(String serviceName) {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (RunningServiceInfo runningServiceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {

            Log.d(TAG,runningServiceInfo.service.getClassName());
            if (serviceName.equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    public void check_state_sample(View v) {

        //Log.d("check_environment","check_environment");

        //WifiManager manager = (WifiManager) getSystemService(mContext.WIFI_SERVICE);

        Log.d("check_environment","check_environment");
        Toast.makeText(getApplicationContext(), "서비스 check 를 시작합니다. ", Toast.LENGTH_SHORT).show();


        if ( validate_sample_sate() )
        {
            Toast.makeText(getApplicationContext(), "서비스가 연결되었습니다. 튜토리얼을 시작하시기 바랍니다. ", Toast.LENGTH_LONG).show();
            beforeTutorial();
        }
        else

            //Toast.makeText(getApplicationContext(), "check config", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "준비가 되지 않았습니다. 설정을 확인하시기 바랍니다.", Toast.LENGTH_LONG).show();

    }
    private boolean isOnline() { // network 연결 상태 확인 check
        ConnectivityManager cManager;
        NetworkInfo mobile;
        NetworkInfo wifi;

        //Log.d("isOnline","");
        cManager=(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        mobile = cManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        wifi = cManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if(mobile.isConnected() || wifi.isConnected())
        {
            Log.d("return true","");
            return true;
        }
        Log.d("return false","");
        return false;
    }
    /*
    private boolean isWifiTurnON() { //  WIFI turn on check
             ConnectivityManager cManager;
            NetworkInfo wifi;

            Log.d("isWifiTurnON","isWifiTurnON");
            cManager=(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
            wifi = cManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if( wifi.isAvailable())
            {
                Log.d("return true","");
                return true;
            }
            else
                Log.d("return false","");

            return true;
    }*/
    public void startTutorial_StartGet(View v) {
        //mRdg.check(R.id.rdoStoreGroup);
        mRdg.setEnabled(true);
        changeRadioToStoreGroup();

        mBtnAdd.setEnabled(true);
        mBtnGet.setEnabled(false);
        mBtnGetAll.setEnabled(false);
        mBtnRemove.setEnabled(false);
        mBtnUpdate.setEnabled(false);

        mLat.setVisibility(View.GONE);
        mLon.setVisibility(View.GONE);

        mBrnTutorial.setEnabled(false);
        mBtnSyncDB.setEnabled(true);
        mBrnCheckEnviroment.setEnabled(false);


        ////////////////////
        mBtnGet.setEnabled(true);
        mBtnGetAll.setEnabled(true);

        mAgentListener.onServiceConnected();
    }
    public void startGet(){
        //mRdg.check(R.id.rdoStoreGroup);
        mRdg.setEnabled(true);
        changeRadioToStoreGroup();

        mBtnAdd.setEnabled(false);
        mBtnGet.setEnabled(true);
        mBtnGetAll.setEnabled(false);
        mBtnRemove.setEnabled(false);
        mBtnUpdate.setEnabled(false);

        mLat.setVisibility(View.GONE);
        mLon.setVisibility(View.GONE);

        mBrnTutorial.setEnabled(false);
        mBtnSyncDB.setEnabled(true);
        mBrnCheckEnviroment.setEnabled(false);
    }
    public void startGetAll(){
        mRdg.setEnabled(true);
        changeRadioToStoreGroup();

        mBtnAdd.setEnabled(false);
        mBtnGet.setEnabled(false);
        mBtnGetAll.setEnabled(true);
        mBtnRemove.setEnabled(false);
        mBtnUpdate.setEnabled(false);

        mLat.setVisibility(View.GONE);
        mLon.setVisibility(View.GONE);

        mBrnTutorial.setEnabled(false);
        mBtnSyncDB.setEnabled(true);
        mBrnCheckEnviroment.setEnabled(false);
    }
    public void startUpdate(){
        mRdg.setEnabled(true);
        changeRadioToStoreGroup();

        mBtnAdd.setEnabled(false);
        mBtnGet.setEnabled(false);
        mBtnGetAll.setEnabled(false);
        mBtnRemove.setEnabled(false);
        mBtnUpdate.setEnabled(true);

        mLat.setVisibility(View.GONE);
        mLon.setVisibility(View.GONE);

        mBrnTutorial.setEnabled(false);
        mBtnSyncDB.setEnabled(true);
        mBrnCheckEnviroment.setEnabled(false);
    }
    public void startDelete(){
        mRdg.setEnabled(true);
        changeRadioToStore();

        mBtnAdd.setEnabled(false);
        mBtnGet.setEnabled(false);
        mBtnGetAll.setEnabled(false);
        mBtnRemove.setEnabled(true);
        mBtnUpdate.setEnabled(false);

        mLat.setVisibility(View.GONE);
        mLon.setVisibility(View.GONE);

        mBrnTutorial.setEnabled(false);
        mBtnSyncDB.setEnabled(true);
        mBrnCheckEnviroment.setEnabled(false);
    }
    public void finishTutorial(){

        mRdg.setEnabled(false);
        mBtnAdd.setEnabled(false);
        mBtnGet.setEnabled(false);
        mBtnGetAll.setEnabled(false);
        mBtnRemove.setEnabled(false);
        mBtnUpdate.setEnabled(false);

        mLat.setVisibility(View.GONE);
        mLon.setVisibility(View.GONE);

        mBrnTutorial.setEnabled(true);
        mBtnSyncDB.setEnabled(true);
        mBrnCheckEnviroment.setEnabled(false);
    }

    public void changeRadioToStoreGroup(){
        mRdg.check(R.id.rdoStoreGroup);
    }

    public void changeRadioToStore(){
        mRdg.check(R.id.rdoStore);
    }

    public void changeRadioToEventGroup(){
        mRdg.check(R.id.rdoEventGroup);
    }

    public void changeRadioToEvent(){
        mRdg.check(R.id.rdoEvent);
    }

    private GeoFenceAgentListener mAgentListener = new GeoFenceAgentListener() {

        @Override
        public void onError(int errCode, String errMsg) {
            Log.d(TAG, "onError errCode = " + errCode);
            Log.d(TAG, "onError errMsg = " + errMsg);
        }

        @Override
        public void onResponseReceived(int reusltCode, String resultData) {

            //get_id_text = (EditText) findViewById(R.id.result_add_id);
            //get_text = (EditText) findViewById(R.id.guide_text_id);
            if ( resultData == null)
            {
                Log.d(TAG, "resultData is null...please check your option");
            }
            else{

                final String return_code = String.valueOf(reusltCode);
                final String return_data = resultData;
                if ( reusltCode == 200 ){
                    new Thread() {
                        @Override
                        public void run() {
                            Looper.prepare();
                            Toast.makeText(getApplicationContext(), "성공입니다 : "+String.valueOf(return_code)+":"+return_data, Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                    }.start();
                }	else{
                    new Thread() {
                        @Override
                        public void run() {
                            Looper.prepare();
                            Toast.makeText(getApplicationContext(), "실패입니다 : "+String.valueOf(return_code)+":"+return_data, Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                    }.start();
                    return;
                }

                Log.d(TAG, "onResponseReceived reusltCode = " + reusltCode);
                Log.d(TAG, "onResponseReceived resultData = " + resultData);

                try
                {
                    //result_id = (EditText) findViewById(R.id.result_add_id);
                    JSONObject json = new JSONObject(resultData);
                    String result_id    = json.getString("data");
                    Log.d("result_id == ",result_id);


                    if (add_store_group_flag == true){
                        Store_Group_ID = result_id;
                    }else if (add_store_flag == true){
                        Store_ID = result_id;
                    }else if ( add_event_group_flag == true ){
                        Event_Group_ID = result_id;
                    }else if ( add_event_flag == true ){
                        Event_ID = result_id;
                    }

                } catch (JSONException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        @Override
        public void onHttpStatusChanged(int httpState) {
            Log.d(TAG, "onHttpStatusChanged = " + httpState);
        }

        @Override
        public void onSynchronizeStatusChanged(GeoFenceSyncState arg0, boolean arg1) {

        }

        @Override
        public void onServiceConnected() {
            Log.d(TAG, "onServiceConnected");

            onServiceConnected_flag = true;


            AppData appdata = new AppData();
            appdata.appID = ""; // not use
            //appdata.appName = "geofencesample";
            //appdata.packageName = mPakacgeName;
            appdata.appName = "todoapp";
            appdata.packageName = mPakacgeName;

            Toast.makeText(getApplicationContext(), appdata.packageName, Toast.LENGTH_LONG).show();
            Log.d("[HC] mPakacgeName",mPakacgeName);

            appdata.tdcProjectKey = "9d57e48e-89d2-4779-8d1b-5a05ee05ff0c";//실제 release 할때는 이 값으로 release 합니다. 이값은 SKT 에서 받아야 합니다.

            if (mAgentManager != null) {
                mAgentManager.setAppData(appdata);
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        try {
            if (mAgentManager != null) {
                mAgentManager.release();
            }
        } catch (GeoFenceAgentException e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }



    void add_setflag_currnet_action(int mIndex){

        switch (mIndex) {
            case 1://add storegroup
                add_store_group_flag = true;
                add_store_flag = false;
                add_event_group_flag = false;
                add_event_flag = false;
                break;
            case 2://add store
                add_store_group_flag = false;
                add_store_flag = true;
                add_event_group_flag = false;
                add_event_flag = false;
                break;
            case 3://add eventgroup
                add_store_group_flag = false;
                add_store_flag = false;
                add_event_group_flag = true;
                add_event_flag = false;
                break;
            case 4://add event
                add_store_group_flag = false;
                add_store_flag = false;
                add_event_group_flag = false;
                add_event_flag = true;
                break;
            case 10 : //clear
                add_store_group_flag = false;
                add_store_flag = false;
                add_event_group_flag = false;
                add_event_flag = false;
            default :
                add_store_group_flag = false;
                add_store_flag = false;
                add_event_group_flag = false;
                add_event_flag = false;
        }
    }



    View.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //String fenceId = "";
            JSONObject data = new JSONObject();

            switch (v.getId()) {
                case R.id.add_api: // add
                    try {
                        switch (mIndex) {
                            case 1://add storegroup

                                add_setflag_currnet_action(mIndex);

                                //data.put("appId", "todoapp");
                                data.put("storeGroupName", null);
                                data.put("groupName", "테스트그룹");
                                data.put("groupDesc", "오늘 테스트");
                                data.put("groupDesc", "그룹 시험2수정입니다.");
                                data.put("groupIcon", "Fastfood");
                                data.put("updateUser", 0);
                                data.put("isUse", "Y");

                                if (mAgentManager != null) {
                                    mAgentManager.setWStoreGroup(data.toString());
                                }

                                mLat.setVisibility(View.VISIBLE);
                                mLon.setVisibility(View.VISIBLE);

                                changeRadioToStore();

                                break;

                            case 2://add store

                                add_setflag_currnet_action(mIndex);


                                int storegroupid = Integer.parseInt(Store_Group_ID);
                                String storegroupname = "한국 굿데이" + Store_Group_ID ;

                                Log.d("addstore : storegroupid == ", String.valueOf(storegroupid));

                                //data.put("storeId", 19);//이건 빼자.//튜토리얼에.
                                data.put("appId", "geofence");
                                data.put("storeGroupId", storegroupid);
                                data.put("name", storegroupname);//store 이름이 겹치지 않도록 한다.
                                data.put("address", "한국");
                                data.put("floor", 1);

                                if ((latitude !=null) && (Longitude != null)){
                                    Log.d("위도 == ", latitude);
                                    Log.d("경도 == ", Longitude);
                                    data.put("latitude",Float.parseFloat(latitude));//위도 직접입력
                                    data.put("longitude", Float.parseFloat(Longitude));//경도 직접입력
                                    latitude = null;
                                    Longitude = null;
                                }
                                else
                                {
                                    data.put("latitude", 37.342170);//셀리지온 주소//테스트 코드
                                    data.put("longitude", 127.108427);//셀리지온 주소//테스트 코드
                                }

                                data.put("isUse", "Y");


                                if (mAgentManager != null) {
                                    mAgentManager.setWStore(data.toString());
                                }

                                mLat.setVisibility(View.GONE);
                                mLon.setVisibility(View.GONE);
                                changeRadioToEventGroup();

                                break;

                            case 3://add eventgroup

                                add_setflag_currnet_action(mIndex);

                                data.put("groupName", "DioTest_2");
                                data.put("groupDesc", "DioTest");
                                //data.put("eventStatus", null);


                                String start_date = "2014-11-25";//사용자 start 날짜 설정
                                String end_date = "2015-11-30";//사용자 end 날짜 설정


                                data.put("startDate", start_date);//날짜를 잘 맞추어야 한다.
                                data.put("endDate", end_date);//날짜를 잘 맞춰야 한다.

                                data.put("groupIcon", "Couple");
                                data.put("isUse", "Y");
                                data.put("eventCount", 0);
                                data.put("eventList", null);




                                if (mAgentManager != null) {
                                    mAgentManager.setWEventGroup(data.toString());
                                }

                                changeRadioToEvent();

                                break;

                            case 4://add event

                                add_setflag_currnet_action(mIndex);

                                int eventgroupid_2 = Integer.parseInt(Event_Group_ID);
                                int store_2 = Integer.parseInt(Store_ID);
                                String eventgroupname = "ClientTest1" + Event_Group_ID ;

                                Log.d("addevent : eventgroupid == ", Event_Group_ID);
                                Log.d("addevent : store_2 == ",Store_ID);

                                data.put("eventGroupId", eventgroupid_2);
                                data.put("eventName", eventgroupname);
                                data.put("eventIcon", "Etc");//정해진 것중에 골라야 함. 아무 값이나 넣으면 안됨.
                                data.put("eventCheckType", "CheckIn");
                                data.put("eventStayMinute", 60);
                                data.put("isAllTime","Y");
                                //타임을 설정하기 위해서는 아래 코드를 참고한다.
                                //data.put("isAllTime","N");
                                //JSONArray eventArray = new JSONArray();
                                //JSONObject eventData = new JSONObject();
                                //eventData.put("startTime","12");
                                //eventData.put("endTime","18");
                                //eventArray.put(eventData);
                                //data.put("eventTimeList",eventArray);
                                data.put("isUse","Y");
                                data.put("availableWeekDate", "MON, TUE, WED, THU, FRI, SAT, SUN");

                                JSONArray storeArray = new JSONArray();
                                JSONObject storeData = new JSONObject();
                                storeData.put("storeId",store_2);
                                storeArray.put(storeData);
                                data.put("eventStoreList",storeArray);

                                Log.d("data.toString()",data.toString());
                                if (mAgentManager != null) {
                                    mAgentManager.setWEvent(data.toString());
                                }

                                startGet();

                                break;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

                case R.id.get_api: // get
                    Store_Group_ID ="1";
                    Store_ID = "1";
                    switch (mIndex) {
                        case 1://get storegroup

                            add_setflag_currnet_action(10);//flag clear
                            Log.d("getstoregroup : storegroupid == ", Store_Group_ID);

                            if (mAgentManager != null) {
                                mAgentManager.getWStoreGroup(Store_Group_ID);
                            }
                            changeRadioToStore();
                            break;
                        case 2://get store

                            Log.d("getstoregroup : storeid == ", Store_ID);

                            if (mAgentManager != null) {
                                mAgentManager.getWStore(Store_ID);
                            }
                            changeRadioToEventGroup();
                            break;
                        case 3://get eventgroup

                            Log.d("getstoregroup : eventgroupid == ", Event_Group_ID);
                            if (mAgentManager != null) {
                                mAgentManager.getWEventGroup(Event_Group_ID);
                            }

                            changeRadioToEvent();
                            //startGetAll();

                            break;
                        case 4://get event//새로 추가되었음

                            Log.d("getstoregroup : eventid == ", Event_ID);
                            if (mAgentManager != null) {
                                mAgentManager.getWEvent(Event_ID);
                            }
                            startGetAll();
                            break;
                    }
                    break;

                case R.id.get_all_api:
                    switch (mIndex) {
                        case 1://get all storegroup

                            if (mAgentManager != null) {

                                AppData appdata = new AppData();
                                appdata.appID = ""; // not use
                                appdata.appName = "todoapp";
                                appdata.packageName = mPakacgeName;
                                Toast.makeText(getApplicationContext(), appdata.packageName, Toast.LENGTH_LONG).show();
                                appdata.tdcProjectKey = "9d57e48e-89d2-4779-8d1b-5a05ee05ff0c";//실제 release 할때는 이 값으로 release 합니다. 이값은 SKT 에서 받아야 합니다.
                                mAgentManager.setAppData(appdata);
                                //////////////////
                                mAgentManager.getWStoreGroupAll();
                            }
                            changeRadioToStore();
                            break;
                        case 2://get store
                            //Log.d("getstoregroup : storegroupid == ", Store_Group_ID);
                            if (mAgentManager != null) {
                                mAgentManager.getWStoreAll(Store_Group_ID);
                            }
                            changeRadioToEventGroup();
                            break;
                        case 3://get all eventgroup
                            if (mAgentManager != null) {
                                Log.d("get all eventgroup","get all eventgroup");
                                mAgentManager.getWEventGroupAll();
                            }
                            changeRadioToEvent();
                            break;
                        case 4://get all event from Store

                            Log.d("getstoregroup : Store_ID for get all event of store == ", Store_ID);
                            if (mAgentManager != null) {
                                Log.d("get all event","get all event");
                                //mAgentManager.getWEventAll("443");
                                mAgentManager.getWStoreEventAll(Store_ID);//위의 인자는 event id 가 아니라 store id 를 넣는다.

                                startUpdate();
                            }
                            break;
                    }
                    break;

                case R.id.delete_api:
                    switch (mIndex) {
                        case 1://del storegroup
                            Log.d("delstoregroup : storegroupid == ", Store_Group_ID);
                            if (mAgentManager != null) {
                                mAgentManager.deleteWStoreGroup(Store_Group_ID);
                            }
                            changeRadioToEvent();
                            break;
                        case 2://del store
                            Log.d("delstoregroup : storeid == ", Store_ID);
                            if (mAgentManager != null) {
                                mAgentManager.deleteWStore(Store_ID);
                            }
                            changeRadioToStoreGroup();
                            break;
                        case 3://del eventgroup
                            Log.d("delstoregroup : Event groupid == ", Event_Group_ID);
                            if (mAgentManager != null) {
                                mAgentManager.deleteWEventGroup(Event_Group_ID);
                            }
                            finishTutorial();
                            break;
                        case 4://del event
                            Log.d("delstoregroup : eventid == ", Event_ID);
                            if (mAgentManager != null) {
                                mAgentManager.deleteWEvent(Event_ID);
                            }
                            changeRadioToEventGroup();
                            break;
                    }
                    break;

                case R.id.update_api: // update
                    switch (mIndex) {
                        case 1://update storegroup
                            int storegroupid_3 = Integer.parseInt(Store_Group_ID);
                            Log.d("updatestoregroup : storegroupid == ", Store_Group_ID);
                            if (mAgentManager != null) {
                                try {
                                    data.put("storeGroupId", storegroupid_3);
                                    data.put("groupName", "테스트그룹");
                                    data.put("groupDesc", "그룹 시험2수정입니다.");
                                    data.put("groupIcon", "Fastfood");
                                    data.put("updateUser", 0);
                                    data.put("isUse", "Y");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                mAgentManager.updateWStoreGroup(data.toString());
                                changeRadioToStore();
                            }
                            break;
                        case 2://update store
                            int storegroupid = Integer.parseInt(Store_Group_ID);
                            int storeid = Integer.parseInt(Store_ID);
                            String storegroupname = "중식매장" + Store_Group_ID ;

                            Log.d("updatestore : storegroupid == ", Store_Group_ID);
                            Log.d("updatestore : storeid == ", Store_ID);
                            if (mAgentManager != null) {
                                try {
                                    data.put("storeGroupId", storegroupid);
                                    data.put("name", storegroupname);
                                    data.put("address", "미국");
                                    data.put("floor", 12);
                                    data.put("storeId", storeid);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                mAgentManager.updateWStore(data.toString());
                                changeRadioToEventGroup();
                            }
                            break;
                        case 3://update eventgroup

                            int eventgroupid = Integer.parseInt(Event_Group_ID);
                            Log.d("updatestoregroup : eventgroupid == ", Event_Group_ID);

                            if (mAgentManager != null) {
                                try {
                                    data.put("eventGroupId", eventgroupid);
                                    //data.put("appId", "geofence");//삭제요함
                                    data.put("groupName", "DioTest");
                                    data.put("groupDesc", "DioTest");
                                    data.put("eventStatus", null);
                                    String start_date = "2014-11-17";//사용자 start 날짜 설정
                                    String end_date = "2014-11-30";//사용자 end 날짜 설정


                                    data.put("startDate", start_date);//날짜를 잘 맞추어야 한다.
                                    data.put("endDate", end_date);//날짜를 잘 맞춰야 한다.
                                    data.put("groupIcon", "Couple");
                                    data.put("isUse", "Y");
                                    data.put("eventCount", 0);
                                    data.put("eventList", null);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                mAgentManager.updateWEventGroup(data.toString());
                                changeRadioToEvent();
                            }
                            break;
                        case 4://update event

                            Log.d("updatestoregroup : eventgroupid == ", Event_Group_ID);
                            Log.d("updatestoregroup : eventid == ", Event_ID);
                            Log.d("updatestoregroup : Storeid == ", Store_ID);

                            if (mAgentManager != null) {
                                try {
                                    data.put("eventId", Event_ID);//update event 는 eventid 와 eventgroupid 모두 넣어야 한다.
                                    data.put("eventGroupId", Event_Group_ID);//update event 는 eventid 와 eventgroupid 모두 넣어야 한다.

                                    data.put("eventName", "ClientTest2");
                                    data.put("eventIcon", "Etc");//정해진 것중에 골라야 함.막 넣지마라
                                    //data.put("eventFenceType", "Geo");
                                    data.put("eventCheckType", "CheckIn");
                                    data.put("eventStayMinute", 60);
                                    data.put("isAllTime","Y");
                                    data.put("isUse","Y");//필수 파라메터..꼭 들어가야함.(add 에서는 필수가 아님...-추후 옵션으로 예정)
                                    data.put("availableWeekDate", "MON, TUE, WED, THU, FRI, SAT, SUN");

                                    JSONArray storeArray = new JSONArray();
                                    JSONObject storeData = new JSONObject();
                                    storeData.put("storeId",Store_ID);
                                    storeArray.put(storeData);
                                    data.put("eventStoreList",storeArray);

                                    Log.d("",data.toString());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                mAgentManager.updateWEvent(data.toString());

                                startDelete();
                            }
                    }
                    break;

                case R.id.sync_db:

                    AppData appdata = new AppData();
                    appdata.appID = "";
                    appdata.appName = "todoapp";
                    appdata.packageName = mPakacgeName;

                    appdata.tdcProjectKey = "9d57e48e-89d2-4779-8d1b-5a05ee05ff0c";//실제 release 할때는 이 값으로 release 합니다. 이값은 SKT 에서 받아야 합니다.

                    appdata.regId = "";
                    if (mAgentManager != null) {
                        mAgentManager.syncWDB(appdata);
                    }
                    break;

            }
        }
    };

    @Override
    public void onBackPressed() {

        finish();

        super.onBackPressed();
    }

    OnCheckedChangeListener mCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rdoStoreGroup://storegroup
                    mIndex = 1;
                    mBtnGet.setVisibility(View.VISIBLE);
                    break;
                case R.id.rdoStore://store
                    mIndex = 2;
                    mBtnGet.setVisibility(View.VISIBLE);
                    break;
                case R.id.rdoEventGroup://eventgroup
                    mIndex = 3;
                    mBtnGet.setVisibility(View.VISIBLE);
                    break;
                case R.id.rdoEvent://event
                    mIndex = 4;
                    mBtnGet.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
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