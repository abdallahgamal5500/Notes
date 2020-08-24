package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import static androidx.core.content.ContextCompat.startActivity;

public class Listview extends AppCompatActivity {
    private Button dialog_btn_cancel, dialog_btn_add;
    private FloatingActionButton btn_add;
    private TextInputLayout dialog_layout_title, dialog_layout_text;
    private String dialog_title_value, dialog_text_value, id;
    private ListView listView;
    public static ArrayList<Note_data> arrayList;
    public static int public_position;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });
        arrayList = new ArrayList<>();
        listView = findViewById(R.id.list_view);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myRef = database.getReference("Users").child(mAuth.getUid()).child("Notes");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    /* we have 2 methods so U can use any method */
//                    String title =snapshot.child("title").getValue().toString();
//                    String date = snapshot.child("date").getValue().toString();
                    Note_data note_data = snapshot.getValue(Note_data.class);
                    arrayList.add(0, note_data);
                }
                Listview_Adapter listview_adapter = new Listview_Adapter(getApplicationContext(), R.layout.item, arrayList);
                listView.setAdapter(listview_adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        public_position = position;
                        Intent intent = new Intent(Listview.this, Content.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void Dialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        dialog_layout_title = view.findViewById(R.id.dialog_layout_title);
        dialog_layout_text = view.findViewById(R.id.dialog_layout_text);

        dialog_btn_add = view.findViewById(R.id.dialog_btn_add);
        dialog_btn_cancel = view.findViewById(R.id.dialog_btn_cancel);
        builder.setView(view);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();

        /* this line to make the border transparent */
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
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
                if (!validationTitle()) {
                } else if (!validationText()) {
                } else {
                    id = myRef.push().getKey();
                    /* THIS LINE TO GET THE DATE DATE */
                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    Note_data note_data = new Note_data(dialog_title_value, dialog_text_value, id, currentDate);
                    myRef.child(id).setValue(note_data);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public boolean validationTitle() {

        dialog_title_value = dialog_layout_title.getEditText().getText().toString();

        if (dialog_title_value.isEmpty()) {
            dialog_layout_title.setError("Enter title note");
            dialog_layout_title.requestFocus();
            return false;
        } else {
            dialog_layout_title.setError(null);
            return true;
        }
    }

    public boolean validationText() {
        dialog_text_value = dialog_layout_text.getEditText().getText().toString();

        if (dialog_text_value.isEmpty()) {
            dialog_layout_text.setError("Enter your note");
            dialog_layout_text.requestFocus();
            return false;
        } else {
            dialog_layout_text.setError(null);
            return true;
        }
    }
}