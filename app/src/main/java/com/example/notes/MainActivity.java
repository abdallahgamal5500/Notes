package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private Button btn_add,dialog_btn_cancel,dialog_btn_add;
private EditText dialog_title,dialog_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add=findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });
    }
    private void Dialog() {
        final AlertDialog.Builder builder =new AlertDialog.Builder(this);
        View view =getLayoutInflater().inflate(R.layout.dialog,null);
        dialog_title=view.findViewById(R.id.dialog_title);
        dialog_text=view.findViewById(R.id.dialog_text);
        dialog_btn_add=view.findViewById(R.id.dialog_btn_add);
        dialog_btn_cancel=view.findViewById(R.id.dialog_btn_cancel);
        builder.setView(view);
        builder.setCancelable(false);

        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "ADD", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

}
