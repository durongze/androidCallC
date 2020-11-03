package com.example.myapplication;
// package com.durongze.jni;
import android.os.Bundle;

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    String TAG = "Main";
    /*static {
        System.loadLibrary("my");
    }
    public native String getStringFromHome();*/
    public native String[] CInterface(String[] name, int[] age, float[] height, int num);
    public native int CInterfaceTest();
    static {
        System.loadLibrary("my");
    }

    public void JInterface()
    {
        Log.e(TAG, "JInterface");
    }

    public int c_call_test()
    {
        int ret = CInterfaceTest();
        Log.e(TAG, "c_call_test: " + ret );
        return ret;
    }
    public String c_call_main(){
        int idx = 0;
        int num = 4;
        String result = null;
        String[] name = new String[num]; // ("durongze", "duyongze");
        int[] age = new int[num]; // (28, 30);
        float[] height = new float[num]; // (177, 188);
        for (idx = 0; idx < num; ++idx){
            name[idx] = Integer.toString(100 + idx);
            age[idx] = 200 + idx;
            height[idx] = 300 + idx;
        }
        String[] vals = CInterface(name, age, height, num);
        for (idx = 0; idx < vals.length; ++idx){
            if (result == null) {
                result = "\nUser[" + idx + "]:" + vals[idx] + "\n";
            } else {
                result += "User[" + idx + "]:" + vals[idx] + "\n";
            }
        }
        return result;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int ret = 0;
            String result = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    // String msg = getStringFromHome();
                    // Log.e(TAG, "onNavigationItemSelected: " + msg);
                    // mTextMessage.setText(R.string.title_home);

                    ret = c_call_test();
                    mTextMessage.setText("c_call_test " + ret);
                    return true;
                case R.id.navigation_dashboard:
                    result = c_call_main();
                    mTextMessage.setText("c_call_main" + result);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
