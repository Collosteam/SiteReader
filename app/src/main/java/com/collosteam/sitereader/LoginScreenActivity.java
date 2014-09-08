package com.collosteam.sitereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.collosteam.sitereader.data.SempleUser;
import com.collosteam.sitereader.data.User;

import static android.view.View.VISIBLE;


public class LoginScreenActivity extends Activity {


    EditText name1;
    EditText pass1;
    Button login1;
    Button signup1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        name1=(EditText)findViewById(R.id.etName1);
        pass1=(EditText)findViewById(R.id.etPass1);


        login1 = (Button) findViewById(R.id.etLogin1);
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name1.getText().length()==0){
                    name1.setError("Name can't be empty");
                    return;
                }
                if (pass1.getText().length()==0){
                    pass1.setError("Pass can't be empty");
                    return;
                }


                User user2=new SempleUser(
                        name1.getText().toString(),
                        pass1.getText().toString()
                );


                if (MyApp.userMap.containsKey(user2.hashCode())){
                    Intent intent2 = new Intent(LoginScreenActivity.this, MyActivity.class);
                    startActivity(intent2);

                    return;
                } else {
                    Toast.makeText(LoginScreenActivity.this, "Incorrect name or pass", Toast.LENGTH_SHORT).show();
                    return;
                }


            }


        });

        signup1 = (Button)findViewById(R.id.etSignup1);
        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent3 = new Intent(LoginScreenActivity.this, SignUpActivity.class);
                startActivity(intent3);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
