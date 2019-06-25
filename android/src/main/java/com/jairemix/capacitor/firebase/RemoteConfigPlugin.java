package com.jairemix.capacitor.firebase;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@NativePlugin()
public class RemoteConfigPlugin extends Plugin {

  private static final String PLUGIN_TAG = "CapacitorRemoteConfig";

  @PluginMethod()
  public void useAndroidSettings(PluginCall call) {
    boolean developerMode = call.getBoolean("developerMode", false);
    Log.d(PLUGIN_TAG, "developerMode " + developerMode);
    FirebaseRemoteConfigSettings settings = new FirebaseRemoteConfigSettings.Builder()
            .setDeveloperModeEnabled(developerMode)
            .build();
    FirebaseRemoteConfig.getInstance().setConfigSettings(settings);
    call.success();
  }

  @PluginMethod()
  public void fetch(final PluginCall call) {

    long expirationDuration = call.getInt("expirationDuration", 0);

//    Log.d(PLUGIN_TAG, "fetch(). expirationDuration: " + expirationDuration);

    final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();

    OnCompleteListener listener = new OnCompleteListener<Void>() {
      @Override
      public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful()) {
          call.success();
        } else {
          Exception e = task.getException();
          call.error(e.getMessage(), e);
        }
      }
    };

    if (expirationDuration > 0) {
      remoteConfig.fetch(expirationDuration).addOnCompleteListener(listener);
    } else {
      remoteConfig.fetch().addOnCompleteListener(listener);
    }

  }

  @PluginMethod()
  public void activateFetched(PluginCall call) {
//    Log.d(PLUGIN_TAG, "activateFetched()");
    boolean activated = FirebaseRemoteConfig.getInstance().activateFetched();
    call.success(new JSObject().put("activated", activated));
  }

  @PluginMethod()
  public void getStrings(PluginCall call) {

    JSArray keys = call.getArray("keys");

    if (keys == null || keys.length() == 0) {
      call.error("[GET] NO_KEYS");
    }

//    Log.d(PLUGIN_TAG, "get() keys: " + keys.toString());

    FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();

    try {
      List<String> keyList = keys.toList();
      JSArray values = new JSArray();
      for (String key : keyList) {
        values.put(remoteConfig.getString(key));
      }
      call.success(new JSObject().put("values", values));
    } catch (JSONException e) {
      call.error(e.getMessage(), e);
    }

  }

  @PluginMethod()
  public void getInfo(PluginCall call) {
    FirebaseRemoteConfigInfo info = FirebaseRemoteConfig.getInstance().getInfo();
    String lastFetchStatus = this.statusToString(info.getLastFetchStatus());
    String lastFetchTime = this.dateToISOString(new Date(info.getFetchTimeMillis()));
    call.success(
            new JSObject()
                    .put("lastFetchStatus", lastFetchStatus)
                    .put("lastFetchTime", lastFetchTime)
    );
  }

  private String statusToString(int status) {
    switch (status) {
      case FirebaseRemoteConfig.LAST_FETCH_STATUS_NO_FETCH_YET: return "noFetchYet";
      case FirebaseRemoteConfig.LAST_FETCH_STATUS_SUCCESS: return "success";
      case FirebaseRemoteConfig.LAST_FETCH_STATUS_FAILURE: return "failure";
      case FirebaseRemoteConfig.LAST_FETCH_STATUS_THROTTLED: return "throttled";
      default: return "";
    }
  }

  private String dateToISOString(Date date) {
    SimpleDateFormat simpleDateFormat;
    try {
      simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    } catch (Exception e) {
      simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ");
    }
    return simpleDateFormat.format(date);
  }
  
}
