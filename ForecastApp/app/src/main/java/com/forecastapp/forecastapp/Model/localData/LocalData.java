package com.forecastapp.forecastapp.Model.localData;

import android.content.Context;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * Created by MelDiSooQi on 3/28/2016.
 */

public class LocalData
{
    //To add in local Storage
    //===================================================================================================================
    public static String PreferenceTypeBoolean	= "Boolean";
    public static String PreferenceTypeFloat	= "Float";
    public static String PreferenceTypeInt		= "Int";
    public static String PreferenceTypeLong		= "Long";
    public static String PreferenceTypeString	= "String";
    public static String PreferenceTypeStringSet= "StringSet";

    public static boolean setPreference(Context context, String type, String key,  Object value)
    {
        if(StringEqual(type, PreferenceTypeBoolean))
        {
            return PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean	(key, (Boolean) value).commit();
        }
        else if(StringEqual(type, PreferenceTypeFloat))
        {
            return PreferenceManager.getDefaultSharedPreferences(context).edit().putFloat(key, (Float) value).commit();
        }
        else if(StringEqual(type, PreferenceTypeInt))
        {
            return PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, (Integer) value).commit();
        }
        else if(StringEqual(type, PreferenceTypeLong))
        {
            return PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(key, (Long) value).commit();
        }
        else if(StringEqual(type, PreferenceTypeString))
        {
            return PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, (String) value).commit();
        }
        else if(StringEqual(type, PreferenceTypeStringSet))
        {
            return PreferenceManager.getDefaultSharedPreferences(context).edit().putStringSet(key, (Set<String>) value).commit();
        }
        else
        {
            return false;
        }
    }

    //888888888888888888888888888888888888888888888888888888888
    public static Object getPreference(Context context, String type, String key)
    {
        if(StringEqual(type, PreferenceTypeBoolean))
        {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, false);
        }
        else if(StringEqual(type, PreferenceTypeFloat))
        {
            return PreferenceManager.getDefaultSharedPreferences(context).getFloat(key, 0);
        }
        else if(StringEqual(type, PreferenceTypeInt))
        {
            return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, 0);
        }
        else if(StringEqual(type, PreferenceTypeLong))
        {
            return PreferenceManager.getDefaultSharedPreferences(context).getLong(key, 0);
        }
        else if(StringEqual(type, PreferenceTypeString))
        {
            //null == "defaultStringIfNothingFound"
            return PreferenceManager.getDefaultSharedPreferences(context).getString(key, null);
        }
        else if(StringEqual(type, PreferenceTypeStringSet))
        {
            return PreferenceManager.getDefaultSharedPreferences(context).getStringSet(key, null);
        }
        else
        {
            return null;
        }
    }
    //888888888888888888888888888888888888888888888888888888888
    public static boolean removePreference(Context context, String key)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).edit().remove(key).commit();
    }
    //888888888888888888888888888888888888888888888888888888888
    public static boolean ClearPreference(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).edit().clear().commit();
    }
    //888888888888888888888888888888888888888888888888888888888
    public static boolean ClearPreferenceByApply(Context context)
    {
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
        return true;
    }

    private static boolean StringEqual(String str1, String Str2)
    {
        if(str1.contentEquals(Str2))
        {
            return true;
        }else
        {
            return false;
        }
    }
//===================================================================================================================
}