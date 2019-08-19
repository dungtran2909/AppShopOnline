package com.example.uimihnathome.Dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uimihnathome.R;

public class DialogBottomSheetBrand extends BottomSheetDialogFragment {
    View view;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_bottomsheet_brand,container,false);
        
        addControls();
        addEvents();
        
        return view;
    }

    private void addEvents() {
    }

    private void addControls() {
    }
}
