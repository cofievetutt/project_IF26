package fr.utt.if26.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;

import fr.utt.if26.myapplication.Class.ClassDb.Compte;
import fr.utt.if26.myapplication.Class.ClassDb.Recette;
import fr.utt.if26.myapplication.Class.DataBaseHelper;
import fr.utt.if26.myapplication.Class.UtilMenu;

public class AccueilActivity extends AppCompatActivity implements OnClickListener{

    ListView listViewAccueil;
    ArrayList<Compte> listeCompte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

        this.listViewAccueil = (ListView) findViewById(R.id.listViewAccueil);
        //this.listViewAccueil.setOnClickListener(this);

        final ArrayList<Integer> positionComtpe = new ArrayList<Integer>();
        final ArrayList<Recette> positionRecette = new ArrayList<Recette>();

        try {
            listeCompte = Compte.getAllComptesByIdUtilisateur(ConnexionActivity.utilisateurConnecte.getIdUtilisateur(), DataBaseHelper.db);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int t = 0;
        ArrayList<String> adapter = new ArrayList<String>();

        for(int i = 0; i < listeCompte.size(); i++)
        {
            ArrayList<Recette> listeRecette = new ArrayList<>();
            try {
                listeRecette = Recette.getAllRecetteByIdCompteOrderBy1Desc(listeCompte.get(i).getIdCompte(), DataBaseHelper.db);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            positionComtpe.add(i + t);

            adapter.add(listeCompte.get(i).toString());
            positionRecette.add(null);

            for(int j = 0; j < listeRecette.size(); j++)
            {
                adapter.add(listeRecette.get(j).toString());
                positionRecette.add(listeRecette.get(j));
                t++;
            }
        }

        ArrayAdapter<String> displayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , adapter){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View row = super.getView(position, convertView, parent);
                TextView text = (TextView) row.findViewById(android.R.id.text1);

                if(positionComtpe.contains(position))
                {
                    text.setTextColor(Color.BLACK);
                    row.setBackgroundColor(Color.parseColor("#D3D3D3"));

                }
                else
                {
                    if(positionRecette.get(position).getMontant() < 0)
                    {
                        text.setTextColor(Color.RED);
                        //row.setBackgroundColor(Color.parseColor("#DE2916"));
                    }
                    else
                    {
                        text.setTextColor(Color.parseColor("#16B84E"));
                        //row.setBackgroundColor(Color.parseColor("#32CD32"));
                    }
                }

                //Rest of your code
                return row;
            }
        };
        this.listViewAccueil.setAdapter(displayAdapter);
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

    }
}
