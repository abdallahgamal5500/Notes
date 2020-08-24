package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Content extends AppCompatActivity {

    private Button content_update, content_delete;
    private TextInputEditText content_text;
    private String content_value;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        content_update = findViewById(R.id.content_update);
        content_delete = findViewById(R.id.content_delete);
        content_text = findViewById(R.id.content_text);

        Note_data note_data = new Note_data();
        note_data = Listview.arrayList.get(Listview.public_position);

        content_text.setText(note_data.getText());
        /* Use this line to move the cursor to the end of the text */
        content_text.setSelection(content_text.getText().length());

        /* UPDATE THE NOTE */
        mAuth =FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child(mAuth.getUid()).child("Notes").child(note_data.getId());

        final Note_data finalNote_data = note_data;
        content_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content_value = content_text.getText().toString();
                myRef.child("text").setValue(content_value);
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                myRef.child("date").setValue(currentDate);
                Toast.makeText(Content.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });
        content_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.removeValue();
                Toast.makeText(Content.this, "Removed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Listview.class);
                startActivity(intent);
            }
        });
    }
}
