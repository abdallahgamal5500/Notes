package com.example.notes;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

public class Dialog extends DialogFragment implements View.OnClickListener {
    private EditText dialog_title, dialog_text;
    private Button dialog_btn_add, dialog_btn_cancel;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialog,container,false);
        dialog_title=view.findViewById(R.id.dialog_title);
        dialog_text=view.findViewById(R.id.dialog_text);
        dialog_btn_add=view.findViewById(R.id.dialog_btn_add);
        dialog_btn_cancel=view.findViewById(R.id.dialog_btn_cancel);
        dialog_btn_add.setOnClickListener(this);
        dialog_btn_cancel.setOnClickListener(this);
        getDialog();
        return view;
    }
    @Override
    public void onClick(View v) {
        if(dialog_btn_add.getText().toString().equals("Add")) {
            Toast.makeText(getContext(), "Add", Toast.LENGTH_SHORT).show();
        } else {
            this.dismiss();
        }
    }
}