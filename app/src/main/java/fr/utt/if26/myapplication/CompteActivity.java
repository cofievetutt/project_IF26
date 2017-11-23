package fr.utt.if26.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fr.utt.if26.myapplication.Class.ClassDb.Compte;
import fr.utt.if26.myapplication.Class.DataBaseHelper;
import fr.utt.if26.myapplication.Class.UtilMenu;

public class CompteActivity extends AppCompatActivity implements OnClickListener{

    Button buttonAjouterCompte;
    ListView listViewCompte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compte);

        this.buttonAjouterCompte = (Button) findViewById(R.id.buttonAjouterCompte);
        this.buttonAjouterCompte.setOnClickListener(this);

        this.listViewCompte = (ListView) findViewById(R.id.listViewCompte);

        ArrayList<Compte> listeCompte = new ArrayList<Compte>();

        try {
            listeCompte = Compte.getAllComptesByIdUtilisateur(ConnexionActivity.utilisateurConnecte.getIdUtilisateur(), DataBaseHelper.db);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayAdapter<Compte> adapter = new ArrayAdapter<Compte>(this, android.R.layout.simple_list_item_1 , listeCompte);
        this.listViewCompte.setAdapter(adapter);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        UtilMenu.createMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case UtilMenu.MENU_ACCEUIL:
                Toast.makeText(getApplicationContext(), "Accueil", Toast.LENGTH_LONG).show();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                break;
            case UtilMenu.MENU_COMPTE:
                Toast.makeText(getApplicationContext(), "Liste des comptes", Toast.LENGTH_LONG).show();
                intent.setClass(this, CompteActivity.class);
                startActivity(intent);
                break;
            case UtilMenu.MENU_DECONNEXION:
                Toast.makeText(getApplicationContext(), "Au revoir " + ConnexionActivity.utilisateurConnecte.getPrenom() + " " + ConnexionActivity.utilisateurConnecte.getNom(), Toast.LENGTH_LONG).show();
                ConnexionActivity.utilisateurConnecte = null;

                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        if (v.equals(this.buttonAjouterCompte))
        {
            Intent intent = new Intent();
            intent.setClass(this, AjouterCompteActivity.class);
            startActivity(intent);
        }
    }
}
