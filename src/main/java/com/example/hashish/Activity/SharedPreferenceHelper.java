package com.example.hashish.Activity;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private SharedPreferences sharedPreferences;
    public SharedPreferenceHelper(Context context)
    {
        sharedPreferences = context.getSharedPreferences("ProfilePreference", Context.MODE_PRIVATE );
    }
    public void saveProfileName(String name)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("profileName",name );
        editor.commit();
    }
    public void saveAge(int age){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("profileAge", age);
        editor.commit();
    }
    public void savePlant(String namePlant){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("profilePlant", namePlant);
        editor.commit();
    }
    public String getProfileName()
    {
        return sharedPreferences.getString("profileName", null);
    }
    public int getAge(){
        return sharedPreferences.getInt("profileAge",  0);
    }
    public String getPlantName(){
        return sharedPreferences.getString("profilePlant",null);
    }

}
