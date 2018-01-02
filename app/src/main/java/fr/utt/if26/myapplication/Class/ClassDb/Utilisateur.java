package fr.utt.if26.myapplication.Class.ClassDb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.utt.if26.myapplication.Class.DataBaseHelper;

/**
 * Created by corentinfievet on 21/11/2017.
 */

public class Utilisateur {

    private int idUtilisateur;
    private String nom;
    private String nomMarital;
    private String prenom;
    private String motDePasse;
    private String identifiant;
    private int idGenre;
    private boolean admin;
    private String email;

    public Utilisateur(int idUtilisateur, String nom, String nomMarital, String prenom, String motDePasse, String identifiant, int idGenre, boolean admin, String email) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.nomMarital = nomMarital;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.identifiant = identifiant;
        this.idGenre = idGenre;
        this.admin = admin;
        this.email = email;
    }

    public Utilisateur(String nom, String nomMarital, String prenom, String motDePasse, String identifiant, int idGenre, boolean admin, String email) {
        this.nom = nom;
        this.nomMarital = nomMarital;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.identifiant = identifiant;
        this.idGenre = idGenre;
        this.admin = admin;
        this.email = email;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomMarital() {
        return nomMarital;
    }

    public void setNomMarital(String nomMarital) {
        this.nomMarital = nomMarital;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Fonction ajoutant un utilisateur
     * @param db
     */
    public void insertNouvelUtilisateur(DataBaseHelper db)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBaseHelper.COL_2_UTILISATEUR , this.nom.trim());
        if(this.nomMarital != null)
            contentValues.put(DataBaseHelper.COL_3_UTILISATEUR , this.nomMarital.trim());
        contentValues.put(DataBaseHelper.COL_4_UTILISATEUR , this.prenom.trim());
        contentValues.put(DataBaseHelper.COL_5_UTILISATEUR , this.motDePasse.trim());
        contentValues.put(DataBaseHelper.COL_6_UTILISATEUR , this.identifiant.trim());
        contentValues.put(DataBaseHelper.COL_7_UTILISATEUR , this.idGenre);
        contentValues.put(DataBaseHelper.COL_8_UTILISATEUR , this.admin);
        contentValues.put(DataBaseHelper.COL_9_UTILISATEUR , this.email.trim());

        db.getWritableDatabase().insert(DataBaseHelper.TABLE_UTILISATEUR_NAME, null, contentValues);

        //contentValues.put(COL_2, name);
        //db.insert(TABLE_GENRE_NAME, null, contentValues);
    }

    /**
     * Fonction retournant un utilisateur en fonction de l'email prit en paramètre
     * @param email
     * @param db
     * @return
     */
    public static Utilisateur getUtilisateurByEmail(String email, DataBaseHelper db)
    {
        SQLiteDatabase stmt = db.getReadableDatabase();
        Cursor cursor = stmt.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_UTILISATEUR_NAME + " WHERE " + DataBaseHelper.COL_9_UTILISATEUR + "='" + email.trim() + "';", null);

        Utilisateur utilisateur = null;
        if(cursor != null)
        {
            cursor.moveToFirst();
            int count = cursor.getCount();
            if(count > 0) {
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_1_UTILISATEUR));
                String nom = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_2_UTILISATEUR));
                String nomMarital = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_3_UTILISATEUR));
                String prenom = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_4_UTILISATEUR));
                String motDePasse = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_5_UTILISATEUR));
                String identifiant = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_6_UTILISATEUR));
                int idGenre = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_7_UTILISATEUR));
                boolean admin = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_8_UTILISATEUR)) > 0;
                String emailDb = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_9_UTILISATEUR));

                utilisateur = new Utilisateur(id, nom, nomMarital, prenom, motDePasse, identifiant, idGenre, admin, emailDb);
            }
        }

        return utilisateur;

    }

    /**
     * Fonction retournant un utilisateur en fonction de l'identifiant prit en paramètre
     * @param identifiant
     * @param db
     * @return
     */
    public static Utilisateur getUtilisateurByIdentifiant(String identifiant, DataBaseHelper db)
    {
        SQLiteDatabase stmt = db.getReadableDatabase();
        Cursor cursor = stmt.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_UTILISATEUR_NAME + " WHERE " + DataBaseHelper.COL_6_UTILISATEUR + "='" + identifiant.trim() + "';", null);

        Utilisateur utilisateur = null;
        if(cursor != null)
        {
            cursor.moveToFirst();
            int count = cursor.getCount();
            if(count > 0) {
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_1_UTILISATEUR));
                String nom = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_2_UTILISATEUR));
                String nomMarital = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_3_UTILISATEUR));
                String prenom = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_4_UTILISATEUR));
                String motDePasse = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_5_UTILISATEUR));
                String identifiantDb = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_6_UTILISATEUR));
                int idGenre = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_7_UTILISATEUR));
                boolean admin = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_8_UTILISATEUR)) > 0;
                String emailDb = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_9_UTILISATEUR));

                utilisateur = new Utilisateur(id, nom, nomMarital, prenom, motDePasse, identifiantDb, idGenre, admin, emailDb);
            }
        }

        return utilisateur;

    }

    public static Utilisateur getUtilisateurByEmailAndMotDePasee(String email, String pass, DataBaseHelper db)
    {
        SQLiteDatabase stmt = db.getReadableDatabase();
        Cursor cursor = stmt.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_UTILISATEUR_NAME + " WHERE " + DataBaseHelper.COL_9_UTILISATEUR + "='" + email.trim() + "' AND " + DataBaseHelper.COL_5_UTILISATEUR + "='" + pass.trim() + "';", null);

        Utilisateur utilisateur = null;
        if(cursor != null)
        {
            cursor.moveToFirst();
            int count = cursor.getCount();
            if(count > 0) {
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_1_UTILISATEUR));
                String nom = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_2_UTILISATEUR));
                String nomMarital = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_3_UTILISATEUR));
                String prenom = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_4_UTILISATEUR));
                String motDePasse = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_5_UTILISATEUR));
                String identifiantDb = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_6_UTILISATEUR));
                int idGenre = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_7_UTILISATEUR));
                boolean admin = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_8_UTILISATEUR)) > 0;
                String emailDb = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_9_UTILISATEUR));

                utilisateur = new Utilisateur(id, nom, nomMarital, prenom, motDePasse, identifiantDb, idGenre, admin, emailDb);
            }
        }

        return utilisateur;

    }

    public static Utilisateur getUtilisateurByIdentifiantAndMotDePasee(String identifiant, String pass, DataBaseHelper db)
    {
        SQLiteDatabase stmt = db.getReadableDatabase();
        Cursor cursor = stmt.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_UTILISATEUR_NAME + " WHERE " + DataBaseHelper.COL_6_UTILISATEUR + "='" + identifiant.trim() + "' AND " + DataBaseHelper.COL_5_UTILISATEUR + "='" + pass.trim() + "';", null);

        Utilisateur utilisateur = null;
        if(cursor != null)
        {
            cursor.moveToFirst();
            int count = cursor.getCount();
            if(count > 0) {
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_1_UTILISATEUR));
                String nom = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_2_UTILISATEUR));
                String nomMarital = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_3_UTILISATEUR));
                String prenom = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_4_UTILISATEUR));
                String motDePasse = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_5_UTILISATEUR));
                String identifiantDb = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_6_UTILISATEUR));
                int idGenre = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_7_UTILISATEUR));
                boolean admin = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_8_UTILISATEUR)) > 0;
                String emailDb = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_9_UTILISATEUR));

                utilisateur = new Utilisateur(id, nom, nomMarital, prenom, motDePasse, identifiantDb, idGenre, admin, emailDb);
            }
        }

        return utilisateur;

    }
}
