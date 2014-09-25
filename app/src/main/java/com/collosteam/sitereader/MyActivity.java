package com.collosteam.sitereader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.collosteam.sitereader.db.DBColumns;
import com.collosteam.sitereader.net.NetworkManager2;

public class MyActivity extends Activity implements View.OnClickListener, DBColumns {
    EditText editText;
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

        /*Score Button*/
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

                /*Check Button*/
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);

        final NetworkManager2 networkManager = new NetworkManager2();

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
                                if (!checkName) {
                                    editText.setError("Имя занято!");
                                } else {
                                    editText.setError(null);
                                }
                            }
                        });

                    }
                }).start();


            }
        });


                 /*Check Button*/
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);


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

    @SuppressLint("NewApi")
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
                intent.putExtra(SignUpActivity.EXTRAS_NAME, "Bill");

                startActivity(intent);

                break;


            case R.id.button3:
                msg = "Score!";
                Intent intent2 = new Intent(this, ScoreActivity.class);
                startActivity(intent2);
                break;
            case R.id.button4:

                break;

            case R.id.button5:

                Notification.Builder builder = new Notification.Builder(this);

                builder.setContentText("I'm suspicious of this solution because the supplicant is only used if WPA (or some variation of WPA) is usesd: if user connects to an AP with no authentication or WEP then the supplicant is not involved. ");
                builder.setContentTitle("Test notification");
                builder.setSmallIcon(R.drawable.icon);

                Notification notify = builder.build();
                NotificationManager manager = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);

                manager.notify(120, notify);


                Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_and_rotate);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                v.startAnimation(animation);


                break;


            default:
                msg = "Ups!";
                break;
        }
        // Toast.makeText(MyActivity.this, msg, Toast.LENGTH_SHORT).show();


    }
}

