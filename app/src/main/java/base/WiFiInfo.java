package base;

public class WiFiInfo {
    private String mWiFiNamePrivate;
    private String mWiFiSignalPrivate;

    public WiFiInfo(String mWiFiNamePrivate, String mWiFiSignalPrivate) {
        this.mWiFiNamePrivate = mWiFiNamePrivate;
        this.mWiFiSignalPrivate = mWiFiSignalPrivate;
    }

    public String getmWiFiNamePrivate() {
        return mWiFiNamePrivate;
    }

    public void setmWiFiNamePrivate(String mWiFiNamePrivate) {
        this.mWiFiNamePrivate = mWiFiNamePrivate;
    }

    public String getmWiFiSignalPrivate() {
        return mWiFiSignalPrivate;
    }

    public void setmWiFiSignalPrivate(String mWiFiSignalPrivate) {
        this.mWiFiSignalPrivate = mWiFiSignalPrivate;
    }
}
