package com.example.kamil.myapplication.DataBase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Kamil on 05.12.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private Context mycontext;

    //private String DB_PATH = mycontext.getApplicationContext().getPackageName()+"/databases/";
    private static String DB_NAME = DataBaseStructure.DATA_BASE_NAME;
    public SQLiteDatabase myDataBase;
    private String DB_PATH;

    public DataBaseHelper(Context context) throws IOException {
        super(context,DB_NAME,null,1);
        this.mycontext=context;
        boolean dbexist = checkdatabase();
        if (dbexist) {
            //System.out.println("Database exists");
            //opendatabase();
        } else {
            System.out.println("Database doesn't exist");
            createdatabase();
        }

    }

    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if(dbexist) {
            //System.out.println(" Database exists.");
        } else {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch(IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkdatabase() {
        //SQLiteDatabase checkdb = null;
        boolean checkdb = false;
        try {
            String myPath = "/data/data/"
                    + mycontext.getApplicationContext().getPackageName()+ "/databases/donttouch.db";
            File dbfile = new File(myPath);
            //checkdb = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
            checkdb = dbfile.exists();
        } catch(SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outfilename = "/data/data/"
                + mycontext.getApplicationContext().getPackageName()+ "/databases/donttouch.db";
        Log.v("Path", outfilename);

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[5120];
        int length;
        while ((length = myinput.read(buffer))>0) {
            myoutput.write(buffer,0,length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if(myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

}

