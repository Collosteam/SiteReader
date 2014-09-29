package com.collosteam.sitereader;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.collosteam.sitereader.net.NetworkManager2;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements View.OnClickListener {


    private EditText editText;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.activity_my, container, false);

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

/*Hello Button*/
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(this);
/*Go Button*/
        Button button2 = (Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(this);

        /*Score Button*/
        Button button3 = (Button) view.findViewById(R.id.button3);
        button3.setOnClickListener(this);

                /*Check Button*/
        Button button4 = (Button) view.findViewById(R.id.button4);
        button4.setOnClickListener(this);

        final NetworkManager2 networkManager = new NetworkManager2();

        editText = (EditText) view.findViewById(R.id.editText);
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
        Button button5 = (Button) view.findViewById(R.id.button5);
        button5.setOnClickListener(this);


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

                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                intent.putExtra(SignUpActivity.EXTRAS_NAME, "Bill");

                startActivity(intent);

                break;


            case R.id.button3:
                msg = "Score!";
                Intent intent2 = new Intent(getActivity(), ScoreActivity.class);
                startActivity(intent2);
                break;
            case R.id.button4:

                break;

            case R.id.button5:

                Notification.Builder builder = new Notification.Builder(getActivity());

                builder.setContentText("I'm suspicious of this solution because the supplicant is only used if WPA (or some variation of WPA) is usesd: if user connects to an AP with no authentication or WEP then the supplicant is not involved. ");
                builder.setContentTitle("Test notification");
                builder.setSmallIcon(R.drawable.icon);

                Notification notify = builder.build();
                NotificationManager manager = (NotificationManager)
                        getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

                manager.notify(120, notify);


                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_and_rotate);

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



