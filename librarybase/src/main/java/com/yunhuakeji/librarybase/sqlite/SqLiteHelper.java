package com.yunhuakeji.librarybase.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yunhuakeji.librarybase.base.BaseApplication;
import com.yunhuakeji.librarybase.util.ZLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yunhuakeji.librarybase.sqlite.SqLiteVersionAndName.SQ_LITE_NAME;

/**
 * description:SqLite数据库数据操作类
 * author:created by Andy on 2019/6/25 0025 11:56
 * email:zsp872126510@gmail.com
 */
public class SqLiteHelper implements ISqLiteDBOperate {

    private Context context;
    private int version = SqLiteVersionAndName.sqLiteVersion;

    private SqLiteHelper() {
        this.context = BaseApplication.getInstance();
    }

    private static class SqLiteHolder {
        private final static SqLiteHelper INSTANCE = new SqLiteHelper();
    }

    public static SqLiteHelper getInstance() {
        return SqLiteHolder.INSTANCE;
    }

    /**
     * @Description:删除表数据
     * @Author:Andy
     * @Date:2019/6/25 0025 13:59
     */
    public void deleteTable(String table) {
        rawQuery("delete from " + table);
    }

    public List<Map<String, String>> rawQuery(String sql) {
        List<Map<String, String>> retVal = new ArrayList<Map<String, String>>();
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = getRead();
            c = db.rawQuery(sql, new String[]{});
            String[] s = c.getColumnNames();
            while (c.moveToNext()) {
                Map<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < s.length; i++) {
                    map.put(s[i], c.getString(i));
                }
                retVal.add(map);
            }
        } catch (Exception e) {
            ZLog.e(e.toString());
        } finally {
            close(db, c);
        }
        return retVal;
    }

    public boolean execSQL(String sql) {
        SQLiteDatabase db = null;
        try {
            db = getWrite();
            db.execSQL(sql);
            return true;
        } catch (Exception e) {
            ZLog.e(e.toString());
            return false;
        } finally {
            close(db, null);
        }
    }

    public boolean execSQL(String sql, Object... cs) {
        SQLiteDatabase db = null;
        try {
            db = getWrite();
            db.execSQL(sql, cs);
            return true;
        } catch (Exception e) {
            ZLog.e(e.toString());
            return false;
        } finally {
            close(db, null);
        }
    }

    public boolean execSQL(String sql, List<Object[]> cs) {
        SQLiteDatabase db = null;
        try {
            db = getWrite();
            for (int i = 0, length = cs.size(); i < length; i++) {
                db.execSQL(sql, cs.get(i));
            }
            return true;
        } catch (Exception e) {
            ZLog.e(e.toString());
        } finally {
            close(db, null);
        }
        return false;
    }

    public List<Map<String, String>> rawQuery(SQLiteDatabase db, String sql) {
        List<Map<String, String>> retVal = new ArrayList<Map<String, String>>();
        Cursor c = null;
        try {
            c = db.rawQuery(sql, new String[]{});
            String[] s = c.getColumnNames();
            while (c.moveToNext()) {
                Map<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < s.length; i++) {
                    map.put(s[i], c.getString(i));
                }
                retVal.add(map);
            }
        } catch (Exception e) {
            ZLog.e(e.toString());
        } finally {
            close(db, c);
        }
        return retVal;
    }

    public List<Map<String, String>> rawQuery(String sql, String... parames) {
        List<Map<String, String>> retVal = new ArrayList<Map<String, String>>();
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = getRead();
            c = db.rawQuery(sql, parames);
            String[] s = c.getColumnNames();
            while (c.moveToNext()) {
                Map<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < s.length; i++) {
                    map.put(s[i], c.getString(i));
                }
                retVal.add(map);
            }
        } catch (Exception e) {
            ZLog.e(e.toString());
        } finally {
            close(db, c);
        }
        return retVal;
    }

    public List<Map<String, String>> rawQuery(String sql, SQLiteDatabase db) {
        List<Map<String, String>> retVal = new ArrayList<Map<String, String>>();
        Cursor c = null;
        try {
            c = db.rawQuery(sql, new String[]{});
            String[] s = c.getColumnNames();
            while (c.moveToNext()) {
                Map<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < s.length; i++) {
                    map.put(s[i], c.getString(i));
                }
                retVal.add(map);
            }
        } catch (Exception e) {
            ZLog.e(e.toString());
        } finally {
            close(db, c);
        }
        return retVal;
    }

    public List<Map<String, String>> rawQuery(Cursor c) {
        List<Map<String, String>> retVal = new ArrayList<Map<String, String>>();
        String[] s = c.getColumnNames();
        while (c.moveToNext()) {
            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < s.length; i++) {
                map.put(s[i], c.getString(i));
            }
            retVal.add(map);
        }
        try {
            c.close();
        } catch (Exception e) {
            ZLog.e(e.toString());
        }
        return retVal;
    }

    public boolean insert(String sql, List<Object[]> parames) {
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = getRead();
            for (int i = 0; parames != null && i < parames.size(); i++)
                db.execSQL(sql, parames.get(i));
            return true;
        } catch (Exception e) {
            ZLog.e(e.toString());
            return false;
        } finally {
            close(db, c);
        }
    }

    @Override
    public void close(SQLiteDatabase db, Cursor c) {
        try {
            if (c != null)
                c.close();
            if (db != null && db.isOpen())
                db.close();
        } catch (Exception e) {
            ZLog.d("close: " + e);
        }
    }

    @Override
    public SQLiteDatabase getRead() {
        return new DatabaseTable(context, SQ_LITE_NAME, version).getReadableDatabase();
    }

    @Override
    public SQLiteDatabase getWrite() {
        return new DatabaseTable(context, SQ_LITE_NAME, version).getWritableDatabase();
    }
}
