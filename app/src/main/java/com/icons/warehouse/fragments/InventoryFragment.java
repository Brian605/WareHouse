package com.icons.warehouse.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.icons.warehouse.InventoryUtils;
import com.icons.warehouse.R;
import com.icons.warehouse.WareHouse;
import com.icons.warehouse.adapters.InventoryAdapter;
import com.icons.warehouse.databinding.FragmentInventoryBinding;
import com.icons.warehouse.models.Inventory;

import java.util.List;


public class InventoryFragment extends Fragment implements LifecycleOwner {
    private FragmentInventoryBinding binding;
    private RecyclerView recyclerView;
    private ExtendedFloatingActionButton exFab;
    private ImageView emptyView;
    private MutableLiveData<List<Inventory>> listMutableLiveData;

    public InventoryFragment() {
        // Required empty public constructor
    }
     public static InventoryFragment newInstance() {
         return new InventoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentInventoryBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        recyclerView=binding.recycler;
        exFab=binding.neItem;
        emptyView=binding.empty;

        exFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container,new NewInventoryFragment());
                transaction.commit();
                transaction.addToBackStack(null);
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                listMutableLiveData=new MutableLiveData<>();
        listMutableLiveData.observe(getViewLifecycleOwner(),inventoryListObserver);
        getInventories();
        return binding.getRoot();
    }

    private void getInventories() {
       new InventoryUtils().fetchInventoryList(getActivity(), new WareHouse.InventoryListener() {
           @Override
           public void onInventories(List<Inventory> inventoryList) {
listMutableLiveData.postValue(inventoryList);
           }
       });
    }

    private Observer<List<Inventory>> inventoryListObserver=new Observer<List<Inventory>>() {
        @Override
        public void onChanged(List<Inventory> inventoryList) {
            if (!inventoryList.isEmpty()){
                emptyView.setVisibility(View.GONE);
            }
            InventoryAdapter adapter=new InventoryAdapter(getActivity(), inventoryList, new InventoryAdapter.ClickListener() {
                @Override
                public void onClick(View view, Inventory inventory) {

                }
            });
            recyclerView.setAdapter(adapter);
        }
    };


}