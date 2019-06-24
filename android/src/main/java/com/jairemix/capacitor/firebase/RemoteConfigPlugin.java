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
  public void fetch(PluginCall call) {

    Log.d(PLUGIN_TAG, "fetch()");

    call.success();
  }

  @PluginMethod()
  public void activateFetched(PluginCall call) {

    Log.d(PLUGIN_TAG, "activateFetched()");

    call.success();
  }

  @PluginMethod()
  public void getStrings(PluginCall call) {

    String keys = call.getString("keys");

    if (keys == null || keys.length == 0) {
      call.error("[GET] NO_KEYS");
    }

    Log.d(PLUGIN_TAG, "get() keys: " + keys.toString());

    call.success();
  }

}
