package com.collosteam.sitereader;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.collosteam.sitereader.db.DBColumns;
import com.collosteam.sitereader.db.DBHelper;

public class MyActivity extends Activity implements View.OnClickListener, DBColumns{
    private String TAG = "{MyActivity}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
/*Hello Button*/
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
/*Go Button*/
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        //Score button
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);


        //DB



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
            case R.id.button:
                msg = "Hello!";
                break;

            case R.id.button2:
                msg = "Go!";

                Intent intent = new Intent(this, SignUpActivity.class);
                intent.putExtra(SignUpActivity.EXTRAS_NAME,"Bill");

                startActivity(intent);

                break;
            case R.id.button3:
                msg="Score";
                Intent intent2=new Intent(this, ScoreActivity.class);
                startActivity(intent2);
                break;

            default:
                msg = "Ups!";
                break;
        }
        Toast.makeText(MyActivity.this, msg, Toast.LENGTH_SHORT).show();



    }
}

