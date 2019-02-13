package com.example.abcd.bookapplication.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abcd.bookapplication.R;
import com.example.abcd.bookapplication.database.DatabaseHelper;

public class CreateNoteActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtNotes,edtTitle;
    Button btnSave,btnCancel;
    int updateID;

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        edtTitle = findViewById(R.id.edt_title);
        edtNotes = findViewById(R.id.edt_notes);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);

        db = new DatabaseHelper(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    protected void onResume() {

        super.onResume();
        String TAG = getIntent().getStringExtra("TAG");

        updateID = getIntent().getIntExtra("id",0);
        if(TAG.equals("editNote"))
        {
            edtTitle.setText(getIntent().getStringExtra("title"));
            edtNotes.setText(getIntent().getStringExtra("contents"));
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_cancel:
                cancelNote();
                break;
            case R.id.btn_save:
                if(edtNotes.getText() != null && edtTitle.getText() != null)
                    saveNote(edtNotes.getText().toString(),edtTitle.getText().toString());
                else
                    Toast.makeText(this,"empty notes",Toast.LENGTH_SHORT).show();

                break;

        }

    }

    private void cancelNote() {

        Intent intent = new Intent();
        intent.putExtra("TAG","cancel");
        setResult(1,intent);
        finish();
    }

    private void saveNote(String notes, String title) {

        Intent intent = new Intent();
        intent.putExtra("title",title);
        intent.putExtra("notes",notes);
        intent.putExtra("id",updateID);
        intent.putExtra("TAG","save");
        setResult(1,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        cancelNote();
        super.onBackPressed();


    }
}
