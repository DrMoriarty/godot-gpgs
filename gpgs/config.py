def can_build(plat):
    return plat == 'android'

def configure(env):
    if env['platform'] == 'android':
	env.android_add_maven_repository("url 'https://maven.google.com'")
        env.android_add_dependency("compile 'com.google.android.gms:play-services-auth:10.2.1'")
        env.android_add_dependency("compile 'com.google.android.gms:play-services-plus:10.2.1'")
        env.android_add_dependency("compile 'com.google.android.gms:play-services-drive:10.2.1'")
        env.android_add_dependency("compile 'com.google.android.gms:play-services-games:10.2.1'")
        env.android_add_java_dir("android")
        env.android_add_to_manifest("android/AndroidManifestChunk.xml")
        env.disable_module()
