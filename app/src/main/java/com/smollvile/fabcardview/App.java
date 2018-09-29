package com.smollvile.fabcardview;


import android.app.Application;

import com.smollvile.fabcardview.dao.DaoMaster;
import com.smollvile.fabcardview.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application{

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes_db");
        Database database = helper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
