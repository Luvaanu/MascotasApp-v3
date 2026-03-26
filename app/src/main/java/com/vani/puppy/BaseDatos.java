package com.vani.puppy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota =
                "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA + " (" +
                        ConstantesBaseDatos.KEY_ID + " INTEGER PRIMARY KEY, " +
                        ConstantesBaseDatos.KEY_FOTO + " INTEGER, " +
                        ConstantesBaseDatos.KEY_NOMBRE + " TEXT, " +
                        ConstantesBaseDatos.KEY_RATING + " INTEGER" +
                        ")";

        db.execSQL(queryCrearTablaMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTA);
        onCreate(db);
    }
}