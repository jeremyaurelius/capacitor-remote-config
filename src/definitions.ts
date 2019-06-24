declare module '@capacitor/core' {
  interface PluginRegistry {
    RemoteConfigPlugin: RemoteConfigPlugin;
  }
}

export interface RemoteConfigPlugin {
  get(options: {
    keys: string[],
  }): Promise<{
    values: any[]
  }>;
}
