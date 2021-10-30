package com.mobraysol.mobashshir.raysol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mobashshir on 8/11/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Login.db";
    public static final String TABLE_NAME = "Logintabble";
    public static final String LoginIdValueNew = "LoginId";
    public static final String Usernamenew = "Username";
    public static final String Passwordnew = "Password";
    public static final String UserPhonenonew = "UserPhoneno";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (LoginId INTEGER PRIMARY KEY, Username TEXT, Password TEXT, UserPhoneno TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertDataLogin(int LoginId, String Username, String Password, String UserPhoneno)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LoginIdValueNew, LoginId);
        contentValues.put(Usernamenew, Username);
        contentValues.put(Passwordnew, Password);
        contentValues.put(UserPhonenonew, UserPhoneno);
        long rowvalue = db.insert(TABLE_NAME, null, contentValues);
        if(rowvalue != -1)
            return  true;
        else
            return false;
    }

    public boolean updateData(String Loginid, String Username, String Password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LoginIdValueNew, Loginid);
        contentValues.put(Username, Username);
        contentValues.put(Password, Password);

        db.update(TABLE_NAME, contentValues, "id = ?", new String[] {Loginid});
        return  true;
    }

    public Cursor getLoginData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select LoginId, Username, Password, UserPhoneno from " + TABLE_NAME, null);
        return  res;
    }

    public Integer deleteData(String LoginId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "LoginId = ?", new String[] {LoginId});
    }

    public  void ShowdataLoginSqlite()
    {
        Cursor res = this.getLoginData();
        if(res.getCount() > 0){
            StringBuffer stringBuffer = new StringBuffer();
            while (res.moveToNext()){
                stringBuffer.append("LoginId : " + res.getString(0) + "\n");
                stringBuffer.append("Username : " + res.getString(1) + "\n");
                stringBuffer.append("Password : " + res.getString(2) + "\n");
                stringBuffer.append("UserPhoneno : " + res.getString(3) + "\n");
            }
        }
    }
}
