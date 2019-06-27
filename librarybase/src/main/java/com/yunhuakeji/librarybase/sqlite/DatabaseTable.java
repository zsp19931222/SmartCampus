package com.yunhuakeji.librarybase.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * description:本地缓存数据库表
 * author:created by Andy on 2019/6/25 0025 11:49
 * email:zsp872126510@gmail.com
 */
public class DatabaseTable extends SQLiteOpenHelper {

    DatabaseTable(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        update_Sql_0_to_1(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = oldVersion; i < newVersion; i++) {
            switch (i) {
                case 1:
                    break;
            }
        }
    }

    private void update_Sql_0_to_1(SQLiteDatabase database) {
    }
}
