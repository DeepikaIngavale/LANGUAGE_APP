package abhinav.com.languageapp;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Toast;

public class CheckingSentence
{
    boolean flag=true;
    String Word[],Words[];
    int icnt=0;

    public boolean Check(String sentence1, String sentence2)
    {
        Words = sentence1.split(" ");

        Word = sentence2.split(" ");


        if (!sentence2.equals("")) {
            for (int i = 0; i < Word.length; i++) {
                for (int j = 0; j < Words.length; j++) {
                    if (Word[i].equals(Words[j])) {
                        icnt++;
                    }
                }
            }


                    /*String Sentence = arrayList.get(temp).getSentence();
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
        return flag;
    }
}
