package fr.utt.if26.myapplication.Class;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import fr.utt.if26.myapplication.AccueilActivity;
import fr.utt.if26.myapplication.CompteActivity;
import fr.utt.if26.myapplication.ConnexionActivity;
import fr.utt.if26.myapplication.InscriptionActivity;
import fr.utt.if26.myapplication.ListeCategoriesActivity;
import fr.utt.if26.myapplication.MainActivity;

/**
 * Created by corentinfievet on 23/11/2017.
 */

public class UtilMenu {


    public static final int MENU_ACCEUIL = Menu.FIRST;
    public static final int MENU_INSCRIPTION = Menu.FIRST + 3;
    public static final int MENU_COMPTE = Menu.FIRST + 1;
    public static final int MENU_CAT = Menu.FIRST + 4;
    public static final int MENU_DECONNEXION = Menu.FIRST + 2;

    public static void createMenu(Menu menu)
    {
        menu.clear();
        menu.add(0, MENU_ACCEUIL, Menu.NONE, "Accueil");
        if(ConnexionActivity.utilisateurConnecte == null)
            menu.add(0, MENU_INSCRIPTION, Menu.NONE, "Inscription");
        if(ConnexionActivity.utilisateurConnecte != null)
            menu.add(0, MENU_COMPTE, Menu.NONE, "Liste des comptes");
        if(ConnexionActivity.utilisateurConnecte != null)
            menu.add(0, MENU_CAT, Menu.NONE, "Liste des catégories");
        if(ConnexionActivity.utilisateurConnecte != null)
            menu.add(0, MENU_DECONNEXION, Menu.NONE, "Déconnexion");
    }

    public static void selectOptionMenu(MenuItem item, ContextWrapper context, Context classe)
    {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case UtilMenu.MENU_ACCEUIL:
                if(ConnexionActivity.utilisateurConnecte == null) {
                    Toast.makeText(context, "Accueil", Toast.LENGTH_LONG).show();
                    intent.setClass(classe, MainActivity.class);
                    classe.startActivity(intent);
                }
                else
                {
                    Toast.makeText(context, "Accueil", Toast.LENGTH_LONG).show();
                    intent.setClass(classe, AccueilActivity.class);
                    classe.startActivity(intent);
                }
                break;
            case UtilMenu.MENU_INSCRIPTION:
                Toast.makeText(context, "Inscription", Toast.LENGTH_LONG).show();
                intent.setClass(classe, InscriptionActivity.class);
                classe.startActivity(intent);
                break;
            case UtilMenu.MENU_COMPTE:
                Toast.makeText(context, "Liste des comptes", Toast.LENGTH_LONG).show();
                intent.setClass(classe, CompteActivity.class);
                classe.startActivity(intent);
                break;
            case UtilMenu.MENU_CAT:
                Toast.makeText(context, "Liste des catégories", Toast.LENGTH_LONG).show();
                intent.setClass(classe, ListeCategoriesActivity.class);
                classe.startActivity(intent);
                break;
            case UtilMenu.MENU_DECONNEXION:
                Toast.makeText(context, "Au revoir " + ConnexionActivity.utilisateurConnecte.getPrenom() + " " + ConnexionActivity.utilisateurConnecte.getNom(), Toast.LENGTH_LONG).show();
                ConnexionActivity.utilisateurConnecte = null;

                intent.setClass(classe, MainActivity.class);
                classe.startActivity(intent);
                break;
        }
    }
}
