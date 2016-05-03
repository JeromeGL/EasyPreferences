package com.ezpreferences;

import com.ezpreferences.interfaces.Commit;
import com.ezpreferences.interfaces.Default;
import com.ezpreferences.interfaces.EasyPreferenceInterface;
import com.ezpreferences.interfaces.Key;

/**
 * Created by hujinrong on 16/5/3.
 */
@EasyPreferenceInterface
public interface Configuration {

    void setVolumnEnable(boolean enable);
    @Default("true")
    boolean getVolumnEnable();

    @Commit
    boolean setCurrentVersionCode(int versionCode);
    int getCurrentVersionCode();

    @Key(key="CURRENT_VERSION_NAME_KEY")
    void setCurrentVersionName(String versionName);
    @Key(key="CURRENT_VERSION_NAME_KEY")
    String getCurrentVersionName();



    void setLastSaveTime(long time);
    long getLastSaveTime();

    void setLastScore(float score);
    float getLastScore();
}
