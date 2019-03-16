package abhinav.com.languageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class LangRandomeGenerator extends AppCompatActivity
{
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_randome_generator);

        db = new DataBaseHelper(LangRandomeGenerator.this);
        //db.Sentences();
        db.Orders();

        Toast.makeText(this, "insert_success", Toast.LENGTH_SHORT).show();
       /* dbSentence.getAllSentences();
        Toast.makeText(this, "sentences", Toast.LENGTH_SHORT).show();*/
    }
}
