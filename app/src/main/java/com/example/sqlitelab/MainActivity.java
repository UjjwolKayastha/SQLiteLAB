 package com.example.sqlitelab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

 public class MainActivity extends AppCompatActivity {

     EditText etName, etDesc;
     Button btnAdd;
     TextView textView;
     DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.name);
        etDesc = findViewById(R.id.description);
        btnAdd = findViewById(R.id.btnAdd);

        textView = findViewById(R.id.tv);

        dbHelper = new DBHelper(this);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note notes = new Note(etName.getText().toString(), etDesc.getText().toString());


                dbHelper.addNote(notes);
                printDB();
            }
        });







    }
     public void printDB(){
        List<Note> notes = dbHelper.databaseToString();
        textView.setText("");
         for (int i=0; i<notes.size();i++){
             textView.append(notes.get(i).getTitle());
             textView.append("\n");
             textView.append(notes.get(i).getDescription());
             textView.append("\n");
             textView.append("\n");
         }
     }
}
