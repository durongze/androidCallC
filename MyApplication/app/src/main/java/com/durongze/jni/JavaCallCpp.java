package com.durongze.jni;

import android.util.Log;

public class JavaCallCpp {
    String TAG = "CallCpp";

    public native String[] GenUserList(String[] name, int[] age, float[] height, int num);
    public native int WriteCtxToFile(String fileName);
    public native int CppCallJava(String className, String funcName);

    static {
        System.loadLibrary("my");
    }

    public void JInterface()
    {
        Log.e(TAG, "JInterface");
    }

    public int CallJava(String className, String funcName)
    {
        int ret = CppCallJava(className, funcName);
        Log.e(TAG, "CppCallJava: " + ret );
        return ret;
    }

    public int WriteFile(String fileName)
    {
        int ret = WriteCtxToFile(fileName);
        Log.e(TAG, "WriteCtxToFile: " + ret );
        return ret;
    }

    public int UserList(String[] name, int[] age, float[] height, int num)
    {
        int idx = 0;
        for (idx = 0; idx < num; ++idx){
            name[idx] = Integer.toString(100 + idx);
            age[idx] = 200 + idx;
            height[idx] = 300 + idx;
        }
        return 0;
    }

    public String GetUserList(){
        int idx = 0;
        int num = 4;
        String result = null;
        String[] name = new String[num]; // ("durongze", "duyongze");
        int[] age = new int[num]; // (28, 30);
        float[] height = new float[num]; // (177, 188);
        UserList(name, age, height, num);
        String[] vals = GenUserList(name, age, height, num);
        for (idx = 0; idx < vals.length; ++idx){
            if (result == null) {
                result = "\nUser[" + idx + "]:" + vals[idx] + "\n";
            } else {
                result += "User[" + idx + "]:" + vals[idx] + "\n";
            }
        }
        return result;
    }
}
