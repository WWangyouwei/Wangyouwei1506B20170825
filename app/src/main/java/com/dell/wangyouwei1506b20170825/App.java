package com.dell.wangyouwei1506b20170825;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * 姓名：王有为
 * 时间：2017/8/25.
 */

public class App extends Application {

    private DbManager dbManager;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        DbManager.DaoConfig config = new DbManager.DaoConfig();
        config.setDbName("yuekao.db");
        config.setDbVersion(1);
        dbManager =  x.getDb(config);
    }

    public DbManager getDbManager() {
        return dbManager;
    }
}
