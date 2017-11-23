package fr.utt.if26.myapplication.Class;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import fr.utt.if26.myapplication.ConnexionActivity;

/**
 * Created by corentinfievet on 23/11/2017.
 */

public class UtilMenu {


    public static final int MENU_ACCEUIL = Menu.FIRST;
    public static final int MENU_INSCRIPTION = Menu.FIRST + 3;
    public static final int MENU_COMPTE = Menu.FIRST + 1;
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
            menu.add(0, MENU_DECONNEXION, Menu.NONE, "Déconnexion");
    }
}
