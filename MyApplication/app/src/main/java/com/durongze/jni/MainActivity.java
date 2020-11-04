package com.durongze.jni;
import android.os.Bundle;

import com.durongze.jni.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    String TAG = "Main";

    String GetUserList()
    {
        String userList = null;
        JavaCallCpp jcc = new JavaCallCpp();
        userList = jcc.GetUserList();
        return userList;
    }

    String WriteCtxToFile(String fileName)
    {
        String result = null;
        JavaCallCpp jcc = new JavaCallCpp();
        result = Integer.toString(jcc.WriteFile(fileName));
        return result;
    }

    String CppCallJava( String funcName)
    {
        String className = "com/durongze/jni/JavaCallCpp";
        String result = null;
        JavaCallCpp jcc = new JavaCallCpp();
        result = Integer.toString(jcc.CallJava(className, funcName));
        return result;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    // String msg = getStringFromHome();
                    // Log.e(TAG, "onNavigationItemSelected: " + msg);
                    // mTextMessage.setText(R.string.title_home);
                    mTextMessage.setText("c_call_test " + GetUserList());
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText("c_call_main" + WriteCtxToFile("xxx.txt"));
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications + CppCallJava("JInterface"));
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
