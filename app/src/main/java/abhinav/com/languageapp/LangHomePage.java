package abhinav.com.languageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LangHomePage extends AppCompatActivity implements View.OnClickListener {
    Button btn_speech2Text,btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_speech2Text=(Button)findViewById(R.id.btn_speech2Text);
        btn_start=(Button)findViewById(R.id.btn_start);
        btn_speech2Text.setOnClickListener(this);
        btn_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.btn_speech2Text)
        {
            Intent intent=new Intent(this,LangSelectAct.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.btn_start)
        {
            Intent intent=new Intent(this,LangRandomeGenerator.class);
            startActivity(intent);
        }
    }
}
