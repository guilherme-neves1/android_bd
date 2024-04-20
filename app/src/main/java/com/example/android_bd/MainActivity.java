package com.example.android_bd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button btnSave, btnConsult, btnAlter, btnExclude;

    EditText code, name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        code = (EditText) findViewById(R.id.txtCode);
        name = (EditText) findViewById(R.id.txtName);
        email = (EditText) findViewById(R.id.txtEmail);

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

    private void consult() {
        int txtCode = Integer.parseInt(code.getText().toString());

        controllerDataBase db = new controllerDataBase(getBaseContext());

        Cursor data = db.loadDataByCode(txtCode);

        if (data.moveToFirst()) {
            name.setText(data.getString(1));
            email.setText(data.getString(2));
        } else {
            String msg= "Código não cadastrado.";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            clear();
        }
    }

    public void alter() {
        int id = Integer.parseInt(code.getText().toString());
        String txtName  = name.getText().toString();
        String txtEmail = email.getText().toString();
        String msg = "";
        boolean error = false;

        if (code.getText().toString().length() == 0) {
            msg = "Preencha o campo ID para alterar os dados";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            error = true;
        }

        if (txtName.length() == 0) {
            msg="Preencha o campo Nome";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            error = true;
        }

        if (txtEmail.length() < 10) {
            msg="Preencha corretamente o campo Email";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            error = true;
        }

        if (error == false) {
            controllerDataBase db = new controllerDataBase(getBaseContext());
            msg = db.alterData(id, txtName, txtEmail);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            clear();
        }
    }

    public void exclude() {
        int id = Integer.parseInt(code.getText().toString());

        controllerDataBase db = new controllerDataBase(getBaseContext());

        String res ;
        res = db.excludeData(id) ;

        Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show() ;
        clear() ;
    }

    public void clear() {
        code.setText("");
        name.setText("");
        email.setText("");
    }
}