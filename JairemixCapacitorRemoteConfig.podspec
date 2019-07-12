
  Pod::Spec.new do |s|
    s.name = 'JairemixCapacitorRemoteConfig'
    s.version = '0.0.4'
    s.summary = 'Capacitor wrapper plugin for Firebase Remote Config SDK'
    s.license = 'MIT'
    s.homepage = 'https://github.com/jairemix/capacitor-remote-config'
    s.author = 'Jeremy Li'
    s.source = { :git => 'https://github.com/jairemix/capacitor-remote-config', :tag => s.version.to_s }
    s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
    s.ios.deployment_target  = '11.0'
    s.static_framework = true
    s.dependency 'Capacitor'
    s.dependency 'Firebase/RemoteConfig', '6.2.0'
  end