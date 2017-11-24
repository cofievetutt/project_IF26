package fr.utt.if26.myapplication.Class.ClassDb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import fr.utt.if26.myapplication.Class.DataBaseHelper;

/**
 * Created by corentinfievet on 24/11/2017.
 */

public class Recette {
    private int idRecette;
    private Date date;
    private double montant;
    private int idCompte;
    private int idCategorie;
    private String commentaire;

    public Recette(int idRecette, Date date, double montant, int idCompte, int idCategorie, String commentaire) {
        this.idRecette = idRecette;
        this.date = date;
        this.montant = montant;
        this.idCompte = idCompte;
        this.idCategorie = idCategorie;
        this.commentaire = commentaire;
    }

    public Recette(Date date, double montant, int idCompte, int idCategorie, String commentaire) {
        this.date = date;
        this.montant = montant;
        this.idCompte = idCompte;
        this.idCategorie = idCategorie;
        this.commentaire = commentaire;
    }

    public int getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(int idRecette) {
        this.idRecette = idRecette;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void insertNouvelRecette(DataBaseHelper db)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBaseHelper.COL_2_RECETTE , getDateTime());
        contentValues.put(DataBaseHelper.COL_3_RECETTE , this.montant);
        contentValues.put(DataBaseHelper.COL_4_RECETTE , this.idCompte);
        contentValues.put(DataBaseHelper.COL_5_RECETTE , this.idCategorie);
        contentValues.put(DataBaseHelper.COL_6_RECETTE , this.commentaire);

        db.getWritableDatabase().insert(DataBaseHelper.TABLE_RECETTE_NAME, null, contentValues);

        //contentValues.put(COL_2, name);
        //db.insert(TABLE_GENRE_NAME, null, contentValues);
    }

    public static ArrayList<Recette> getAllRecetteByIdCompte(int idCompte, DataBaseHelper db) throws ParseException {
        SQLiteDatabase stmt = db.getReadableDatabase();
        Cursor cursor = stmt.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_RECETTE_NAME + " WHERE " + DataBaseHelper.COL_4_RECETTE + "='" + idCompte + "';", null);

        ArrayList<Recette> listeRecettes = new ArrayList<Recette>();
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            for(int i = 0; i < count; i++)
            {
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_1_RECETTE));
                String date = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_2_RECETTE));
                double montant = cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.COL_3_RECETTE));
                int idCompteDb = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_4_RECETTE));
                int idCategorie = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_5_RECETTE));
                String commentaire = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_6_RECETTE));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date convertedDate = new Date();
                convertedDate = dateFormat.parse(date);

                System.out.println(convertedDate);

                Recette recette = new Recette(id, convertedDate, montant, idCompteDb, idCategorie, commentaire);
                listeRecettes.add(recette);

                cursor.moveToNext();
            }
        }

        return listeRecettes;
    }
}
