package fr.utt.if26.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;

import fr.utt.if26.myapplication.Class.ClassDb.Compte;
import fr.utt.if26.myapplication.Class.ClassDb.Recette;
import fr.utt.if26.myapplication.Class.DataBaseHelper;
import fr.utt.if26.myapplication.Class.UtilMenu;

public class RecetteActivity extends AppCompatActivity implements OnClickListener {

    TextView textViewIdCompte;
    ListView listViewRecette;
    Button buttonAjouterRecette;

    ArrayList<Recette> listeRecette;

    public static Recette recetteSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recette);

        this.textViewIdCompte = (TextView)findViewById(R.id.textViewLabel);

        this.listViewRecette = (ListView)findViewById(R.id.listViewRecette);

        this.buttonAjouterRecette = (Button)findViewById(R.id.buttonAjouterRecette);
        this.buttonAjouterRecette.setOnClickListener(this);

        Compte compte = null;
        textViewIdCompte.setText("");
        try {
            compte = Compte.getCompteById(CompteActivity.idCompte);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            double montantEnAttente = compte.getCaptitalCompteEnAttent();
            if(montantEnAttente != compte.getCapital()) {
                textViewIdCompte.append("Compte " + compte.getNumero() + " \n " +
                        "Montant réel " + compte.getCapital() + '\n' +
                        "Montant avec paiement en attente " + montantEnAttente);
            }
            else
            {
                textViewIdCompte.append("Compte " + compte.getNumero() + " \n " +
                        "Montant réel " + compte.getCapital());
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.listeRecette = new ArrayList<Recette>();

        try {
            listeRecette = Recette.getAllRecetteByIdCompte(CompteActivity.idCompte, DataBaseHelper.db);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayAdapter<Recette> adapter = new ArrayAdapter<Recette>(this, android.R.layout.simple_list_item_1 , listeRecette){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View row = super.getView(position, convertView, parent);
                TextView text = (TextView) row.findViewById(android.R.id.text1);

                if(listeRecette.get(position).getIdEtat() == 1)
                {
                    text.setTextColor(Color.BLACK);
                    row.setBackgroundColor(Color.parseColor("#D3D3D3"));
                }
                else
                {
                    if(listeRecette.get(position).getMontant() < 0)
                    {
                        text.setTextColor(Color.RED);
                    }
                    else
                    {
                        text.setTextColor(Color.parseColor("#16B84E"));
                    }
                }

                //Rest of your code
                return row;
            }
        };
        this.listViewRecette.setAdapter(adapter);

        this.listViewRecette.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                recetteSelect = listeRecette.get(position);

                Intent intent = new Intent(RecetteActivity.this, ConsulterRecetteActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(this.listViewRecette);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listViewRecette) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuInfo;

            menu.add(0, v.getId(), 0, "Consulter");
            menu.add(0, v.getId(), 0, "Modifier");
            menu.add(0, v.getId(), 0, "Supprimer");
        }
        if (v.getId() == R.id.buttonAjouterRecette) {

            Button lv = this.buttonAjouterRecette;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuInfo;
            menu.add(0, lv.getId(), 0, "Ajouter une dépense");
            menu.add(0, lv.getId(), 0, "Ajouter une recette");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        View view = info.targetView;
        String key = ((TextView) info.targetView).getText().toString();

        Intent intent = new Intent();
        switch (item.getTitle().toString()){
            case "Consulter":
                Toast.makeText(getApplicationContext(), "Consulter", Toast.LENGTH_LONG).show();
                return true;

            case "Modifier":
                try {
                    recetteSelect = Recette.getRecetteById(listeRecette.get(info.position).getIdRecette());
                    intent.setClass(this, ModifierRecetteActivity.class);
                    startActivity(intent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Modifier", Toast.LENGTH_LONG).show();
                return true;

            case "Supprimer":
                try {
                    Recette.deleteRecette(listeRecette.get(info.position).getIdRecette());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                intent.setClass(this, RecetteActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "La dépense a été supprimée", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
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
        if (v.equals(this.buttonAjouterRecette))
        {
            Intent intent = new Intent();
            intent.setClass(this, AjouterRecetteActivity.class);
            startActivity(intent);
            //openOptionsMenu();
        }
    }
}
