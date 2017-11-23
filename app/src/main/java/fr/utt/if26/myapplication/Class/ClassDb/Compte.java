package fr.utt.if26.myapplication.Class.ClassDb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fr.utt.if26.myapplication.Class.DataBaseHelper;

/**
 * Created by corentinfievet on 23/11/2017.
 */

public class Compte {

    private int idCompte;
    private String numero;
    private Date dateAjout;
    private double capital;
    private boolean cloture;
    private int idBanque;
    private int idUtilisateur;

    public Compte(String numero, Date dateAjout, double capital, boolean cloture, int idBanque, int idUtilisateur) {
        this.numero = numero;
        this.dateAjout = dateAjout;
        this.capital = capital;
        this.cloture = cloture;
        this.idBanque = idBanque;
        this.idUtilisateur = idUtilisateur;
    }

    public Compte(int idCompte, String numero, Date dateAjout, double capital, boolean cloture, int idBanque, int idUtilisateur) {
        this.idCompte = idCompte;
        this.numero = numero;
        this.dateAjout = dateAjout;
        this.capital = capital;
        this.cloture = cloture;
        this.idBanque = idBanque;
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public boolean isCloture() {
        return cloture;
    }

    public void setCloture(boolean cloture) {
        this.cloture = cloture;
    }

    public int getIdBanque() {
        return idBanque;
    }

    public void setIdBanque(int idBanque) {
        this.idBanque = idBanque;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "numero='" + numero + '\'' +
                ", capital=" + capital +
                ", idBanque=" + idBanque +
                '}';
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void insertNouveauCompte(DataBaseHelper db)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBaseHelper.COL_2_COMPTE , this.numero.trim());
        contentValues.put(DataBaseHelper.COL_3_COMPTE , getDateTime());
        contentValues.put(DataBaseHelper.COL_4_COMPTE , this.capital);
        contentValues.put(DataBaseHelper.COL_5_COMPTE , false);
        contentValues.put(DataBaseHelper.COL_6_COMPTE , 1);
        contentValues.put(DataBaseHelper.COL_7_COMPTE , this.idUtilisateur);

        db.getWritableDatabase().insert(DataBaseHelper.TABLE_COMPTE_NAME, null, contentValues);

        //contentValues.put(COL_2, name);
        //db.insert(TABLE_GENRE_NAME, null, contentValues);
    }

    public static ArrayList<Compte> getAllComptesByIdUtilisateur(int idUtilisateur, DataBaseHelper db) throws ParseException {
        SQLiteDatabase stmt = db.getReadableDatabase();
        Cursor cursor = stmt.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_COMPTE_NAME + " WHERE " + DataBaseHelper.COL_7_COMPTE + "='" + idUtilisateur + "';", null);

        ArrayList<Compte> listeCompteUtilisateur = new ArrayList<Compte>();
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getCount();
            for(int i = 0; i < count; i++)
            {
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_1_COMPTE));
                String numero = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_2_COMPTE));
                String date = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_3_COMPTE));
                double capital = cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.COL_4_COMPTE));
                boolean cloture = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_5_COMPTE)) > 0;
                int idBanque = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_6_COMPTE));
                int idUtilisateurDb = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_7_COMPTE));

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date convertedDate = new Date();
                convertedDate = dateFormat.parse(date);

                System.out.println(convertedDate);

                Compte compte = new Compte(id, numero, convertedDate, capital, cloture, idBanque, idUtilisateurDb);
                listeCompteUtilisateur.add(compte);

                cursor.moveToNext();
            }
        }

        return listeCompteUtilisateur;
    }
}
