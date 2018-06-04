package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asus.wifiscan.R;

import java.util.List;

import base.WiFiInfo;

public class WiFiInfoAdapter extends RecyclerView.Adapter<WiFiInfoAdapter.WiFiInfoViewHolder> {

    private List<WiFiInfo> mWiFiInfoList;

    static class WiFiInfoViewHolder extends RecyclerView.ViewHolder {
        TextView wiFiName;
        TextView wiFiSignal;

        public WiFiInfoViewHolder(View view) {
            super(view);
            wiFiName = (TextView) view.findViewById(R.id.wifi_name_tv);
            wiFiSignal = (TextView) view.findViewById(R.id.wifi_signal_tv);
        }
    }

    public WiFiInfoAdapter(List<WiFiInfo> wiFiiInfoList) {
        mWiFiInfoList = wiFiiInfoList;
    }

    @Override
    public WiFiInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wifi_info_item, parent, false);
        WiFiInfoViewHolder wiFiInfoViewHolder = new WiFiInfoViewHolder(view);
        return wiFiInfoViewHolder;
    }

    @Override
    public void onBindViewHolder(WiFiInfoViewHolder wiFiInfoViewHolder, int position) {
        WiFiInfo wiFiInfo = mWiFiInfoList.get(position);
        wiFiInfoViewHolder.wiFiName.setText(wiFiInfo.getmWiFiNamePrivate());
        wiFiInfoViewHolder.wiFiSignal.setText(wiFiInfo.getmWiFiSignalPrivate());
    }

    @Override
    public int getItemCount() {
        return mWiFiInfoList.size();
    }
}
