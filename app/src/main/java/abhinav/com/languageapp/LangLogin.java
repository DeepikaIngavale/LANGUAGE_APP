package abhinav.com.languageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LangLogin extends AppCompatActivity implements View.OnClickListener
{
    CheckBox chkbx_show;
    EditText edtxt_passwrd,edtxt_email;
    String email,password;
    Button btn_save,btn_login;
    DataBaseHelperLogin db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_login);

        edtxt_passwrd=(EditText)findViewById(R.id.edtxt_passwrd);
        edtxt_email=(EditText)findViewById(R.id.edtxt_email);
        btn_save=(Button)findViewById(R.id.btn_save);
        btn_login=(Button)findViewById(R.id.btn_login);

        db = new DataBaseHelperLogin(LangLogin.this);

        edtxt_email.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        db.getAllLoginDetails();
        chkbx_show=(CheckBox)findViewById(R.id.chkbx_show);
        chkbx_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean value)
            {
                if(value)
                {
                    edtxt_passwrd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    edtxt_passwrd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.btn_save)
        {
            email = edtxt_email.getText().toString().trim();
            boolean valid = isEmailAddressValid(email);
            if(valid)
            {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/html");
                Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
            }
            else
            {
                edtxt_email.setError("Invalid email address");
            }
            password = edtxt_passwrd.getText().toString().trim();
            long insert_id = db.insertLoginDetails(email, password);
            if(insert_id>0)
            {
                Toast.makeText(this, "insert_success", Toast.LENGTH_SHORT).show();
            }
        }
        if(view.getId()==R.id.btn_login)
        {
            Intent intent=new Intent(this,LangSelectAct.class);
            startActivity(intent);
        }
    }
    public static boolean isEmailAddressValid(String email)
    {
        boolean isEmailValid = false;

        String strExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern objPattern = Pattern.compile(strExpression, Pattern.CASE_INSENSITIVE);
        Matcher objMatcher = objPattern.matcher(inputStr);
        if (objMatcher.matches())
        {
            isEmailValid = true;
        }
        return isEmailValid;
    }

}
