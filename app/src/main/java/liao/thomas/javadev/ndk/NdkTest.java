package liao.thomas.javadev.ndk;

/**
 * Created by thomasliao on 2017/4/13.
 */

public class NdkTest {

    public native String stringFromJNI();

    static {
        System.loadLibrary("native-lib");
    }

}
