package com.icons.warehouse.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {
static int DB_VERSION=1;
static String DB_NAME="warehouse.db";
static String CREATE_INVENTORY_TABLE="CREATE TABLE IF NOT EXISTS Inventory("+
        Tables.PRODUCT_ID+" INTEGER NOT NULL PRIMARY KEY  AUTOINCREMENT ,"+
        Tables.PRODUCT_NAME+" TEXT NOT NULL,"+
        Tables.PRODUCT_QUANTITY+" INTEGER NOT NULL,"+
        Tables.QUANTITY_UNITS+" TEXT NOT NULL)";

static String DROP_TABLE="DROP TABLE IF EXISTS ";


    public Helper(@Nullable Context context) {
        super(context,DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INVENTORY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL(DROP_TABLE+Tables.INVENTORY_TABLE);
onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE+Tables.INVENTORY_TABLE);
        onCreate(db);
    }
}
