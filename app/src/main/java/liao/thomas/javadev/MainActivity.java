package liao.thomas.javadev;

import android.support.annotation.Keep;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import liao.thomas.javadev.ndk.NdkTest;

public class MainActivity extends AppCompatActivity {
    int hour = 0;
    int minute = 0;
    int second = 0;

    TextView tickView;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        tickView = (TextView) findViewById(R.id.sample_text);
        NdkTest.test();
    }


    @Override
    protected void onResume() {
        super.onResume();
        hour = minute = second = 0;
        ((TextView)findViewById(R.id.sample_text)).setText(stringFromJNI());
        startTicks();
    }

    @Override
    protected void onPause() {
        super.onPause();
        StopTicks();
    }

    /*
 * A function calling from JNI to update current timer
 */
    @Keep
    private void updateTimer() {
        ++second;
        if(second >= 60) {
            ++minute;
            second -= 60;
            if(minute >= 60) {
                ++hour;
                minute -= 60;
            }
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String ticks = "" + MainActivity.this.hour + ":" +
                        MainActivity.this.minute + ":" +
                        MainActivity.this.second;
                MainActivity.this.tickView.setText(ticks);
            }
        });
    }
    static {
        System.loadLibrary("hello-jnicallback");
    }
    public native  String stringFromJNI();
    public native void startTicks();
    public native void StopTicks();
}
