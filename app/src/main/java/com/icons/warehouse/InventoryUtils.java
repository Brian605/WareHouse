package com.icons.warehouse;

import android.content.Context;

import com.icons.warehouse.db.DBManager;

public class InventoryUtils {

    public void fetchInventoryList(Context context,WareHouse.InventoryListener listener){
        DBManager manager=new DBManager(context).open();
        listener.onInventories(manager.getInventoryList());
        manager.close();
    }
}
