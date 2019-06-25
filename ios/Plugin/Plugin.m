#import <Foundation/Foundation.h>
#import <Capacitor/Capacitor.h>

// Define the plugin using the CAP_PLUGIN Macro, and
// each method the plugin supports using the CAP_PLUGIN_METHOD macro.
CAP_PLUGIN(RemoteConfigPlugin, "RemoteConfigPlugin",
           CAP_PLUGIN_METHOD(fetch, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(activateFetched, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(getStrings, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(getInfo, CAPPluginReturnPromise);
)
