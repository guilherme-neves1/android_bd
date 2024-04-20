package com.example.android_bd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button btnSave, btnConsult, btnAlter, btnExclude;

    EditText txtCode, txtName, txtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCode = (EditText) findViewById(R.id.txtCode);
        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnConsult = (Button) findViewById(R.id.btnConsult);
        btnAlter = (Button) findViewById(R.id.btnAlter);
        btnExclude = (Button) findViewById(R.id.btnExclude);

        btnSave.setOnClickListener(this);
        btnConsult.setOnClickListener(this);
        btnAlter.setOnClickListener(this);
        btnExclude.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            save();
        }

        if (v.getId() == R.id.btnConsult) {
            consult();
        }

        if (v.getId() == R.id.btnAlter) {
            alter();
        }

        if (v.getId() == R.id.btnExclude) {
            exclude();
        }
    }

    public void save() {
        String msg = "";
        String txtName = name.getText().toString();
        String txtEmail = email.getText().toString();

        if (txtName.length() == 0 || txtEmail.length() < 10 ) {
            msg = "ATENÇÃO! Os campos Nome e Email devem ser preenchidos.";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        } else {
            controllerDataBase db = new controllerDataBase(getBaseContext());
            String result;

            result = db.insertData(txtName, txtEmail);

            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            clear();
        }
    }
}