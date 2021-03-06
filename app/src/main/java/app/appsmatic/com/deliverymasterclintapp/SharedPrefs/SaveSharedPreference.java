package app.appsmatic.com.deliverymasterclintapp.SharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import app.appsmatic.com.deliverymasterclintapp.CartStructure.CartMeal;

/**
 * Created by Mido PC on 9/5/2016.
 */
public class SaveSharedPreference {

    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_PASS = "password";
    static final String LANG_ID="langId";
    static final String lOAD_IMG_ID="imagesStatus";
    static final String CART_ID="cartId";
    static final String OWNER_ID="ownerId";
    static final String CART_ORDERS="cartOrders";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.putString(PREF_USER_PASS, password);
        editor.commit();
    }

    public static void setLangId(Context context,String lang){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LANG_ID,lang);
        editor.commit();
    }

    public static String getLangId(Context context){
        return getSharedPreferences(context).getString(LANG_ID, "");
    }

    public static Boolean getImgLoadingSatatus(Context context){
        return getSharedPreferences(context).getBoolean(lOAD_IMG_ID, true);
    }


    public static void setImgLoadStatus(Context context,Boolean status){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(lOAD_IMG_ID,status);
        editor.commit();
    }



    public static void setCartId(Context context,String lang){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(CART_ID, lang);
        editor.commit();
    }

    public static String getCartId(Context context){
        return getSharedPreferences(context).getString(CART_ID, "");
    }


    public static void setOwnerId(Context context,String ownerId){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(OWNER_ID, ownerId);
        editor.commit();
    }

    public static String getOwnerId(Context context){
        return getSharedPreferences(context).getString(OWNER_ID,"");
    }




    public static void setCartOrders(Context context,List<CartMeal>cartMeals){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
            Gson gson = new Gson();
            String json = gson.toJson(cartMeals);
            editor.putString(CART_ORDERS,json);
            editor.commit();
    }

    public static List<CartMeal> getCartOrders(Context context){
        String json= getSharedPreferences(context).getString(CART_ORDERS, "");
        List<CartMeal> cartMeals=new ArrayList<>();
        if(!json.isEmpty()) {
             Type type = new TypeToken<List<CartMeal>>() {}.getType();
             Gson gson = new Gson();
             cartMeals= gson.fromJson(json, type);
        }
        return cartMeals;
    }

    public static void clearCart(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(CART_ORDERS,"");
    }




    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getUserPassword(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_PASS, "");
    }

    public static void clearUserName(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }





}