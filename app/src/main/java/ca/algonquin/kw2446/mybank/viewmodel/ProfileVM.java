package ca.algonquin.kw2446.mybank.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import ca.algonquin.kw2446.mybank.util.AppUtil;
import ca.algonquin.kw2446.mybank.util.PreferenceManager;


public class ProfileVM extends AndroidViewModel {


    private String name, email, oriPwd, pwd,newPwd, photoString;
    private Bitmap photo;
    public ProfileVM(@NonNull Application application) {
        super(application);
        setName(PreferenceManager.getString(application,"Name"));
        setOriPwd(PreferenceManager.getString(application, "Pwd"));
        setPwd("");
        setEmail(PreferenceManager.getString(application, "Email"));
        setNewPwd("");
        if(!PreferenceManager.getString(application,"Photo").isEmpty()){
            setPhoto(AppUtil.decodeBase64(PreferenceManager.getString(application,"Photo")));
        }

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

    public Bitmap getPhoto() { return photo; }
    public void setPhoto(Bitmap photo) { this.photo = photo; }


    public boolean setProfile(boolean isImgSet, Bitmap bitmap){
        boolean result=false;
        if(name.isEmpty()|| email.isEmpty() ||pwd.isEmpty()){
            Toast.makeText(getApplication(), "Please fill all fields", Toast.LENGTH_SHORT).show();
        }else{
            if(pwd.equalsIgnoreCase(oriPwd)){
                PreferenceManager.setValue(getApplication(),"Name",name);
                if(!newPwd.isEmpty())
                PreferenceManager.setValue(getApplication(),"Pwd",newPwd);
                PreferenceManager.setValue(getApplication(),"Email",email);
                if(isImgSet)PreferenceManager.setValue(getApplication(),"Photo", AppUtil.encodeTobase64(bitmap));
                result=true;

            }
            else{
                Toast.makeText(getApplication(), "The password is not matched!", Toast.LENGTH_SHORT).show();
            }
        }
        return result;
    }
}
