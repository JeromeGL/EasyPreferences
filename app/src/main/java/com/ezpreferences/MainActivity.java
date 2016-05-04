package com.ezpreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivity";
    Configuration configuration = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testConfiguration();
    }

    private void testConfiguration() {
        Configuration  configuration = new Configuration_(this);
        long lastSaveTime = configuration.getLastSaveTime() ;
        if( lastSaveTime == 0 ) {
            configuration.setLastSaveTime(System.currentTimeMillis());
        }

        boolean volumn = configuration.getVolumnEnable() ;


        float lastScore = configuration.getLastScore() ;
        if( lastScore == 0 ) {
            configuration.setLastScore(99.89f);
        }

        configuration.setCurrentVersionCode(1000001);
        configuration.setCurrentVersionName("V1.0.0.1");

        Log.d(TAG,"volumn enable:"+volumn);
        Log.d(TAG,"lastSaveTime:"+configuration.getLastSaveTime());
        Log.d(TAG,"lastScore:"+configuration.getLastScore());
        Log.d(TAG,"versionCode:"+configuration.getCurrentVersionCode());
        Log.d(TAG,"versionName:"+configuration.getCurrentVersionName());




    }
}
