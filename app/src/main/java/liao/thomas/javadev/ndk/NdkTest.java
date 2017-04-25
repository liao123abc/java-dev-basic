package liao.thomas.javadev.ndk;

import android.util.Log;

/**
 * Created by thomasliao on 2017/4/13.
 *
 *
 *
 *
 *
 *
 */

public class NdkTest {

    private static final String TAG = NdkTest.class.getSimpleName();

    static {
        System.loadLibrary("native-lib");// Load native library at runtime, dll(windows), .so(Unixes)
    }

    public static String test() {
        NdkTest ndkTest = new NdkTest();
        String test = ndkTest.stringFromJNI();
        Log.d(TAG, test);
        return test;
    }


    public native String stringFromJNI();
}
