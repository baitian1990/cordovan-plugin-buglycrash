# cordovan-plugin-buglycrash
A cordova plugin for platform of android ,it is about buglycrashupdate of tencent.
#### 方法：checkUpgrade、getVersionName、initBugly； 对象名cordova.plugins.CrashUpgrader
#### initBugly（）：程序启动时调用，用来初始化插件
#### checkUpgrade（）：需要手动检查更新时调用，会检测更新，并提醒用户更新
#### getVersionName（）：用来获取当前版本的版本名
#### 使用前需要修改BugCrashUpgrader.java中的import com.example.helloworld.R;
#### R前面的包名改为AndroidManifest文件中package属性所对应的包名
