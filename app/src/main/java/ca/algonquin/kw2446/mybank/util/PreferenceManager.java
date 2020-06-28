package ca.algonquin.kw2446.mybank.util;


import android.content.Context;
import android.content.SharedPreferences;


/**

 * 데이터 저장 및 로드 클래스

 */

public class PreferenceManager {

    public static final String PREFERENCES_NAME = "bank_preference";



    private static final String DEFAULT_VALUE_STRING = "";

    private static final boolean DEFAULT_VALUE_BOOLEAN = false;

    private static final int DEFAULT_VALUE_INT = -1;

    private static final long DEFAULT_VALUE_LONG = -1L;

    private static final float DEFAULT_VALUE_FLOAT = -1F;



    private static SharedPreferences getPreferences(Context context) {

        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

    }


    public static void setValue(Context context, String key, Object value){
        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();
        if(value instanceof String){
            editor.putString(key,(String) value);
        }else if(value instanceof Integer){
            editor.putInt(key,(Integer) value);
        }else if(value instanceof Float){
            editor.putFloat(key,(Float) value);
        }else if(value instanceof Boolean){
            editor.putBoolean(key,(Boolean)value);
        }
            editor.commit();

    }


    public static Object getValue(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        Object value= prefs.getAll().get(key);
        return value;
    }

    /**

     * String 값 로드

     * @param context

     * @param key

     * @return

     */

    public static String getString(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        String value = prefs.getString(key, DEFAULT_VALUE_STRING);

        return value;

    }



    /**

     * boolean 값 로드

     * @param context

     * @param key

     * @return

     */

    public static boolean getBoolean(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        boolean value = prefs.getBoolean(key, DEFAULT_VALUE_BOOLEAN);

        return value;

    }



    /**

     * int 값 로드

     * @param context

     * @param key

     * @return

     */

    public static int getInt(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        int value = prefs.getInt(key, DEFAULT_VALUE_INT);

        return value;

    }



    /**

     * long 값 로드

     * @param context

     * @param key

     * @return

     */

    public static long getLong(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        long value = prefs.getLong(key, DEFAULT_VALUE_LONG);

        return value;

    }



    /**

     * float 값 로드

     * @param context

     * @param key

     * @return

     */

    public static float getFloat(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        float value = prefs.getFloat(key, DEFAULT_VALUE_FLOAT);

        return value;

    }



    /**

     * 키 값 삭제

     * @param context

     * @param key

     */

    public static void removeKey(Context context, String key) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor edit = prefs.edit();

        edit.remove(key);

        edit.commit();

    }



    /**

     * 모든 저장 데이터 삭제

     * @param context

     */

    public static void clear(Context context) {

        SharedPreferences prefs = getPreferences(context);

        SharedPreferences.Editor edit = prefs.edit();

        edit.clear();

        edit.commit();

    }

}