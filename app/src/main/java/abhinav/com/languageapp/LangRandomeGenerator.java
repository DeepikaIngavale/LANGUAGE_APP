package abhinav.com.languageapp;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class LangRandomeGenerator extends AppCompatActivity implements View.OnClickListener, OnRecyclerItemClicked {
    DataBaseHelper db;
    TextView txt_Sentance;
    EditText edtxt_SetSentance;
    TextView txt_SetSentance;
    LinearLayout mylinearlayout;
    Button btn_check;
    String getSentence,sentence,EdtSentence;
    String[] Words,Word,ArrayWord;
    boolean flag=true;
    ImageView imgv_speak;
    private TextToSpeech textToSpeech;
    ArrayList<SentenceBean> arrayList;
    ArrayList<SentenceBean>   arrayListWord;
    ArrayList<SentenceBean>   arrayListWordNew;
    ArrayList<SentenceBean>   al_selected_words;
    int temp=0;
    int i,iCnt;
    RecyclerView rv_answer,rv_question;
    CustomAdapter customAdapter,customAdapterA;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_randome_generator);
        arrayList = new ArrayList<>();
        al_selected_words = new ArrayList<>();

        txt_Sentance=(TextView)findViewById(R.id.txt_Sentance);
        //txt_SetSentance=(TextView)findViewById(R.id.txt_SetSentance);
        edtxt_SetSentance=(EditText) findViewById(R.id.edtxt_SetSentance);
        imgv_speak=(ImageView) findViewById(R.id.imgv_speak);
        //mylinearlayout=(LinearLayout)findViewById(R.id.mylinearlayout);
        rv_answer = (RecyclerView) findViewById(R.id.rv_answer);
        rv_question = (RecyclerView) findViewById(R.id.rv_question);
        btn_check=(Button) findViewById(R.id.btn_check);

        btn_check.setOnClickListener(this);
        imgv_speak.setOnClickListener(this);
        rv_answer.setOnClickListener(this);
        rv_question.setOnClickListener(this);
        db = new DataBaseHelper(LangRandomeGenerator.this);

        /*db.Sentences();
        Toast.makeText(this, "insert_success", Toast.LENGTH_SHORT).show();
*/
       /* db.Orders();
        Toast.makeText(this, "insert_success", Toast.LENGTH_SHORT).show();*/

        arrayList=db.getAllSentences();
        //arrayListWord=db.getAllWords();
        arrayListWord=db.getWord("1");
        for(int i=0;i<arrayListWord.size();i++)
        {
            System.out.println("ARRAY OF WORS =>" + arrayListWord.get(i).getOder());
        }

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

        final int N = 6; // total number of textviews to add

        final TextView[] myTextViews = new TextView[arrayListWord.size()]; // create an empty array;
        arrayListWordNew=getRandomElement(arrayListWord,N);

        setRandomListAdapter();
        setAnswerListAdapter();

       /* for (int i = 0; i < arrayListWordNew.size(); i++)
        {
            // create a new textview
            final TextView rowTextView = new TextView(this);

            // set some properties of rowTextView or something
            rowTextView.setText(arrayListWordNew.get(i).getOder());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5,5,5,5);
            rowTextView.setPadding(15,15,15,15);
            rowTextView.setLayoutParams(params);
            // add the textview to the linearlayout
            mylinearlayout.addView(rowTextView);

            rowTextView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    txt_SetSentance.append(rowTextView.getText()+" ");
                    System.out.println("set values =>"+txt_SetSentance.getText());
                    rowTextView.setClickable(false);
                    //edtxt_SetSentance.setText(rowTextView.getText() + " ");
                }
            });

            // save a reference to the textview for later
            myTextViews[i] = rowTextView;

        }*/
    }

    public void setAnswerListAdapter()
    {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        rv_answer.setLayoutManager(staggeredGridLayoutManager);

        customAdapterA =new CustomAdapter(LangRandomeGenerator.this, al_selected_words,
                rv_answer.getId(),LangRandomeGenerator.this);
        rv_answer.setAdapter(customAdapterA);
    }

    public void setRandomListAdapter()
    {
        StaggeredGridLayoutManager staggeredGridLayoutManagerQ = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        rv_question.setLayoutManager(staggeredGridLayoutManagerQ);

        customAdapter =new CustomAdapter(LangRandomeGenerator.this,arrayListWordNew,
                rv_question.getId(),LangRandomeGenerator.this);
        rv_question.setAdapter(customAdapter);
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.imgv_speak)
        {
            getSentence=arrayList.get(0).getSentence();
            int speechStatus = textToSpeech.speak(getSentence, TextToSpeech.QUEUE_FLUSH, null);
            if (speechStatus == TextToSpeech.ERROR)
            {
                Log.e("TTS", "Error in converting Text to Speech!");
            }
        }
        if (view.getId() == R.id.btn_check)
        {
            String Sentence = arrayList.get(0).getSentence();
            for(int i=0;i<al_selected_words.size();i++)
            {
                //EdtSentence=txt_SetSentance.getText().toString().trim();
                edtxt_SetSentance.append(al_selected_words.get(i).getOder()+" ");
            }
            EdtSentence=edtxt_SetSentance.getText().toString().trim();
            Words = Sentence.split(" ");
            Word = EdtSentence.split(" ");
            if (Word.length != Words.length)
            {
                flag = false;
                Toast.makeText(this, "The Sentence Is Not Correct", Toast.LENGTH_SHORT).show();
            }
            else
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
                }
                iCnt=0;

           /* if(temp<arrayList.size()-1)
            {
                temp++;
                edtxt_SetSentance.setText("");
            }*/
        }
        edtxt_SetSentance.setText("");
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
    public ArrayList<SentenceBean> getRandomElement(ArrayList<SentenceBean> list,int totalItems)
    {
        Random rand = new Random();

        // create a temporary list for storing
        // selected element
        ArrayList<SentenceBean> newList = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {

            // take a raundom index between 0 to size
            // of given List
            int randomIndex = rand.nextInt(list.size());

            // add element in temporary list
            newList.add(list.get(randomIndex));

            // Remove selected element from orginal list
            list.remove(randomIndex);
        }
        return newList;
    }

    @Override
    public void onRecyclerItemClicked(int recycler_id, int position, Object item)
    {
        if(recycler_id==R.id.rv_answer)
        {
            arrayListWordNew.add(al_selected_words.get(position));
            al_selected_words.remove(position);
            setRandomListAdapter();
            setAnswerListAdapter();
        }
        else
        {
            al_selected_words.add(arrayListWordNew.get(position));
            arrayListWordNew.remove(position);
            setAnswerListAdapter();
            setRandomListAdapter();
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