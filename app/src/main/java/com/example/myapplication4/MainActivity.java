package com.example.myapplication4;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication4.dataaccess.PLDataAccess;

public class MainActivity extends AppCompatActivity {
    private EditText edtName;
    private Button show;
    private Spinner spnPl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setupViews();
        //thisis a new code

        bindSpinnerPL();

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg ="";
                String name = edtName.getText().toString();
                if(!name.isEmpty()){
                    msg ="welcome " + name;
                    String pl = spnPl.getSelectedItem().toString();
                    msg =msg +", " + pl;
                }
                else{
                    msg = "enter your name";
                }
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void bindSpinnerPL() {
        String [] arr = PLDataAccess.getPls();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arr);
        spnPl.setAdapter(adapter);
    }

    private void setupViews() {
        edtName = findViewById(R.id.edtName);
        show = findViewById(R.id.show);
        spnPl = findViewById(R.id.SPNpl);
    }

}