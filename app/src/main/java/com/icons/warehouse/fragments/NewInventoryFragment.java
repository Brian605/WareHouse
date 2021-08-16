package com.icons.warehouse.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.icons.warehouse.InventoryUtils;
import com.icons.warehouse.MainActivity;
import com.icons.warehouse.R;
import com.icons.warehouse.WareHouse;
import com.icons.warehouse.adapters.InventoryAdapter;
import com.icons.warehouse.databinding.FragmentInventoryBinding;
import com.icons.warehouse.databinding.FragmentNewInventoryBinding;
import com.icons.warehouse.db.DBManager;
import com.icons.warehouse.models.Inventory;

import java.util.List;


public class NewInventoryFragment extends Fragment implements LifecycleOwner {
    private FragmentNewInventoryBinding binding;
    private MaterialButton saveButton;
    private MaterialToolbar toolbar;
    private EditText nameEdit,qttyEdit,unitEdit;
    public NewInventoryFragment() {
        // Required empty public constructor
    }
     public static NewInventoryFragment newInstance() {
         return new NewInventoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.removeAllViews();
        binding=FragmentNewInventoryBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        nameEdit=binding.nameInput;
        qttyEdit=binding.qttyInput;
        unitEdit=binding.unitsInput;
        saveButton=binding.save;
        toolbar=binding.toolbar;

        unitEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                String[] units=getResources().getStringArray(R.array.units);
                builder.setItems(units, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      unitEdit.setText(units[which]);
                      dialog.dismiss();
                    }
                });
                Dialog dialog=builder.create();
                dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.bg_white_rounded));
                dialog.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameEdit.getText().toString();
                String quantity=qttyEdit.getText().toString();
                String units=unitEdit.getText().toString();

                if (TextUtils.isEmpty(name)){
                    nameEdit.setError("You must provide the product name");
                    nameEdit.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(quantity)){
                    nameEdit.setError("You must provide the product Quantity");
                    qttyEdit.requestFocus();
                    return;
                }

                if (units.equalsIgnoreCase("Select Unit")){
                    unitEdit.setError("You must provide the measurement unit");
                    unitEdit.requestFocus();
                    return;
                }
                DBManager manager=new DBManager(getActivity()).open();
                manager.addInventory(new Inventory(0,
                        Integer.parseInt(quantity),
                        name,units));
                Toast.makeText(getActivity(), "Saved!", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });
        return binding.getRoot();
    }


}