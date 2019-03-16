package abhinav.com.languageapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DataBaseHelperLogin extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "login.db";
    public static final int DATABASE_VERSION = 1;
    private Context context;
    SQLiteDatabase db;

    private static final String TABLE_LOGIN_DETAILS = "login";
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    public DataBaseHelperLogin(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String create_login_details =
                "create table if not exists "+TABLE_LOGIN_DETAILS
                        +"("+ID+" integer primary key autoincrement,"
                        +EMAIL+" text, "
                        +PASSWORD+" text);";
        sqLiteDatabase.execSQL(create_login_details);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void open() throws SQLException
    {
        db = getWritableDatabase();
    }
    public long insertLoginDetails(String email,String password)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(EMAIL, email);
        values.put(PASSWORD, password);
        long insertid = db.insert(TABLE_LOGIN_DETAILS, EMAIL, values);
        db.close();
        return insertid;
    }
    public void getAllLoginDetails()
    {
        open();
        Cursor c = db.query(TABLE_LOGIN_DETAILS, null, null,
                null, null, null, null);

        int returned_rows = c.getCount();
        if(returned_rows>0)
        {
            c.moveToFirst();
            do{
                String email = c.getString(c.getColumnIndex(EMAIL));
                String password = c.getString(c.getColumnIndex(PASSWORD));
                System.out.println("EMAIL => "+email);
                System.out.println("PASSWORD => "+password);
            }while (c.moveToNext());
        }
        db.close();
    }
}
