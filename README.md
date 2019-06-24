# capacitor-remote-config
- Capacitor wrapper plugin for Firebase Remote Config SDK.
- Only implemented on iOS and Android

## Installation

#### yarn
```
$ yarn add @jairemix/capacitor-remote-config
$ yarn cap sync
```

#### npm
```
$ npm install @jairemix/capacitor-remote-config
$ npx cap sync
```
### Android only
After installing and syncing the Capacitor plugin, remember to add it to `MainActivity.java`
```java
// Other Imports
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
