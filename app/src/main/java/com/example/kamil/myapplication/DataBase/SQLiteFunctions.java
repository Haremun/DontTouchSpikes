package com.example.kamil.myapplication.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kamil.myapplication.Structures_and_functions.Functions;

/**
 * Created by Kamil on 19.11.2017.
 */

public class SQLiteFunctions{

    private Context context;

    public static Cursor LoadOneRowById(Context context, int id){

        String string = context.getApplicationContext().getPackageName() + "/databases/" + DataBaseStructure.DATA_BASE_NAME;
        SQLiteDatabase base = context.openOrCreateDatabase(DataBaseStructure.DATA_BASE_NAME, SQLiteDatabase.OPEN_READWRITE, null);
        //SQLiteDatabase base2 = SQLiteDatabase.openDatabase(string, null, SQLiteDatabase.OPEN_READWRITE);
        String kolumny[] = {DataBaseStructure.ID_COLUMN_NAME, DataBaseStructure.BACKGROUND_COLUMN_NAME, DataBaseStructure.SPIKES_COLUMN_NAME};
        Cursor cursor = base.query(DataBaseStructure.TABLE_NAME, kolumny, DataBaseStructure.ID_COLUMN_NAME + "=?", new String[] {Integer.toString(id)},
                                  null, null, null);
        cursor.moveToLast();

        base.close();

        return cursor;
    }
}
