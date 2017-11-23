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
import android.widget.RadioButton;
import android.widget.Toast;

import fr.utt.if26.myapplication.Class.DataBaseHelper;
import fr.utt.if26.myapplication.Class.ClassDb.Utilisateur;
import fr.utt.if26.myapplication.Class.UtilMenu;

public class InscriptionActivity extends AppCompatActivity implements OnClickListener{

    public Button buttonValider;
    public EditText textNom;
    public RadioButton rbMonsieur;
    public RadioButton rbMadame;

    public String ErrorMessage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);

        ((RadioButton) findViewById(R.id.radioButtonMonsieur)).setChecked(true);

        this.buttonValider = (Button)findViewById(R.id.buttonValider);
        this.buttonValider.setOnClickListener(this);

        this.textNom = (EditText) findViewById(R.id.editTextNom);
        this.textNom.setOnClickListener(this);

        this.rbMonsieur = ((RadioButton) findViewById(R.id.radioButtonMonsieur));
        this.rbMonsieur.setOnClickListener(this);

        this.rbMadame = ((RadioButton) findViewById(R.id.radioButtonMadame));
        this.rbMadame.setOnClickListener(this);
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
    public void onClick(View view) {
        if(view.equals(this.buttonValider))
        {
            this.ErrorMessage = "";
            boolean b = false;
            EditText editTextNom =  findViewById(R.id.editTextNom);
            EditText editTextNomMarital =findViewById(R.id.editTextNomMarital);
            EditText editTextPrenom =  findViewById(R.id.editTextPrenom);
            EditText editTextEmail = findViewById(R.id.editTextEmail);
            EditText editTextIdentifiant = findViewById(R.id.editTextIdentifiant);
            EditText editTextMotDePasse = findViewById(R.id.editTextMotDePasse);
            EditText editTextConfirmerMotDePasse = findViewById(R.id.editTextConfirmerMotDePasse);
            RadioButton rbMadame = findViewById(R.id.radioButtonMadame);

            String nom = editTextNom.getText().toString();
            String nomMarital = editTextNomMarital.getText().toString();
            String prenom = editTextPrenom.getText().toString();
            String motDePasse = editTextMotDePasse.getText().toString();
            String confirmationMotDePasse = editTextConfirmerMotDePasse.getText().toString();
            String identifiant = editTextIdentifiant.getText().toString();
            boolean admin = false;
            String email = editTextEmail.getText().toString();

            if(nomMarital.isEmpty())
                nomMarital = null;

            if(nom.isEmpty() && prenom.isEmpty() && motDePasse.isEmpty() && confirmationMotDePasse.isEmpty() && identifiant.isEmpty() && email.isEmpty())
            {
                b = true;
            }
            else
            {
                ErrorMessage += "Tours les champs ne sont pas remplis \n";
            }

            int idGenre = 1;
            if(rbMadame.isChecked())
                idGenre = 2;

            if(!motDePasse.equals(confirmationMotDePasse)) {
                b = true;
                ErrorMessage += "Les mots de passe ne sont pas les mêmes \n";
            }


            Utilisateur user =  Utilisateur.getUtilisateurByEmail(email, DataBaseHelper.db);

            if(user != null)
            {
                b = true;
                ErrorMessage += "Cet email est déjà utilisé\n";
            }

            user =  Utilisateur.getUtilisateurByIdentifiant(identifiant, DataBaseHelper.db);

            if(user != null)
            {
                b = true;
                ErrorMessage += "\n Cet identifiant est déjà utilisé\n";
            }

            if(b == false) {
                Utilisateur utilisateur = new Utilisateur(nom, nomMarital, prenom, motDePasse, identifiant, idGenre, admin, email);
                utilisateur.insertNouvelUtilisateur(DataBaseHelper.db);
                Toast.makeText(getApplicationContext(), "Utilisateur inscrit", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(InscriptionActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else
            {

                Toast.makeText(getApplicationContext(), ErrorMessage, Toast.LENGTH_LONG).show();
            }
        }

        if(view.equals(this.rbMonsieur))
        {
            ((RadioButton) findViewById(R.id.radioButtonMadame)).setChecked(false);
            ((RadioButton) findViewById(R.id.radioButtonMonsieur)).setChecked(true);
        }

        if(view.equals(this.rbMadame))
        {
            ((RadioButton) findViewById(R.id.radioButtonMadame)).setChecked(true);
            ((RadioButton) findViewById(R.id.radioButtonMonsieur)).setChecked(false);
        }
    }
}
