package cd.synah.androidhack.gui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import cd.synah.androidhack.R;
import cd.synah.androidhack.controller.HackDatabaseAPI;

/**
 * Created by Michelo on 28/11/15.
 */
public class UserListActivity extends AppCompatActivity{

    ListView lv;
    private HackDatabaseAPI myCon;
    private SimpleCursorAdapter data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Le code ICI

        lv=(ListView)findViewById(R.id.monlistview);

        myCon=new HackDatabaseAPI(this);
        myCon.open();

        displayAllUserInDb();

    }

    //Appel de la méthode qui va afficher la liste à partir de la base des données

    private void  displayAllUserInDb(){

        Cursor cursor=myCon.getAllUser();

        //Définition des colonnes concernées

        String[] colonnes=new String[]{
                HackDatabaseAPI.key_usern,
                HackDatabaseAPI.key_title
        };

        //Spécification de la destination
        int[] to=new int[]{R.id.tvusern, R.id.tvtitle};

        data=new SimpleCursorAdapter(this,
                R.layout.list_item_layout,
                cursor,
                colonnes,
                to,
                0
                );
        lv.setAdapter(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor1=(Cursor)lv.getItemAtPosition(position);
                String UserName=cursor1.getString(cursor1.getColumnIndexOrThrow("username"));
                Toast.makeText(getApplicationContext(),UserName,Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    public void onRestart() {
        Log.v("###", "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume(){
        super.onResume();
        displayAllUserInDb();
    }

    @Override
    protected void onDestroy(){
        myCon.close();
        super.onDestroy();	}





}
