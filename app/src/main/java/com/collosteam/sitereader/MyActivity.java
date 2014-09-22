package com.collosteam.sitereader;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.collosteam.sitereader.db.DBColumns;
import com.collosteam.sitereader.db.DBHelper;
import com.collosteam.sitereader.net.NetworkManager;
import com.collosteam.sitereader.net.NetworkManager2;

public class MyActivity extends Activity implements View.OnClickListener, DBColumns{
    private String TAG = "{MyActivity}";

    EditText editText;
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

        // check button
        Button button4 = (Button) findViewById(R.id.btn4);
        button4.setOnClickListener(this);

        final NetworkManager2 networkManager= new NetworkManager2();
        // or this
        // final NetworkManager networkManager= new NetworkManager(this);

        // edit text
        editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final boolean checkName = networkManager.checkName(s.toString());

                        editText.post(new Runnable() {
                            @Override
                            public void run() {
                                if (!checkName){
                                    editText.setError("имя занято");
                                } else {
                                    editText.setError(null);
                                }
                            }
                        });

                    }
                }).start();




            }
        });

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

            case R.id.btn4:

                break;

            default:
                msg = "Ups!";
                break;
        }
        Toast.makeText(MyActivity.this, msg, Toast.LENGTH_SHORT).show();



    }
}

