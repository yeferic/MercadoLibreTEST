package com.yeferic.mercadolibreapp.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeferic.mercadolibreapp.R;

/**
 * A simple {@link Fragment} subclass..
 */
public class DetailsFragment extends Fragment {

    private MainViewModel mViewModel;


    public DetailsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.details_fragment, container, false);
    }
}