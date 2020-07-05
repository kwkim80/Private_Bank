package ca.algonquin.kw2446.mybank.util;

import android.app.Application;

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
}
