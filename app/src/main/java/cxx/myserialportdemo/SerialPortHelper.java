package cxx.myserialportdemo;

import android.os.SystemClock;

import android_serialport_api.SerialUtil;

/**
 * Created by Administrator on 2018/3/5.
 */

public class SerialPortHelper {
    private SerialUtil serialUtil;
    private ReadThread readThread;

    public SerialPortHelper() {

    }

    static OnReceivedListener mOnReceivedListener;

    public SerialPortHelper(SerialUtil serialUtil) {
        this.serialUtil = serialUtil;
    }

    public void sendData(byte[] bytes) {
        //int test2=bytes[0]&255;   -2 2
        serialUtil.setData(bytes);
    }

    //    app.portHelper.startReadThread();//开启线程读串口数据
    public void releaseReadThread() {

        mOnReceivedListener = null;
        if (readThread != null) readThread.interrupt();
    }

    public class ReadThread extends Thread {

        @Override
        public void run() {
            super.run();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    SystemClock.sleep(10);
                    byte[] results = serialUtil.getDataByte();
                    if (results != null) {
                        SerialPortHelper.mOnReceivedListener.onReceived(results);
                    }
                } catch (NullPointerException e) {
                } catch (Exception e) {
                }

            }
        }
    }

    public void setOnReceivedListener(OnReceivedListener onReceivedListener) {

        this.mOnReceivedListener = null;
        if (readThread != null) {
            readThread.interrupt();
            readThread = null;
        }
        this.mOnReceivedListener = onReceivedListener;
        readThread = new ReadThread();
        readThread.start();
    }

    public interface OnReceivedListener {
        void onReceived(byte[] bytes);
    }
}
