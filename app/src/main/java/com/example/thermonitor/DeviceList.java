package com.example.thermonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DeviceList extends AppCompatActivity {


    private WifiManager wifiManager;
    private ListView mListView;
    private int size = 0;
    private List <ScanResult> results;
    private ArrayList<String> esps = new ArrayList<>();
    private String espinfo;


    int espimage = R.drawable.esp;


    public DeviceList() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

       wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);




       if (!wifiManager.isWifiEnabled()){

           Toast.makeText(this, "WiFi is disabled.... we will to enable it for you!!!", Toast.LENGTH_LONG).show();
           wifiManager.setWifiEnabled(true);
       }

        scanWifi();
        mListView = findViewById(R.id.OS);

        CustomAdaptor customAdaptor = new CustomAdaptor();
        mListView.setAdapter(customAdaptor);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DeviceList.this, DeviceDetail.class);
                intent.putExtra("esp", results.get(position).SSID);
                startActivity(intent);
            }
        });

    }

    class CustomAdaptor extends BaseAdapter{

        @Override
        public int getCount() {
            return esps.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.os_row, null);
            ImageView mImageView = view.findViewById(R.id.imageView);
            TextView mTextView =  view.findViewById(R.id.TextView);
            mImageView.setImageResource(espimage);
            mTextView.setText(esps.get(position));

            return view;
        }
    }

    private void scanWifi(){
        esps.clear();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(this, "Scanning for nearby  WiFi devices", Toast.LENGTH_SHORT).show();
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifiManager.getScanResults();
            unregisterReceiver(this);
            for(int i=0;i<results.size();i++){
                if(results.get(i).SSID.equals("Nadeem")){
                    espinfo ="SSID:  " + results.get(i).SSID +"      " + "MAC Address:  " + results.get(i).BSSID;
                    esps.add(espinfo);
                }
            }
        }
    };

}
