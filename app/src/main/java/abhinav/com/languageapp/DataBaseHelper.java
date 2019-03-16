package abhinav.com.languageapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import static android.os.Build.ID;

public class DataBaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "database.db";
    public static final int DATABASE_VERSION = 1;
    private Context context;
    SQLiteDatabase db;

    public static final String TABLE_SENTENCE_DETAILS = "SentenceTable";
    private static final String ID = "id";
    private static final String SENTENCE = "sentence";

    private static final String TABLE_ORDER_DETAILS = "OrderTable";
    public static final String id = "id";
    public static final String ORDER = "orderS";
    public static final String SENTENCEID = "sentenceid";
    private static final String WORD = "word";

    public DataBaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String create_sentence_details =
                "create table if not exists "+TABLE_SENTENCE_DETAILS
                        +"("+ID+" integer primary key,"
                        +SENTENCE+" text);";
        sqLiteDatabase.execSQL(create_sentence_details);

        String create_order_details =
                "create table if not exists "+TABLE_ORDER_DETAILS
                        +"("+id+" integer primary key,"
                        +ORDER+" text,"
                        +WORD+" text,"
                        +SENTENCEID+" integer,"
                        +"FOREIGN KEY("+SENTENCEID+")REFERENCES "+TABLE_SENTENCE_DETAILS+"("+ID+"));";
        sqLiteDatabase.execSQL(create_order_details);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void open() throws SQLException
    {
        db = getWritableDatabase();
    }
    public void Sentences()
    {
        open();
        ContentValues values = new ContentValues();

        /*values.put(ID, "1");
        values.put(SENTENCE, "A Sentence is full of values");*/

       /* values.put(ID, "2");
        values.put(SENTENCE, "Have a beautiful Page");*/

        /*values.put(ID, "3");
        values.put(SENTENCE, "Every Next level of your life will Demand A Different You");*/

        db.insert(TABLE_SENTENCE_DETAILS, ID, values);
        db.close();
    }

    public void Orders()
    {
        open();
        ContentValues values = new ContentValues();
        /*values.put(id, 1);
        values.put(ORDER, 1);
        values.put(WORD, "A");
        values.put(SENTENCEID, 1);*/

        /*values.put(id, 2);
        values.put(ORDER, 2);
        values.put(WORD, "Sentence");
        values.put(SENTENCEID, 1);*/

        /*values.put(id, 3);
        values.put(ORDER, 3);
        values.put(WORD, "is");
        values.put(SENTENCEID, 1);*/

        /*values.put(id, 4);
        values.put(ORDER, 4);
        values.put(WORD, "full");
        values.put(SENTENCEID, 1);*/

        /*values.put(id, 5);
        values.put(ORDER, 5);
        values.put(WORD, "of");
        values.put(SENTENCEID, 1);*/

        /*values.put(id, 6);
        values.put(ORDER, 6);
        values.put(WORD, "values");
        values.put(SENTENCEID, 1);*/

       /* values.put(id, 7);
        values.put(ORDER, 1);
        values.put(WORD, "Have");
        values.put(SENTENCEID, 2);*/

        /*values.put(id, 8);
        values.put(ORDER, 2);
        values.put(WORD, "a");
        values.put(SENTENCEID, 2);*/

        /*values.put(id, 9);
        values.put(ORDER, 3);
        values.put(WORD, "beautiful");
        values.put(SENTENCEID, 2);*/

       /* values.put(id, 10);
        values.put(ORDER, 4);
        values.put(WORD, "Page");
        values.put(SENTENCEID, 2);*/

       /* values.put(id, 11);
        values.put(ORDER, 1);
        values.put(WORD, "Every");
        values.put(SENTENCEID, 3);*/

        /*values.put(id, 12);
        values.put(ORDER, 2);
        values.put(WORD, "Next");
        values.put(SENTENCEID, 3);*/

        /*values.put(id, 13);
        values.put(ORDER, 3);
        values.put(WORD, "level");
        values.put(SENTENCEID, 3);*/

        /*values.put(id, 14);
        values.put(ORDER, 4);
        values.put(WORD, "of");
        values.put(SENTENCEID, 3);*/

        /*values.put(id, 15);
        values.put(ORDER, 5);
        values.put(WORD, "your");
        values.put(SENTENCEID, 3);*/

        /*values.put(id, 16);
        values.put(ORDER, 6);
        values.put(WORD, "life");
        values.put(SENTENCEID, 3);*/

        /*values.put(id, 17);
        values.put(ORDER, 7);
        values.put(WORD, "will");
        values.put(SENTENCEID, 3);*/

        /*values.put(id, 18);
        values.put(ORDER, 8);
        values.put(WORD, "Demand");
        values.put(SENTENCEID, 3);*/

        /*values.put(id, 19);
        values.put(ORDER, 9);
        values.put(WORD, "A");
        values.put(SENTENCEID, 3);*/

       /* values.put(id, 20);
        values.put(ORDER, 10);
        values.put(WORD, "Different");
        values.put(SENTENCEID, 3);*/

        /*values.put(id, 21);
        values.put(ORDER, 11);
        values.put(WORD, "You");
        values.put(SENTENCEID, 3);*/

        /*values.put(id, 22);
        values.put(ORDER, 1);
        values.put(WORD, "You");
        values.put(SENTENCEID, 4);*/

        db.insert(TABLE_ORDER_DETAILS, null, values);
        db.close();
    }
    public void getAllSentences()
    {
        open();
        Cursor c = db.query(TABLE_SENTENCE_DETAILS, null, null,
                null, null, null, null);

        int returned_rows = c.getCount();
        if(returned_rows>0)
        {
            c.moveToFirst();
            do{
                String id = c.getString(c.getColumnIndex(ID));
                String sentence = c.getString(c.getColumnIndex(SENTENCE));
                System.out.println("ID => "+id);
                System.out.println("SENTENCE => "+sentence);
            }while (c.moveToNext());
        }
        db.close();
    }
}
