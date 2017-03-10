package liao.thomas.javadev.gson;

import com.google.gson.Gson;

/**
 * Created by liaodongming on 2017/2/13.
 */

public class Test {

    public static void test() {
        UserSimple userObject = new UserSimple(
                "Norman",
                "norman@futurestud.io",
                26,
                true
        );

        Gson gson = new Gson();
        String userJson = gson.toJson(userObject);


    }



}
