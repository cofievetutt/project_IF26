package fr.utt.if26.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import fr.utt.if26.myapplication.Class.DataBaseHelper;
import fr.utt.if26.myapplication.Class.UtilMenu;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    Button buttonInscription;
    Button buttonConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buttonInscription = (Button) findViewById(R.id.buttonInscription);
        this.buttonInscription.setOnClickListener(this);

        this.buttonConnexion = (Button)findViewById(R.id.buttonConnexion);
        this.buttonConnexion.setOnClickListener(this);

        DataBaseHelper.db = new DataBaseHelper(this);

        if(ConnexionActivity.utilisateurConnecte != null)
        {
            this.buttonConnexion.setVisibility(View.GONE);
            this.buttonInscription.setVisibility(View.GONE);
        }
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
            case UtilMenu.MENU_INSCRIPTION:
                Toast.makeText(getApplicationContext(), "Inscription", Toast.LENGTH_LONG).show();
                intent.setClass(this, InscriptionActivity.class);
                startActivity(intent);
                break;
            case UtilMenu.MENU_COMPTE:
                Toast.makeText(getApplicationContext(), "Liste des comptes", Toast.LENGTH_LONG).show();
                intent.setClass(MainActivity.this, CompteActivity.class);
                startActivity(intent);
                break;
            case UtilMenu.MENU_DECONNEXION:
                Toast.makeText(getApplicationContext(), "Au revoir " + ConnexionActivity.utilisateurConnecte.getPrenom() + " " + ConnexionActivity.utilisateurConnecte.getNom(), Toast.LENGTH_LONG).show();
                ConnexionActivity.utilisateurConnecte = null;

                intent.setClass(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
       return false;
    }

    @Override
    public void onClick(View view) {
        if (view.equals(this.buttonInscription))
        {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,InscriptionActivity.class);
            startActivity(intent);
        }

        if(view.equals(this.buttonConnexion))
        {
            if(ConnexionActivity.utilisateurConnecte == null) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ConnexionActivity.class);
                startActivity(intent);
            }
            /*else
            {
                Toast.makeText(getApplicationContext(), "Au revoir " + ConnexionActivity.utilisateurConnecte.getPrenom() + " " + ConnexionActivity.utilisateurConnecte.getNom(), Toast.LENGTH_LONG).show();
                ConnexionActivity.utilisateurConnecte = null;
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }*/
        }
    }
}
