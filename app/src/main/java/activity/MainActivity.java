package activity;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asus.wifiscan.R;

import java.util.ArrayList;
import java.util.List;

import adapter.WiFiInfoAdapter;
import base.WiFiInfo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<WiFiInfo> mWiFiInfoList = new ArrayList<>();

    private EditText mInputEditText;
    private Button mScanButton;
    private RecyclerView mWiFiRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取控件实例
        mInputEditText = (EditText) findViewById(R.id.input_wifi_name_et);
        mScanButton = (Button) findViewById(R.id.scan_bt);
        mWiFiRecyclerView = (RecyclerView) findViewById(R.id.wifi_show_rv);

        // 设置监听动作
        mScanButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scan_bt:
                if ("".equals(mInputEditText.getText().toString().trim())) {
                    Toast.makeText(MainActivity.this,
                            "请输入wifi名字", Toast.LENGTH_SHORT).show();
                } else {
                    startScanThread(mInputEditText.getText().toString().trim());
                }
                break;
        }
    }

    // 启动线程进行循环扫描
    private void startScanThread(final String wifiName) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            wiFiScan(wifiName);
                            showWiFiInfo();
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    private void wiFiScan(String wifiName) {
        WifiManager wifiManager = (WifiManager) getApplicationContext()
                .getSystemService(WIFI_SERVICE);
        // 启动扫描
        wifiManager.startScan();
        List<ScanResult> scanResultList = wifiManager.getScanResults();
        for (ScanResult scanResult : scanResultList) {
            if (wifiName.equals(scanResult.SSID)) {
                WiFiInfo wiFiInfo = new WiFiInfo("WifiName: " + scanResult.SSID,
                        "WifiSignal: " + scanResult.level);
                mWiFiInfoList.add(wiFiInfo);
            }
        }
    }

    private void showWiFiInfo() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mWiFiRecyclerView.setLayoutManager(layoutManager);
        WiFiInfoAdapter wiFiInfoAdapter = new WiFiInfoAdapter(mWiFiInfoList);
        mWiFiRecyclerView.setAdapter(wiFiInfoAdapter);
        mWiFiRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        mWiFiRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
