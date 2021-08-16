package com.icons.warehouse;

import android.Manifest;
import android.app.Application;
import android.widget.Toast;

import com.icons.warehouse.db.DBManager;
import com.icons.warehouse.models.Inventory;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class WareHouse extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Dexter.withContext(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
if (!multiplePermissionsReport.areAllPermissionsGranted()){
    Toast.makeText(getApplicationContext(), "You need to allow all permissions for the app to work correctly", Toast.LENGTH_LONG).show();
}
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
permissionToken.continuePermissionRequest();
                    }
                }).check();
    }


    public interface InventoryListener{
        default void onInventories(List<Inventory> inventoryList){

        }
    }
}
