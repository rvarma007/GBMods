package com.gbmods;

import android.app.Application;
import android.content.Context;

/**
 * Created By Ravi Varma on 23,August,2019
 */
public class App extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        GBMods.init(mContext);   //Add This Code in Ld/d/b/a/a/a/c.smali Under invoke-super {p0}, Landroid/app/Application;->onCreate()V
    }


}
