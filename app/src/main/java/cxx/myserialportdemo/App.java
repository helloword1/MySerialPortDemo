package cxx.myserialportdemo;

import android.app.Application;

import android_serialport_api.SerialUtil;

/**
 * Created by Administrator on 2018/3/5.
 */

public class App extends Application {

    public SerialPortHelper serialPortHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        serialPortHelper = new SerialPortHelper(new SerialUtil("dev/ttys4", 9600, 0));
    }
}
