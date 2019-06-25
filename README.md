# capacitor-remote-config
- Capacitor wrapper plugin for Firebase Remote Config SDK.
- Only implemented on iOS and Android

## Installation

### iOS
Add your `GoogleService-Info.plist` file to the `ios/App/App` folder.

### Android
Add your `google-services.json` file to the `android/App` folder.
After installing and syncing the plugin through yarn/npm, add the plugin to `MainActivity.java`.
```java
// ... Other Imports
import com.jairemix.capacitor.firebase.RemoteConfigPlugin;

public class MainActivity extends BridgeActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Initializes the Bridge
    this.init(savedInstanceState, new ArrayList<Class<? extends Plugin>>() {{
      add(RemoteConfigPlugin.class);
      // Additional plugins you've installed go here
    }});
  }

}
```
