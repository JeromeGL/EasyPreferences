#EasyPreference
##EasyPreference 用来帮助Android开发者减少编写SharedPreferences重复代码的工具.

#特性
* 编译期生成代码.
* 依赖jar包文件小于5k.

#原理
通过AndroidStudio插件apt,编译期间对Annotation解析生成Java源代码并编译.


#使用方法-AndroidStudio配置

build.gradle(project level)
```javascript
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.0.0'
        //annotationprocess
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
        //maven
        maven { url "https://jitpack.io" }
    }
}
```
build.gradle(module level)
```javascript
apply plugin: 'com.android.application'
apply plugin: 'android-apt'
...
dependencies {
    //...
    // EasyPreference
    compile 'com.github.JeromeGL.EasyPreferences:library:0.3'
    apt 'com.github.JeromeGL.EasyPreferences:processor:0.3'
}
```
#使用方法

定义
```java
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

```
* @EasyPreferenceInterface 注解接口.生成的类为Configuration_与Configuration处于同一包名.在build/generated/source/apt/下
* 方法的命名格式为 getXXX/setXXX/isXXX 其中isXXX返回值为boolean类型
* setXXX方法默认调用的是apply方法,且返回类型为void.
* @Commit注解setXXX方法调用commit方法.返回类型为boolean.
* @Default注解isXXX/getXXX方法指定默认值.
* 默认的key通过方法名解析得到.@Key指定key名称.
* 
## 生成的类为

```java
public final class Configuration_ implements Configuration {
  SharedPreferences mSharedPreference;

  public Configuration_(Context context) {
    mSharedPreference = context.getApplicationContext().getSharedPreferences("Configuration_P",Context.MODE_PRIVATE);;
  }

  public Configuration_(SharedPreferences sharedPreference) {
    mSharedPreference = sharedPreference;
  }

  public void setVolumnEnable(boolean value) {
    mSharedPreference.edit().putBoolean("EZP_VolumnEnable",value).apply();
  }

  public boolean getVolumnEnable() {
    return mSharedPreference.getBoolean("EZP_VolumnEnable",true);
  }

  public boolean setCurrentVersionCode(int value) {
    return mSharedPreference.edit().putInt("EZP_CurrentVersionCode",value).commit();
  }

  public int getCurrentVersionCode() {
    return mSharedPreference.getInt("EZP_CurrentVersionCode",0);
  }

  public void setCurrentVersionName(String value) {
    mSharedPreference.edit().putString("CURRENT_VERSION_NAME_KEY",value).apply();
  }

  public String getCurrentVersionName() {
    return mSharedPreference.getString("CURRENT_VERSION_NAME_KEY",null);
  }

  public void setLastSaveTime(long value) {
    mSharedPreference.edit().putLong("EZP_LastSaveTime",value).apply();
  }

  public long getLastSaveTime() {
    return mSharedPreference.getLong("EZP_LastSaveTime",0);
  }

  public void setLastScore(float value) {
    mSharedPreference.edit().putFloat("EZP_LastScore",value).apply();
  }

  public float getLastScore() {
    return mSharedPreference.getFloat("EZP_LastScore",0);
  }
}

```

使用
```java 
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
```


  
```