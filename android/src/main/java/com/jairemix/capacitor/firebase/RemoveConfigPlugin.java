package com.jairemix.capacitor.firebase;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;

@NativePlugin()
public class RemoteConfigPlugin extends Plugin {

    private static final String PLUGIN_TAG = "CapacitorRemoteConfig";

    @PluginMethod()
    public void get(PluginCall call) {

        String keys = call.getString("keys");

        if (!keys) {
            call.error("[GET] NO_KEYS");
        }

        Log.d(PLUGIN_TAG, "get() keys " + keys.toString());

        call.success();
    }

}
