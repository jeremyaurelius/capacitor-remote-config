import Foundation
import Capacitor
import FirebaseRemoteConfig

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitor.ionicframework.com/docs/plugins/ios
 */
@objc(RemoteConfigPlugin)
public class RemoteConfigPlugin: CAPPlugin {
  
  let remoteConfig = RemoteConfig.remoteConfig();
    
  @objc func fetch(_ call: CAPPluginCall) {
    remoteConfig.fetch { (status: RemoteConfigFetchStatus, error: Error?) in
      if (error == nil) {
        print("[CapacitorRemoteConfig] fetch");
        call.success();
      } else {
        call.error(error!.localizedDescription, error!);
      }
    }
  }
  
  @objc func activateFetched(_ call: CAPPluginCall) {
    let activated = remoteConfig.activateFetched();
    print("[CapacitorRemoteConfig] activateFetched. activated " + activated.description);
    call.success([
      "activated": activated
    ]);
  }
  
  @objc func getStrings(_ call: CAPPluginCall) {
    let keys = call.getArray("keys", String.self);
    print("[CapacitorRemoteConfig] getStrings. keys: " + keys.debugDescription);
    
    if (keys == nil || keys!.count == 0) {
      call.error("[GET_STRINGS] NO_KEYS")
      return;
    }
    
    let values = keys!.map({ (key: String) -> String in
      let value = remoteConfig.configValue(forKey: key);
      return value.stringValue ?? "";
    });
    
    call.success([
      "values": values
    ]);
    
  }
    
}
