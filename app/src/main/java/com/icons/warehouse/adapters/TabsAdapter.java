package com.icons.warehouse.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.icons.warehouse.fragments.InventoryFragment;
import com.icons.warehouse.fragments.PaymentsFragment;
import com.icons.warehouse.fragments.ShippingFragment;

public class TabsAdapter extends FragmentStateAdapter {
    public TabsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:return PaymentsFragment.newInstance();
            case 2:return ShippingFragment.newInstance();
            case 0:
            default:return InventoryFragment.newInstance();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
