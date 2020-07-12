package ca.algonquin.kw2446.mybank.util;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ApplicationClass extends Application {


    
    @Override
    public void onCreate() {
        super.onCreate();

        if(PreferenceManager.getString( getApplicationContext(),"Pwd")=="" ){
           PreferenceManager.setValue(getApplicationContext(),"Pwd","0000");
           PreferenceManager.setValue(getApplicationContext(),"Name","Client");
           PreferenceManager.setValue(getApplicationContext(),"Email","");

        }
    }

    public static boolean connectionAvailable(Context context) {
        int[] networkTypes = {ConnectivityManager.TYPE_MOBILE,
                ConnectivityManager.TYPE_WIFI};
        try {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            for (int networkType : networkTypes) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null &&
                        activeNetworkInfo.getType() == networkType)
                    return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
