package com.yunhuakeji.librarybase.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * description:sqlite相关操作接口
 * author:created by Andy on 2019/6/25 0025 11:55
 * email:zsp872126510@gmail.com
 */
public interface ISqLiteDBOperate {
    //关闭数据库
    void close(SQLiteDatabase db, Cursor c);
    //读取数据库
    SQLiteDatabase getRead();
    //写入数据库
    SQLiteDatabase getWrite();
}
