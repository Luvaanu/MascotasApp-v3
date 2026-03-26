package com.vani.puppy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ConstructorMascotas {

    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public void guardarMascotaConRating(Mascota mascota) {
        BaseDatos dbHelper = new BaseDatos(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ConstantesBaseDatos.KEY_FOTO, mascota.getFoto());
        values.put(ConstantesBaseDatos.KEY_NOMBRE, mascota.getNombre());
        values.put(ConstantesBaseDatos.KEY_RATING, mascota.getRating());

        int filasActualizadas = db.update(
                ConstantesBaseDatos.TABLE_MASCOTA,
                values,
                ConstantesBaseDatos.KEY_ID + " = ?",
                new String[]{String.valueOf(mascota.getId())}
        );

        if (filasActualizadas == 0) {
            values.put(ConstantesBaseDatos.KEY_ID, mascota.getId());
            db.insert(ConstantesBaseDatos.TABLE_MASCOTA, null, values);
        }

        db.close();
    }

    public void insertarMascotasIniciales() {
        BaseDatos dbHelper = new BaseDatos(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + ConstantesBaseDatos.TABLE_MASCOTA,
                null
        );

        int cantidad = 0;
        if (cursor.moveToFirst()) {
            cantidad = cursor.getInt(0);
        }
        cursor.close();

        if (cantidad == 0) {
            insertarMascota(db, new Mascota(1, R.drawable.mascota1, "Koda", 0));
            insertarMascota(db, new Mascota(2, R.drawable.mascota2, "Milo", 0));
            insertarMascota(db, new Mascota(3, R.drawable.mascota3, "Luna", 0));
            insertarMascota(db, new Mascota(4, R.drawable.mascota4, "Simba", 0));
            insertarMascota(db, new Mascota(5, R.drawable.mascota5, "Nala", 0));
            insertarMascota(db, new Mascota(6, R.drawable.mascota6, "Ronny", 0));
        }

        db.close();
    }

    private void insertarMascota(SQLiteDatabase db, Mascota mascota) {
        ContentValues values = new ContentValues();
        values.put(ConstantesBaseDatos.KEY_ID, mascota.getId());
        values.put(ConstantesBaseDatos.KEY_FOTO, mascota.getFoto());
        values.put(ConstantesBaseDatos.KEY_NOMBRE, mascota.getNombre());
        values.put(ConstantesBaseDatos.KEY_RATING, mascota.getRating());

        db.insert(ConstantesBaseDatos.TABLE_MASCOTA, null, values);
    }

    public ArrayList<Mascota> obtenerMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        BaseDatos dbHelper = new BaseDatos(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA +
                " ORDER BY " + ConstantesBaseDatos.KEY_RATING + " DESC";

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Mascota mascota = new Mascota(
                    cursor.getInt(cursor.getColumnIndexOrThrow(ConstantesBaseDatos.KEY_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(ConstantesBaseDatos.KEY_FOTO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(ConstantesBaseDatos.KEY_NOMBRE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(ConstantesBaseDatos.KEY_RATING))
            );
            mascotas.add(mascota);
        }

        cursor.close();
        db.close();

        return mascotas;
    }

    public ArrayList<Mascota> obtenerTopMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        BaseDatos dbHelper = new BaseDatos(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA +
                " ORDER BY " + ConstantesBaseDatos.KEY_RATING + " DESC LIMIT 5";

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Mascota mascota = new Mascota(
                    cursor.getInt(cursor.getColumnIndexOrThrow(ConstantesBaseDatos.KEY_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(ConstantesBaseDatos.KEY_FOTO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(ConstantesBaseDatos.KEY_NOMBRE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(ConstantesBaseDatos.KEY_RATING))
            );
            mascotas.add(mascota);
        }

        cursor.close();
        db.close();

        return mascotas;
    }
}