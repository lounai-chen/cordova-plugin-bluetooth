package com.zzl.intelligence;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * This class echoes a string called from JavaScript.
 */
public class BluetoothStatusPlugin extends CordovaPlugin {

    private static final String LOG_TAG = "BleStatusManager";

    BroadcastReceiver receiver;

    private CallbackContext BleStatusCallbackContext = null;

    private BluetoothAdapter ba;

    public BluetoothStatusPlugin(){
        this.receiver = null;
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("start")) {
            if(this.BleStatusCallbackContext != null){
                removeBleStatusListener();
            }
            this.BleStatusCallbackContext = callbackContext;
            this.ba = BluetoothAdapter.getDefaultAdapter();

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
            if(this.receiver == null){
                this.receiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        updateBleStatusInfo();
                    }
                };
                webView.getContext().registerReceiver(this.receiver, intentFilter);
            }
            updateBleStatusInfo();
            return true;
        }
        else if(action.equals("stop")){
            removeBleStatusListener();
            this.sendUpdate("", false); // release status callback in JS side
            this.BleStatusCallbackContext = null;
            callbackContext.success();
            return true;
        }
        return false;
    }

    public void onDestroy() {
        removeBleStatusListener();
    }

    /**
     * Stop battery receiver.
     */
    public void onReset() {
        removeBleStatusListener();
    }

    private void updateBleStatusInfo() {
        boolean isConn = false;
        if(ba != null && ba.isEnabled()){
            int headset = ba.getProfileConnectionState(BluetoothProfile.HEADSET);
            if(headset == BluetoothProfile.STATE_CONNECTED){
                isConn = true;
            }
        }
        sendUpdate(Boolean.toString(isConn), true);
    }

    private void sendUpdate(String message, boolean keepCallback) {
        if (this.BleStatusCallbackContext != null) {
            PluginResult result = new PluginResult(PluginResult.Status.OK, message);
            result.setKeepCallback(keepCallback);
            this.BleStatusCallbackContext.sendPluginResult(result);
        }
    }

    private void removeBleStatusListener() {
        if (this.receiver != null) {
            try {
                webView.getContext().unregisterReceiver(this.receiver);
                this.receiver = null;
            } catch (Exception e) {
                LOG.e(LOG_TAG, "Error unregistering ble status receiver: " + e.getMessage(), e);
            }
        }
    }

}
