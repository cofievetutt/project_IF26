package fr.utt.if26.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
import fr.utt.if26.myapplication.Class.ClassDb.Recette;
import fr.utt.if26.myapplication.Class.DataBaseHelper;
import fr.utt.if26.myapplication.Class.UtilMenu;

public class CompteActivity extends AppCompatActivity implements OnClickListener{

    Button buttonAjouterCompte;
    ListView listViewCompte;

    ArrayList<Compte> listeCompte = new ArrayList<Compte>();

    public static int idCompte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compte);

        this.buttonAjouterCompte = (Button) findViewById(R.id.buttonAjouterCompte);
        this.buttonAjouterCompte.setOnClickListener(this);

        this.listViewCompte = (ListView) findViewById(R.id.listViewCompte);


        try {
            this.listeCompte = Compte.getAllComptesByIdUtilisateur(ConnexionActivity.utilisateurConnecte.getIdUtilisateur(), DataBaseHelper.db);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final ArrayAdapter<Compte> adapter = new ArrayAdapter<Compte>(this, android.R.layout.simple_list_item_1 , this.listeCompte){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View row = super.getView(position, convertView, parent);

                if(listeCompte.get(position).getCapital() < 0)
                {
                    row.setBackgroundColor(Color.parseColor("#7F0000"));
                }
                else
                {
                    row.setBackgroundColor(Color.parseColor("#006600"));

                }

                //Rest of your code
                return row;
            }
        };

        this.listViewCompte.setAdapter(adapter);

        listViewCompte.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Compte compte = listeCompte.get(position);
                idCompte = compte.getIdCompte();

                Intent intent = new Intent( CompteActivity.this, RecetteActivity.class );
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        UtilMenu.createMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        UtilMenu.selectOptionMenu(item, this, this);
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
