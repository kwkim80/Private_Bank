package ca.algonquin.kw2446.mybank.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import ca.algonquin.kw2446.mybank.util.PreferenceManager;


public class ProfileVM extends AndroidViewModel {


    private String name, email, oriPwd, pwd,newPwd;
    public ProfileVM(@NonNull Application application) {
        super(application);
        setName(PreferenceManager.getString(application,"Name"));
        setOriPwd(PreferenceManager.getString(application, "Pwd"));
        setPwd("");
        setEmail(PreferenceManager.getString(application, "Email"));
        setNewPwd("");
    }

    public String getName() { return name;}
    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPwd() {return pwd;}
    public void setPwd(String pwd) {this.pwd = pwd;}

    public String getNewPwd() {return newPwd;}
    public void setNewPwd(String newPwd) {this.newPwd = newPwd;}

    public String getOriPwd() {return oriPwd;}
    public void setOriPwd(String oriPwd) {this.oriPwd = oriPwd;}

    public boolean setProfile(){
        boolean result=false;
        if(name.isEmpty()|| email.isEmpty() ||pwd.isEmpty()){
            Toast.makeText(getApplication(), "Please fill all fields", Toast.LENGTH_SHORT).show();
        }else{
            if(pwd.equalsIgnoreCase(oriPwd)){
                PreferenceManager.setValue(getApplication(),"Name",name);
                if(!newPwd.isEmpty())
                PreferenceManager.setValue(getApplication(),"Pwd",newPwd);
                PreferenceManager.setValue(getApplication(),"Email",email);
                result=true;

            }
        }
        return result;
    }
}
