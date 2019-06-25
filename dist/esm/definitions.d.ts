declare module '@capacitor/core' {
    interface PluginRegistry {
        RemoteConfigPlugin: RemoteConfigPlugin;
    }
}
export interface RemoteConfigPlugin {
    fetch(options: {
        expirationDuration?: number;
    }): Promise<{}>;
    activateFetched(): Promise<{
        activated: boolean;
    }>;
    getStrings(options: {
        keys: string[];
    }): Promise<{
        values: any[];
    }>;
}
