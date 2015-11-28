package cd.synah.androidhack.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import cd.synah.androidhack.model.User;

/**
 * Created by Michelo on 28/11/15.
 */
public class HackDatabaseAPI {

    public static final String key_id="_id";

    public static final String key_usern="username";
    public static final String key_pwd="password";
    public static final String key_title="title";

    private static final String TAG="HackDatabaseAPI";
    private static final String table_user="User";


    private static final String ma_Base_ddonne="HackDb";
    private static final int bdversion=1;


    private static Context hctx;
    private HackDatabaseHelper hackHelper;
    private SQLiteDatabase HackDb;


    //Code de création de la base des données
    private static final String creation_table_USER=
            "CREATE TABLE if not exists " + table_user +"(" +
                    key_id + " integer primary key autoincrement, " +
                    key_usern + " text not null," +
                    key_pwd + " text not null," +
                    key_title + " text not null)";


    //Create SQLiteOpenHelper class
    private static class HackDatabaseHelper extends SQLiteOpenHelper{

        //constructor
        HackDatabaseHelper(Context c){
            super(c,ma_Base_ddonne,null,bdversion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(creation_table_USER);

            //showing the log
            Log.w(TAG,creation_table_USER);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Met a jour la BD de la version " + oldVersion + " a la version "
                    + newVersion + ", vos donnees seront effacees.");
            db.execSQL("DROP TABLE IF EXISTS " + table_user);
            onCreate(db);
        }

    }


    //API Contructor

    public HackDatabaseAPI(Context htx){
        this.hctx=htx;
    }

    //methods for open && close the API
    public HackDatabaseAPI open() throws SQLException{
        hackHelper=new HackDatabaseHelper(hctx);
        HackDb=hackHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        if(hackHelper != null){
            hackHelper.close();
        }
    }


    //Opérations on tables : CRUD

    public long insertNewUser(User person){
        ContentValues data=new ContentValues();
        data.put(key_usern,person.getUsername());
        data.put(key_pwd,person.getPassword());
        data.put(key_title,person.getTitle());

        return HackDb.insert(table_user,null,data);
    }


    public  boolean deleteAllUser(){
        int doneDel=0;
        doneDel=HackDb.delete(table_user,null,null);
        Log.w(TAG,Integer.toString(doneDel));
        return doneDel>1;
    }

    public boolean deleteOneUser(User personne){
        return(
                HackDb.delete(table_user,
                        key_usern +"="+ personne.getUsername(),null)>0
                );
    }


    //Récupération des données pour affichage sur une liste

    public Cursor getAllUser(){
        Cursor data=HackDb.query(table_user,new String[]{key_id,key_usern,key_pwd,key_title},null,null,null,null,null);
        if(data != null){
            data.moveToFirst();
        }
        return data;
    }

    public Cursor getOneUser(String usern) throws SQLException{
        Cursor data=null;
        if(usern.toString() != null){
            data=HackDb.query(table_user,new String[]{key_id,key_usern,key_pwd,key_title}
                 , key_usern + "=" + usern,null,null,null,null
            );
        }
        if(data != null){
            data.moveToFirst();
        }
        return data;
    }




}
