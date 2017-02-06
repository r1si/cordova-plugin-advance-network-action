package com.cordova.r1si.advance.network;

import org.apache.cordova.*;
import java.io.*;
import java.net.*;
import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;
import android.content.Context;
import android.app.Activity;
import android.os.Message;
import android.telephony.TelephonyManager;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class advance_network extends CordovaPlugin {
	private static final String TAG = "CordovaShellExecute";

	@Override
	public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

		Context context = this.cordova.getActivity().getApplicationContext();
		/*
		if (action.equals("getDeviceNetwokActivity")){
			callbackContext.success(String.valueOf(getDeviceNetwokActivity(context)));
			return true;
		}else if(action.equals("setDeviceNetwork"){
			callbackContext.success(String.valueOf(setDeviceNetwork(context)));
		}else{
			callbackContext.error("errore");
			return false;
		}
		*/
		if (action.equals("setDeviceNetwork")){
			callbackContext.success("debug");
//			callbackContext.success(String.valueOf(setDeviceNetwork(context,"2G")));
			return true;
		}else{
			callbackContext.error("errore");
			return false;
		}
	}

	/**
	 * Function that change the preffered network
	 * @param Context context - The context of application
	 * @param String state_network - State that you want change ["2G" "3G" "4G" "2G/3G" "2G/3G/4G"]
	 * @return boolean
	 */
	public static String setDeviceNetwork(Context context, String state_network){
		try {
			Process process = null;
			process = Runtime.getRuntime().exec("su");

			TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			try{
				Class<?> forName = Class.forName("com.android.internal.telephony.PhoneFactory");
				try {
					Method getDefaultPhone = forName.getMethod("getDefaultPhone", new Class[]{});
					try {
						Object mPhone = getDefaultPhone.invoke(null, new Object[]{});
						Method setPreferredNetworkType = mPhone.getClass().getMethod("setPreferredNetworkType", new Class[]{int.class, Message.class});
						Method getPreferredNetworkType = mPhone.getClass().getMethod("getPreferredNetworkType", new Class[]{Message.class});

						setPreferredNetworkType.invoke(mPhone, new Object[]{1, "1"});
					}catch (IllegalAccessException e){
						return "Errore IllegalAccessException on run command change network";
					}catch (InvocationTargetException e){
						return "Errore InvocationTargetException on run command change network";
					}
				}catch (NoSuchMethodException e){
					return "Errore NoSuchMethodException on find method";
				}
			}catch (ClassNotFoundException e){
				return "Errore ClassNotFoundException on find class";
			}



//				Method m1 = tm.getClass().getDeclaredMethod("getITelephony");
//				m1.setAccessible(true);
//				Object iTelephony = m1.invoke(tm);

//			Method m4 = iTelephony.getClass().getDeclaredMethod("setPreferredNetworkType",);

			// m2.invoke(iTelephony);
			// m3.invoke(iTelephony);
//			m4.invoke(iTelephony,2);
			process.waitFor();

		} catch (IOException e) {
			e.printStackTrace();
			return "Errore IOException on sudo command";
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "Errore InterruptException on sudo command";
		}

		return "true";
	}

	/**
	 * Function that get the current device netwok activity
	 * @param Context context - The context of application
	 * @return int - [0,1,2,3,4,5,6]
	 */
	public static int getDeviceNetwokActivity(Context context){
		TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE); // TelehponyManager
		return  manager.getDataActivity();
	}

	/**
	 * Function to close Andorid Socket
	 * @param xs
	 */
	public static void closeSilently(Object... xs) {
		// Note: on Android API levels prior to 19 Socket does not implement Closeable
		for (Object x : xs) {
			if (x != null) {
				try {
					Log.d(TAG,"closing: "+x);
					if (x instanceof Closeable) {
						((Closeable)x).close();
					} else if (x instanceof Socket) {
						((Socket)x).close();
					} else if (x instanceof DatagramSocket) {
						((DatagramSocket)x).close();
					} else {
						Log.d(TAG,"cannot close: "+x);
						throw new RuntimeException("cannot close "+x);
					}
				} catch (Throwable e) {
					Log.e(TAG,"Closer failed");
				}
			}
		}
	}
}