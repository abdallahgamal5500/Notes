package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
private Button btn_add,dialog_btn_cancel,dialog_btn_add;
private EditText dialog_title,dialog_text;
private String dialog_title_value,dialog_text_value,id,date;
private ListView listView;
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
        final ArrayList<Note_data> arrayList=new ArrayList<>();
        listView=findViewById(R.id.list_view);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Notes");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String title =snapshot.child("title").getValue().toString();
                    String date = snapshot.child("date").getValue().toString();
                    arrayList.add(0,new Note_data(title,"b","c",date));
                }
                Listview_Adapter listview_adapter=new Listview_Adapter(getApplicationContext(),R.layout.item,arrayList);
                listView.setAdapter(listview_adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this,Content.class);
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
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Notes");
                dialog_title_value=dialog_title.getText().toString();
                dialog_text_value=dialog_text.getText().toString();
                if (dialog_title_value.isEmpty() || dialog_text_value.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Empty field", Toast.LENGTH_SHORT).show();
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
}
