package com.icons.warehouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.icons.warehouse.adapters.TabsAdapter;
import com.icons.warehouse.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private MaterialToolbar toolbar;
    private ActivityMainBinding binding;
    private TabsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPager2=binding.viewPager;
        tabLayout=binding.tabLayout;
        toolbar=binding.toolbar;

        String[] tabNames=getResources().getStringArray(R.array.tabs);
        int[] icons=new int[]{R.drawable.ic_baseline_list_alt_24,R.drawable.ic_baseline_money_24,
                R.drawable.ic_baseline_local_shipping_24};

        adapter=new TabsAdapter(MainActivity.this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout,
                viewPager2,
                true,
                true,
                (tab, position) -> {
tab.setText(tabNames[position]);
tab.setIcon(icons[position]);
                }).attach();

    }

    public void recreateActivity(){
        recreate();
    }
}