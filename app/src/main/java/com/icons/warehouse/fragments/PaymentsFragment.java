package com.icons.warehouse.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.icons.warehouse.R;


public class PaymentsFragment extends Fragment {

    public PaymentsFragment() {
        // Required empty public constructor
    }
     public static PaymentsFragment newInstance() {
         return new PaymentsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payments, container, false);
    }
}