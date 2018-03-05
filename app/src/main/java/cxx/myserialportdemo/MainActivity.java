package cxx.myserialportdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SerialPortHelper serialPortHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serialPortHelper = ((App) getApplication()).serialPortHelper;

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //向串口发送数据
                byte[] ins={(byte)0x7e,(byte)0x7f};
                serialPortHelper.sendData(ins);
            }
        });
        //得到串口的数据
        serialPortHelper.setOnReceivedListener(new SerialPortHelper.OnReceivedListener() {
            @Override
            public void onReceived(byte[] bytes) {
                Log.d("onReceived", "------->>" + new String(bytes));
            }
        });
    }
}
