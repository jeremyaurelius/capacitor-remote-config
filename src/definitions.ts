declare module '@capacitor/core' {
  interface PluginRegistry {
    RemoteConfigPlugin: RemoteConfigPlugin;
  }
}

export interface RemoteConfigPlugin {

  fetch(options?: {
    expirationDuration?: number,
  }): Promise<{}>;

  activateFetched(): Promise<{ activated: boolean }>

  getStrings(options: {
    keys: string[],
  }): Promise<{
    values: string[],
  }>;

  getInfo(): Promise<{
    lastFetchStatus: FetchStatus,
    /**
     * time last fetch made, formatted as an ISO date
     */
    lastFetchTime: string,
  }>;

  useAndroidSettings?(options: {
    developerMode?: boolean,
  }): Promise<{}>;

}

export type FetchStatus = 
  'noFetchYet' |
  'success' |
  'throttled' |
  'failure';
