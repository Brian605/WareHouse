package com.icons.warehouse.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.icons.warehouse.models.Inventory;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private Context context;
    private Helper helper;
    private SQLiteDatabase database;

    public DBManager(Context context){
        this.context = context;
    }

    public DBManager open(){
        this.helper=new Helper(context);
        this.database=helper.getWritableDatabase();
        return this;
    }

    public void close(){
        this.database.close();
    }

    public void addInventory(Inventory inventory){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Tables.PRODUCT_NAME,inventory.getProductName());
        contentValues.put(Tables.PRODUCT_QUANTITY,inventory.getQuantity());
        contentValues.put(Tables.QUANTITY_UNITS,inventory.getUnits());
        database.insert(Tables.INVENTORY_TABLE,null,contentValues);
    }

    public List<Inventory> getInventoryList(){
        List<Inventory>inventoryList=new ArrayList<>();
        String[] inventoryColumns=new String[]{Tables.PRODUCT_ID,Tables.PRODUCT_NAME,Tables.PRODUCT_QUANTITY,Tables.QUANTITY_UNITS};
        Cursor cursor=database.query(Tables.INVENTORY_TABLE,inventoryColumns,null,null,null,null,Tables.PRODUCT_QUANTITY);

        if(cursor != null && cursor.moveToFirst()){
do {
    int id=cursor.getInt(cursor.getColumnIndex(Tables.PRODUCT_ID));
    String name=cursor.getString(cursor.getColumnIndex(Tables.PRODUCT_NAME));
    int quantity=cursor.getInt(cursor.getColumnIndex(Tables.PRODUCT_QUANTITY));
    String units=cursor.getString(cursor.getColumnIndex(Tables.QUANTITY_UNITS));
    inventoryList.add(new Inventory(id,quantity,name,units));

}while (cursor.moveToNext());
            cursor.close();
        }

        return inventoryList;
    }

    public void sellProduct(int id,int quantity){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Tables.PRODUCT_QUANTITY,quantity);
        database.update(Tables.INVENTORY_TABLE,contentValues,Tables.PRODUCT_ID+"="+id,null);
    }

    public void deleteProduct(int id){
         database.delete(Tables.INVENTORY_TABLE,Tables.PRODUCT_ID+"="+id,null);
    }

    public void addProduct(int id,int quantity){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Tables.PRODUCT_QUANTITY,quantity);
        database.update(Tables.INVENTORY_TABLE,contentValues,Tables.PRODUCT_ID+"="+id,null);
    }
    public int getAvailableQuantity(int id){
        String query="SELECT "+Tables.PRODUCT_QUANTITY+" FROM "+Tables.INVENTORY_TABLE
                +" WHERE "+Tables.PRODUCT_ID+"="+id;
        Cursor cursor=database.rawQuery(query,null);
        if (cursor!=null && cursor.moveToFirst()){
            int availableQuantity=cursor.getInt(cursor.getColumnIndex(Tables.PRODUCT_QUANTITY));
            cursor.close();
            return availableQuantity;
        }
        return 0;
    }
}
