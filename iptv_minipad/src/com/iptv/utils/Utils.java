package com.iptv.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class Utils {

	private static SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
	public static String getLocalMacAddressFromIp(Context context) {
		String uniqueId="b3e18e619ffc8689";
		try {
			uniqueId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
			Log.d("debug", "uuid=" + uniqueId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uniqueId;
	}

	

	public static String md5(String str, String charset) {
		try {
			byte[] tmpInput = null;
			if (null != str) {
				if (null != charset) {
					tmpInput = str.getBytes(charset);
				} else {
					tmpInput = str.getBytes();
				}
			} else {
				return null;
			}
			MessageDigest alg = MessageDigest.getInstance("MD5"); // or "SHA-1"
			alg.update(tmpInput);
			return byte1hex(alg.digest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static String byte1hex(byte[] inputByte) {
		if (null == inputByte) {
			return null;
		}
		String resultStr = "";
		String tmpStr = "";
		for (int n = 0; n < inputByte.length; n++) {
			tmpStr = (Integer.toHexString(inputByte[n] & 0XFF));
			if (tmpStr.length() == 1) {
				resultStr = resultStr + "0" + tmpStr;
			} else {
				resultStr = resultStr + tmpStr;
			}
		}
		return resultStr.toUpperCase();
	}


	public static int pageleft(int start, int end, int count) {
		int vcount = end - start;
		if ((start-vcount)>0) {
				return (start-vcount-1);
		}else{
			return 0;
		}
	}
	public static int pageright(int start, int end, int count) {
		if (end+1==count) {
				return end;
		}else{
			return end+1;
		}
	}
	public static int getversioncode(Context context){
		PackageManager manager = context.getPackageManager();
		try {
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			return info.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public static String longToString(long time){
		
		return sdf.format(time);
	}
	public static void setConfig(Context context,String key,String value){
		SharedPreferences sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		Editor  editor=sp.edit();
		editor.putString(key, value);
		editor.commit();
	}
	public static String getConfig(Context context,String key,String def){
		SharedPreferences sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getString(key, def);
	}
	public static void setConfig(Context context,String key,int value){
		SharedPreferences sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		Editor  editor=sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	public static int getConfig(Context context,String key,int def){
		SharedPreferences sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getInt(key, def);
	}
	public static void setConfig(Context context,String key,boolean value){
		SharedPreferences sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		Editor  editor=sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	public static boolean getConfig(Context context,String key,boolean def){
		SharedPreferences sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getBoolean(key, def);
	}
	
}
