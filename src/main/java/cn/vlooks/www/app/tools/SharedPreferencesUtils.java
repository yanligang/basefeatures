package cn.vlooks.www.app.tools;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created with IntelliJ IDEA.
 */
public class SharedPreferencesUtils {

    private static SharedPreferencesUtils instance;

    public static SharedPreferencesUtils getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesUtils(context);
        }
        return instance;
    }

    private SharedPreferences settings;


    public SharedPreferencesUtils(Context context) {
        settings = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public synchronized void set(String key, String value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public synchronized void remove(String key) {
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.commit();
    }

    public synchronized String get(String key) {
        return settings.getString(key, "");
    }


}
