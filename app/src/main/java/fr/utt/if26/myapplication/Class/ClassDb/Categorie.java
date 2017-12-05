package fr.utt.if26.myapplication.Class.ClassDb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fr.utt.if26.myapplication.Class.DataBaseHelper;

/**
 * Created by corentinfievet on 26/11/2017.
 */

public class Categorie {

    private int idCategorie;
    private String libelle;

    public Categorie(int idCategorie, String libelle) {
        this.idCategorie = idCategorie;
        this.libelle = libelle;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "idCategorie=" + idCategorie +
                ", libelle='" + libelle + '\'' +
                '}';
    }

    /**
     * Fonction retournant l'ensemble des categories existantes en BDD
     * @return
     * @throws ParseException
     */
    public static ArrayList<Categorie> getAllCategorie() throws ParseException {
        SQLiteDatabase stmt = DataBaseHelper.db.getReadableDatabase();
        Cursor cursor = stmt.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_CATEGORIE_NAME + " ORDER BY libelle;", null);

        ArrayList<Categorie> listCategorie = new ArrayList<Categorie>();
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            for(int i = 0; i < count; i++)
            {
                int idCategorie = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_1_CATEGORIE));
                String libelle = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_2_CATEGORIE));

                Categorie categorie = new Categorie(idCategorie, libelle);
                listCategorie.add(categorie);

                cursor.moveToNext();
            }
        }

        return listCategorie;
    }

    public static Categorie getCategorieById(int idCategorie) throws ParseException {
        SQLiteDatabase stmt = DataBaseHelper.db.getReadableDatabase();
        Cursor cursor = stmt.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_CATEGORIE_NAME + " WHERE " + DataBaseHelper.COL_1_CATEGORIE + " = " + idCategorie + ";" , null);

        Categorie categorie = null;
        if (cursor != null) {
            cursor.moveToFirst();
            int idCategorieDb = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_1_CATEGORIE));
            String libelle = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_2_CATEGORIE));

            categorie = new Categorie(idCategorieDb, libelle);
        }

        return categorie;
    }
}
