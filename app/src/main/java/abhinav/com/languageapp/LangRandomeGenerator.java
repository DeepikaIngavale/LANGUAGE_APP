package abhinav.com.languageapp;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class LangRandomeGenerator extends AppCompatActivity implements View.OnClickListener {
    DataBaseHelper db;
    TextView txt_Sentance;
    EditText edtxt_SetSentance;
    LinearLayout mylinearlayout;
    Button btn_check;
    String getSentence,sentence,EdtSentence;
    String[] Words,Word;
    boolean flag=true;
    ImageView imgv_speak;
    private TextToSpeech textToSpeech;
    ArrayList<SentenceBean> arrayList;
    int temp=0;
    int i,iCnt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_randome_generator);
        arrayList = new ArrayList<>();

        txt_Sentance=(TextView)findViewById(R.id.txt_Sentance);
        edtxt_SetSentance=(EditText) findViewById(R.id.edtxt_SetSentance);
        imgv_speak=(ImageView) findViewById(R.id.imgv_speak);
        mylinearlayout=(LinearLayout)findViewById(R.id.mylinearlayout);
        btn_check=(Button) findViewById(R.id.btn_check);

        btn_check.setOnClickListener(this);
        imgv_speak.setOnClickListener(this);
        db = new DataBaseHelper(LangRandomeGenerator.this);

        /*db.Sentences();
        Toast.makeText(this, "insert_success", Toast.LENGTH_SHORT).show();
*/
       /* db.Orders();
        Toast.makeText(this, "insert_success", Toast.LENGTH_SHORT).show();*/

        arrayList=db.getAllSentences();

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int status)
            {
                if (status == TextToSpeech.SUCCESS)
                {
                    int ttsLang = textToSpeech.setLanguage(Locale.US);
                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Log.e("TTS", "The Language is not supported!");
                    }
                    else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");
                }
                else {
                    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        final int N = 10; // total number of textviews to add

        final TextView[] myTextViews = new TextView[N]; // create an empty array;

        for (int i = 0; i < N; i++) {
            // create a new textview
            final TextView rowTextView = new TextView(this);

            // set some properties of rowTextView or something
            rowTextView.setText("This is row #" + i);

            // add the textview to the linearlayout
            mylinearlayout.addView(rowTextView);

            // save a reference to the textview for later
            myTextViews[i] = rowTextView;
        }
       // txt_Sentance.setText(arrayList.get(temp).getSentence().trim());
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_check)
        {

            getSentence=arrayList.get(0).getSentence();
            //txt_Sentance.setText(arrayList.get(0).getSentence());
            int speechStatus = textToSpeech.speak(getSentence, TextToSpeech.QUEUE_FLUSH, null);
            if (speechStatus == TextToSpeech.ERROR)
            {
                Log.e("TTS", "Error in converting Text to Speech!");
            }
                String Sentence = arrayList.get(0).getSentence();
                EdtSentence=edtxt_SetSentance.getText().toString().trim();
                Words = Sentence.split(" ");
                Word = EdtSentence.split(" ");

                if(!EdtSentence.equals(""))
                {
                    for(int i=0;i< Word.length;i++)
                    {
                        for (int j=0;j<Words.length;j++)
                        {
                            if(Word[i].equalsIgnoreCase(Words[j]))
                            {
                                iCnt++;
                            }
                        }
                    }

                    if(Word.length==iCnt)
                    {
                        Toast.makeText(this, "The sentence is correct", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this, "Sentence is not correct", Toast.LENGTH_SHORT).show();
                    }
                    iCnt=0;

                   /* String Sentence = arrayList.get(temp).getSentence();
                    int icnt=0;
                    for(int i=0;i<Sentence.length();i++)
                    {
                        if(Sentence.contains(Word[i]))
                        {
                            icnt++;
                        }
                    }
                    if(icnt==Sentence.length())
                    {
                        Toast.makeText(this, "The Sentence Is Correct", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this, "The Sentence Is Not Correct", Toast.LENGTH_SHORT).show();
                    }*/
                }
                else
                {
                    Toast.makeText(this, "Enter Sentence", Toast.LENGTH_SHORT).show();
                }
            if(temp<arrayList.size()-1)
            {
                temp++;
                edtxt_SetSentance.setText("");
            }
        }
    }
    public boolean Check()
    {
        for (int i = 0; i < Words.length; i++)
        {
            for (int j = 0; j < Word.length; j++)
            {
                if (Words[i].contains(Word[j]))
                {
                    continue;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
    @Override
    public void onDestroy()
    { super.onDestroy();
        if (textToSpeech != null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}



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



 /*for(int x=0;x<arrayList.size()-1;x++)
            {
                EdtSentence = edtxt_SetSentance.getText().toString().trim();
                System.out.println("EDIT TEXT SENTENCE" + EdtSentence);
                Word = EdtSentence.split(" ");

                if(EdtSentence.equals(" "))
                {
                    if (Word.length != Words.length)
                    {
                        flag = false;
                        Toast.makeText(this, "The Sentence Is Not Correct", Toast.LENGTH_SHORT).show();
                    }
                    for (int i = 0; i < Word.length; i++) {
                        for (int j = 0; j < Words.length; j++) {
                            if (Word[i].equalsIgnoreCase(Words[j])) {
                                continue;
                            } else {
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (!flag) {
                        Toast.makeText(this, "The Sentence Is Not Correct", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "The Sentence Is Correct", Toast.LENGTH_SHORT).show();
                    }
                }
            }*/



/*getSentence=arrayList.get(temp).getSentence();
        txt_Sentance.setText(getSentence);
        Words = getSentence.split(" ");
        for(int i=0;i<Words.length;i++)
        {
            System.out.println("WORD OF TEXTVIEW => " + Words[i]);
        }
        EdtSentence = edtxt_SetSentance.getText().toString().trim();
        System.out.println("EDIT TEXT SENTENCE"+EdtSentence);
        if (view.getId() == R.id.btn_check)
        {
            Word = EdtSentence.split(" ");
            for(int i=0;i<Words.length;i++)
            {
                System.out.println("WORDS => " + Words[i]);
            }

           *//* if(EdtSentence.equals(""))
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
            }*//*
            if(temp<arrayList.size())
            {
                temp++;
            }
        }*/

/*textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener()
{
@Override
public void onInit(int status)
{
if (status == TextToSpeech.SUCCESS)
{
int ttsLang = textToSpeech.setLanguage(Locale.US);
 if (ttsLang == TextToSpeech.LANG_MISSING_DATA || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED)
  {
  Log.e("TTS", "The Language is not supported!");
  }
  else {
   Log.i("TTS", "Language Supported.");
    }
    Log.i("TTS", "Initialization success.");
    }
    else {
    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
    }
    }
     });
      btn.setOnClickListener(new View.OnClickListener()
      {
      @Override
       public void onClick(View arg0)
       {
       String data = editText.getText().toString();
        Log.i("TTS", "button clicked: " + data);
        int speechStatus = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);
        if (speechStatus == TextToSpeech.ERROR)
         {
         Log.e("TTS", "Error in converting Text to Speech!");
         } } });
         }
         @Override
         public void onDestroy()
         { super.onDestroy();
          if (textToSpeech != null)
           {
           textToSpeech.stop();
           textToSpeech.shutdown();
           } }*/