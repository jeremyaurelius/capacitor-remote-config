declare module '@capacitor/core' {
  interface PluginRegistry {
    RemoteConfigPlugin: RemoteConfigPlugin;
  }
}

export interface RemoteConfigPlugin {

  fetch(options: {
    expirationDuration?: number,
  }): Promise<{
    status: FetchStatus
  }>;

  activateFetched(): Promise<{ activated: boolean }>

  getStrings(options: {
    keys: string[],
  }): Promise<{
    values: any[]
  }>;

  getLastFetchStatus(): Promise<{
    status: FetchStatus
  }>;

  /**
   * get last time fetch made, formatted as an ISO date
   */
  getLastFetchTime(): Promise<{
    lastFetchTime: string; 
  }>;

}

export type FetchStatus = 
  'noFetchYet' |
  'success' |
  'throttled' |
  'failure';
