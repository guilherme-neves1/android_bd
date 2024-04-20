package com.example.android_bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class controllerDataBase {
    private SQLiteDatabase db;
    private createDataBase database;

    public controllerDataBase(Context context) {
        database = new createDataBase(context);
    }

    public String insertData(String txtName, String txtEmail) {
        ContentValues values;
        long result;
        db = database.getWritableDatabase();

        values = new ContentValues();
        values.put("name", txtName);
        values.put("email", txtEmail);

        result = db.insert("contacts", null, values);
        db.close();

        if (result == -1)
            return "Erro ao inserir registro! Tente novamente.";
        else
            return "Registro inserido com sucesso!";
    }

    public Cursor loadDataByCode(int id) {
        Cursor cursor;
        String[] fields = {"code", "name", "email"};
        String where = "code=" + id;
        db = database.getReadableDatabase();
        cursor = db.query("contacts", fields, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public String alterData(int id, String name, String email){

        String msg = "Dados alterados com sucesso!" ;

        db = database.getReadableDatabase();

        ContentValues values = new ContentValues() ;
        values.put("name", name) ;
        values.put("email", email) ;

        String condition = "code = " + id;

        int line ;
        line = db.update("contacts", values, condition, null) ;

        if (line < 1){
            msg = "Erro ao alterar os dados! Tente novamente." ;
        }

        db.close();
        return msg;
    }

    public String excludeData(int id){
        String msg = "Registro excluído com sucesso!" ;

        db = database.getReadableDatabase();

        String condition = "code = " + id ;

        int lines;
        lines = db.delete("contacts", condition, null) ;

        if (lines < 1) {
            msg = "Erro ao excluir! Tente novamente." ;
        }

        db.close();
        return msg;
    }
}
