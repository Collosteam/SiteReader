package com.collosteam.sitereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends Activity implements View.OnClickListener {
    private String TAG = "{MyActivity}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
/*Login screen Button*/
        Button button1 = (Button) findViewById(R.id.etLoginScreen);
        button1.setOnClickListener(this);
/*registration screen Button*/
        Button button2 = (Button) findViewById(R.id.etRegistrationScreen);
        button2.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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

    @Override
    public void onClick(View v) {
        String msg = new String();

        int id = v.getId();

        switch (id) {
            case R.id.etLoginScreen:
                msg = "Wazzzzzup!!!!";

                Intent intent1 = new Intent(this, LoginScreenActivity.class);
                startActivity(intent1);
                //intent1.putExtra(SignUpActivity.EXTRAS_NAME,"Bill");
                break;

            case R.id.etRegistrationScreen:
                msg = "Go to registration screen";

                Intent intent2 = new Intent(this, SignUpActivity.class);
                //intent2.putExtra(SignUpActivity.EXTRAS_NAME, msg);

                startActivity(intent2);

                break;

            default:
                msg = "Ups!";
                break;
        }
        Toast.makeText(MyActivity.this, msg, Toast.LENGTH_SHORT).show();



    }
}

