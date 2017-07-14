package com.yuntian.buglycrash;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.yuntian.test.R;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;

/**
 * Created by yuntian on 2017/7/11.
 */

public class BugCrashUpgrader extends CordovaPlugin {
    private static final String APP_ID = "b962547663";
  
    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        Log.e("SmartLinker", "execute: " + action);
        if ("checkUpgrade".equals(action)) {
            Beta.checkUpgrade();//检查版本更新
            return true;
        }else if ("getVersionName".equals(action)){
            callbackContext.success(getVersionName());
            return true;
        }else if ("initBugly".equals(action)){
            initBugly();
            return true;
        }
            return super.execute(action, args, callbackContext);
        }
    /**
     *获取版本号
     */

    private String getVersionName() {
        PackageManager pm = this.cordova.getActivity().getPackageManager();
        PackageInfo pi;
        String versionName = "";
        try {
            pi = pm.getPackageInfo(this.cordova.getActivity().getPackageName(), 0);
            versionName = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (versionName == null || versionName.length() <= 0) {
            versionName = "";
        }
        return versionName;
    }
    /**
     *初始化bugly函数，启动app的时候调用，仅调用一次
     */
     
    private void initBugly(){
        /***** Beta高级设置 *****/
        /**
         * true表示app启动自动初始化升级模块;
         * false不会自动初始化;
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false，
         * 在后面某个时刻手动调用Beta.init(getApplicationContext(),false);
         */
        Beta.autoInit = true;

        /**
         * true表示初始化时自动检查升级;
         * false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
         */
        Beta.autoCheckUpgrade = true;

        /**
         * 设置升级检查周期为60s(默认检查周期为0s)，60s内SDK不重复向后台请求策略);
         */
        Beta.upgradeCheckPeriod = 60 * 1000;

        /**
         * 设置启动延时为1s（默认延时3s），APP启动1s后初始化SDK，避免影响APP启动速度;
         */
        Beta.initDelay = 1 * 1000;

        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源;
         */
        Beta.largeIconId = R.drawable.ic_launcher;

        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源Id;
         */
        Beta.smallIconId = R.drawable.ic_launcher;

        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
        Beta.defaultBannerId = R.drawable.ic_launcher;

        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = true;
//        添加可显示弹窗的Activity

//        Beta.canShowUpgradeActs.add(MainActivity.class);

        Bugly.init(this.cordova.getActivity(),APP_ID,false);
    }
}
