package fr.utt.if26.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.utt.if26.myapplication.Class.ClassDb.Compte;
import fr.utt.if26.myapplication.Class.DataBaseHelper;
import fr.utt.if26.myapplication.Class.UtilMenu;

public class AjouterCompteActivity extends AppCompatActivity implements OnClickListener {

    EditText EditTextnumero;
    EditText EditTextcapital;
    Button buttonValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_compte);

        this.buttonValider = (Button) findViewById(R.id.buttonValider);
        this.buttonValider.setOnClickListener(this);

        this.EditTextnumero = (EditText) findViewById(R.id.editTextnumero);
        this.EditTextnumero.setOnClickListener(this);

        this.EditTextcapital = (EditText) findViewById(R.id.editTextCapital);
        this.EditTextcapital.setOnClickListener(this);
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
        if (v.equals(this.buttonValider))
        {
            String numero = this.EditTextnumero.getText().toString();
            double capital = Double.parseDouble(this.EditTextcapital.getText().toString());
            Compte compte = new Compte(numero, null, capital, false, 1, ConnexionActivity.utilisateurConnecte.getIdUtilisateur());
            compte.insertNouveauCompte(DataBaseHelper.db);

            Toast.makeText(getApplicationContext(), "Le compte a bien été ajouté", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setClass(this, CompteActivity.class);
            startActivity(intent);
        }
    }
}
