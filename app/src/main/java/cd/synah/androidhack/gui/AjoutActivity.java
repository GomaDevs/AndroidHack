package cd.synah.androidhack.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cd.synah.androidhack.R;
import cd.synah.androidhack.controller.HackDatabaseAPI;
import cd.synah.androidhack.model.User;

/**
 * Created by Michelo on 28/11/15.
 */
public class AjoutActivity extends AppCompatActivity {

    private Button btnAdd, btnDelete;
    private EditText edUser;
    private EditText edPwd;
    private EditText edTitre;

    private User Utilisateur = new User();

    HackDatabaseAPI myCon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // Le code ICI

        edUser = (EditText) findViewById(R.id.ed_username);
        edPwd = (EditText) findViewById(R.id.ed_password);
        edTitre = (EditText) findViewById(R.id.ed_title);

        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnDelete = (Button) findViewById(R.id.buttonDel);

        //Initialisation de la base des données ici
        myCon = new HackDatabaseAPI(this);
        myCon.open();

        //Creation des Listener pour les boutons
        btnAdd.setOnClickListener(quandJeClickLeButton); //Utilisation de la méthode événement

        //Listener type 2
        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserListActivity.class));
            }
        });

    }


    //Method View.OnClickListener

    private View.OnClickListener quandJeClickLeButton = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Utilisateur.setUsername(edUser.getText().toString());
            Utilisateur.setPassword(edPwd.getText().toString());
            Utilisateur.setTitle(edTitre.getText().toString());

            //insert dans la base des données
            myCon.insertNewUser(Utilisateur);

          //  Toast.makeText(getApplicationContext(), edUser.getText().toString() + "/" + edPwd.getText().toString() + "Nouveau cours a ete enregistre", Toast.LENGTH_SHORT).show();
            edUser.setText("");
            edPwd.setText("");
            edTitre.setText("");

        }
    };


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
