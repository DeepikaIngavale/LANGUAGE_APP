package abhinav.com.languageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LangRandomeGenerator extends AppCompatActivity implements View.OnClickListener {
    DataBaseHelper db;
    TextView txt_Sentance;
    EditText edtxt_SetSentance;
    Button btn_check;
    String getSentence,EdtSentence;
    String[] Words,Word;
    boolean flag=true;
    ArrayList<SentenceBean> arrayList;
    int temp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_randome_generator);
        arrayList = new ArrayList<>();

        txt_Sentance=(TextView)findViewById(R.id.txt_Sentance);
        edtxt_SetSentance=(EditText) findViewById(R.id.edtxt_SetSentance);
        btn_check=(Button) findViewById(R.id.btn_check);
        btn_check.setOnClickListener(this);
        db = new DataBaseHelper(LangRandomeGenerator.this);
        arrayList=db.getAllSentences();


        //db.Sentences();
        //db.Orders();

        /*sentence=db.getSentence("1");
        txt_Sentance.setText(sentence);

        Words = sentence.split(" ");*/

       /* for (String Word : Words)
        {
            System.out.println("WORDS => " + Word);
        }*/

        /*for(int i=0;i<Words.length;i++)
        {
            System.out.println("WORDS => " + Words[i]);
        }*/

        //Toast.makeText(this, "insert_success", Toast.LENGTH_SHORT).show();
       /* dbSentence.getAllSentences();
        Toast.makeText(this, "sentences", Toast.LENGTH_SHORT).show();*/

        /*for(int i=0;i<arrayList.size();i++)
        {
            //System.out.println("The Sentences In The DataBase => "+arrayList.get(i).getSentence());
        }*/
    }


    @Override
    public void onClick(View view)
    {
        getSentence=arrayList.get(temp).getSentence();
        txt_Sentance.setText(getSentence);
        Words = getSentence.split(" ");
        for(int i=0;i<Words.length;i++)
        {
            System.out.println("WORD OF TEXTVIEW => " + Words[i]);
        }
        if (view.getId() == R.id.btn_check)
        {
            EdtSentence = edtxt_SetSentance.getText().toString().trim();
            System.out.println("EDIT TEXT SENTENCE"+EdtSentence);
            Word = EdtSentence.split(" ");
            /*for(int i=0;i<Words.length;i++)
            {
                System.out.println("WORDS => " + Words[i]);
            }*/

            for (int i = 0; i < Word.length; i++)
            {
                for (int j = 0; j < Words.length; j++)
                {
                    if (Word[i].equalsIgnoreCase(Words[j]))
                    {
                        System.out.println("Word Is =>" + Word[i]);
                        continue;
                    } else
                    {
                        flag = false;
                        break;
                    }
                }
            }
            if (!flag)
            {
                Toast.makeText(this, "The Sentence Is Not Correct", Toast.LENGTH_SHORT).show();
            } else
            {
                Toast.makeText(this, "The Sentence Is Correct", Toast.LENGTH_SHORT).show();
            }

           /* if(EdtSentence.equals(""))
            {
                flag=false;
                Toast.makeText(this, "Enter The Sentence ", Toast.LENGTH_LONG).show();
            }
            else
            {
                if (Word.length != Words.length)
                {
                    flag = false;
                    Toast.makeText(this, "The Sentence Is Not Correct", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    for (int i = 0; i < Word.length; i++)
                    {
                        for (int j = 0; j < Words.length; j++)
                        {
                            if (Word[i].equalsIgnoreCase(Words[j]))
                            {
                                System.out.println("Word Is =>" + Word[i]);
                                continue;
                            } else
                            {
                                flag = false;
                                break;
                            }
                        }
                    }
                }
            }
            if (!flag)
            {
                Toast.makeText(this, "The Sentence Is Not Correct", Toast.LENGTH_SHORT).show();
            } else
            {
                Toast.makeText(this, "The Sentence Is Correct", Toast.LENGTH_SHORT).show();
            }*/
            if(temp<arrayList.size())
            {
                temp++;
            }
        }
    }
}
