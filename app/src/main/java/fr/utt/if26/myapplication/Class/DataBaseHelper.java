package fr.utt.if26.myapplication.Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by corentinfievet on 21/11/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper{


    public static DataBaseHelper db;

    //Database informations;
    public static final String DATABASE_NAME = "Compte.db";
    public static final int DATABASE_VERSION = 1;

    //Table Genre
    public static final String TABLE_GENRE_NAME = "Genre";
    public static final String COL_1_GENRE = "idGenre";
    public static final String COL_2_GENRE = "libelle";
    public static final String CREATE_TABLE_GENRE = "CREATE TABLE IF NOT EXISTS " + TABLE_GENRE_NAME + " (" +
            COL_1_GENRE + " INTEGER PRIMARY KEY NOT NULL," +
            COL_2_GENRE + " TEXT NOT NULL)";

    public static final String INSERT_VALEUR_DEFAUT_GENRE =
            "INSERT OR REPLACE INTO " + TABLE_GENRE_NAME + " (" + COL_1_GENRE + "," + COL_2_GENRE + ")" +
            "VALUES " +
            "(1, 'Monsieur')," +
            "(2, 'Madame');";

    //Table Genre
    public static final String TABLE_ETAT_NAME = "Etat";
    public static final String COL_1_ETAT = "idEtat";
    public static final String COL_2_ETAT = "libelle";
    public static final String CREATE_TABLE_ETAT = "CREATE TABLE IF NOT EXISTS " + TABLE_ETAT_NAME + " (" +
            COL_1_ETAT + " INTEGER PRIMARY KEY NOT NULL," +
            COL_2_ETAT + " TEXT NOT NULL)";

    public static final String INSERT_VALEUR_DEFAUT_ETAT =
            "INSERT OR REPLACE INTO " + TABLE_ETAT_NAME + " (" + COL_1_ETAT + "," + COL_2_ETAT + ")" +
                    "VALUES " +
                    "(1, 'En Attente')," +
                    "(2, 'Validé');";

    //Table Banque
    public static final String TABLE_BANQUE_NAME = "Banque";
    public static final String COL_1_BANQUE = "idBanque";
    public static final String COL_2_BANQUE = "libelle";
    public static final String CREATE_TABLE_BANQUE = "CREATE TABLE IF NOT EXISTS " + TABLE_BANQUE_NAME + " (" +
            COL_1_BANQUE + " INTEGER PRIMARY KEY NOT NULL," +
            COL_2_BANQUE + " TEXT NOT NULL)";

    public static final String INSERT_VALEUR_DEFAUT_BANQUE =
            "INSERT OR REPLACE INTO " + TABLE_BANQUE_NAME + " (" + COL_1_BANQUE + "," + COL_2_BANQUE + ")" +
                    "VALUES " +
                    "(1, 'BNP Paribas')," +
                    "(2, 'Banque Populaire')," +
                    "(3, 'Caisse d''Epargne')," +
                    "(4, 'LCL')," +
                    "(5, 'Autre');";

    //Table catégorie
    public static final String TABLE_CATEGORIE_NAME = "Categorie";
    public static final String COL_1_CATEGORIE = "idCategorie";
    public static final String COL_2_CATEGORIE = "libelle";
    public static final String CREATE_TABLE_CATEGORIE = "CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORIE_NAME + " (" +
            COL_1_CATEGORIE + " INTEGER PRIMARY KEY NOT NULL," +
            COL_2_CATEGORIE + " TEXT NOT NULL)";

    public static final String INSERT_VALEUR_DEFAUT_CATEGORIE =
            "INSERT OR REPLACE INTO " + TABLE_CATEGORIE_NAME + " (" + COL_1_CATEGORIE + "," + COL_2_CATEGORIE + ")" +
                    " VALUES " +
                    " (1, 'Salaire')," +
                    " (2, 'Voiture')," +
                    " (3, 'Courses')," +
                    " (4, 'Autre');";

    //Table Utilisateur
    public static final String TABLE_UTILISATEUR_NAME = "Utilisateur";
    public static final String COL_1_UTILISATEUR = "idUtilisateur";
    public static final String COL_2_UTILISATEUR = "nom";
    public static final String COL_3_UTILISATEUR = "nomMarital";
    public static final String COL_4_UTILISATEUR = "prenom";
    public static final String COL_5_UTILISATEUR = "motDePasse";
    public static final String COL_6_UTILISATEUR = "identifiant";
    public static final String COL_7_UTILISATEUR = "idGenre";
    public static final String COL_8_UTILISATEUR = "admin";
    public static final String COL_9_UTILISATEUR = "email";
    public static final String CREATE_TABLE_UTILISATEUR =  "CREATE TABLE IF NOT EXISTS " + TABLE_UTILISATEUR_NAME + "(" +
            COL_1_UTILISATEUR + " INTEGER PRIMARY KEY NOT NULL," +
            COL_2_UTILISATEUR + " TEXT NOT NULL," +
            COL_3_UTILISATEUR + " TEXT NULL," +
            COL_4_UTILISATEUR + " TEXT NOT NULL," +
            COL_5_UTILISATEUR + " TEXT NOT NULL," +
            COL_6_UTILISATEUR + " TEXT NOT NULL," +
            COL_7_UTILISATEUR + " INTEGER NOT NULL," +
            COL_8_UTILISATEUR + " BIT NOT NULL," +
            COL_9_UTILISATEUR + " TEXT NULL," +
            "FOREIGN KEY(" + COL_7_UTILISATEUR + ") REFERENCES " + TABLE_GENRE_NAME + "(" + COL_1_GENRE + ")" +
            ");";

    //Table Compte
    public static final String TABLE_COMPTE_NAME = "Compte";
    public static final String COL_1_COMPTE = "idCompte";
    public static final String COL_2_COMPTE = "numero";
    public static final String COL_3_COMPTE = "dateAjout";
    public static final String COL_4_COMPTE = "capital";
    public static final String COL_5_COMPTE = "cloture";
    public static final String COL_6_COMPTE = "idBanque";
    public static final String COL_7_COMPTE = "idUtilisateur";
    public static final String CREATE_TABLE_COMPTE = "CREATE TABLE IF NOT EXISTS " + TABLE_COMPTE_NAME + "(" +
             COL_1_COMPTE + " INTEGER PRIMARY KEY NOT NULL," +
             COL_2_COMPTE + " TEXT NOT NULL," +
             COL_3_COMPTE + " DATE NOT NULL," +
             COL_4_COMPTE + " DECIMAL(18,2) NOT NULL," +
             COL_5_COMPTE + " BIT NOT NULL," +
             COL_6_COMPTE + " INTEGER NOT NULL," +
             COL_7_COMPTE + " INTEGER NOT NULL," +
            "FOREIGN KEY(" + COL_7_COMPTE + ") REFERENCES " + TABLE_UTILISATEUR_NAME + "(" + COL_1_UTILISATEUR + ")" +
            ");";

    //Table Recette
    public static final String TABLE_RECETTE_NAME = "Recette";
    public static final String COL_1_RECETTE = "idRecette";
    public static final String COL_2_RECETTE = "date";
    public static final String COL_3_RECETTE = "montant";
    public static final String COL_4_RECETTE = "idCompte";
    public static final String COL_5_RECETTE = "idCategorie";
    public static final String COL_6_RECETTE = "commentaire";
    public static final String COL_7_RECETTE = "idEtat";
    public static final String CREATE_TABLE_RECETTE = "CREATE TABLE IF NOT EXISTS " + TABLE_RECETTE_NAME + "(" +
            COL_1_RECETTE + " INTEGER PRIMARY KEY NOT NULL," +
            COL_2_RECETTE + " DATE NOT NULL," +
            COL_3_RECETTE + " DECIMAL(18,2) NOT NULL," +
            COL_4_RECETTE + " INTEGER NOT NULL," +
            COL_5_RECETTE + " INTEGER NOT NULL," +
            COL_6_RECETTE + " TEXT NOT NULL," +
            COL_7_RECETTE + " INTEGER NOT NULL," +
            "FOREIGN KEY(" + COL_4_RECETTE + ") REFERENCES " + TABLE_COMPTE_NAME + "(" + COL_1_COMPTE + ")" +
            "FOREIGN KEY(" + COL_5_RECETTE + ") REFERENCES " + TABLE_CATEGORIE_NAME + "(" + COL_1_CATEGORIE + ")" +
            "FOREIGN KEY(" + COL_7_RECETTE + ") REFERENCES " + TABLE_ETAT_NAME + "(" + COL_1_ETAT + ")" +
            ");";



    public DataBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
        //onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_GENRE);
        db.execSQL(INSERT_VALEUR_DEFAUT_GENRE);
        db.execSQL(CREATE_TABLE_ETAT);
        db.execSQL(INSERT_VALEUR_DEFAUT_ETAT);
        db.execSQL(CREATE_TABLE_CATEGORIE);
        db.execSQL(INSERT_VALEUR_DEFAUT_CATEGORIE);
        db.execSQL(CREATE_TABLE_BANQUE);
        db.execSQL(INSERT_VALEUR_DEFAUT_BANQUE);
        db.execSQL(CREATE_TABLE_UTILISATEUR);
        db.execSQL(CREATE_TABLE_COMPTE);
        db.execSQL(CREATE_TABLE_RECETTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_GENRE );
        //db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_UTILISATEUR );
    }

    public void insertGenreData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL_2_GENRE , "Monsieur");
        //contentValues.put(COL_2, name);
        //db.insert(TABLE_GENRE_NAME, null, contentValues);

    }
}
