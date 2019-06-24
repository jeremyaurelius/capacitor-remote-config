declare module '@capacitor/core' {
    interface PluginRegistry {
        RemoteConfigPlugin: RemoteConfigPlugin;
    }
}
export interface RemoteConfigPlugin {
    fetch(): Promise<{}>;
    activateFetched(): Promise<{
        activated: boolean;
    }>;
    getStrings(options: {
        keys: string[];
    }): Promise<{
        values: any[];
    }>;
}
