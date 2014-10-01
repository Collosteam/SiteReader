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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.collosteam.sitereader.net.NetworkManager2;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements View.OnClickListener {


    private EditText editText;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Gets a string error reason from an error code.
     */
    private static String getErrorReason(int errorCode) {
        String errorReason = "";
        switch (errorCode) {
            case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                errorReason = "Internal error";
                break;
            case AdRequest.ERROR_CODE_INVALID_REQUEST:
                errorReason = "Invalid request";
                break;
            case AdRequest.ERROR_CODE_NETWORK_ERROR:
                errorReason = "Network Error";
                break;
            case AdRequest.ERROR_CODE_NO_FILL:
                errorReason = "No fill";
                break;
        }
        return errorReason;
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
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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


        final AdView adView = (AdView) view.findViewById(R.id.adView);

        // Set the AdListener.
        adView.setAdListener(new AdListener() {
            public static final String LOG_TAG = "{AdListener}";

            /**
             * Called when an ad is clicked and about to return to the application.
             */
            @Override
            public void onAdClosed() {
                Log.d(LOG_TAG, "onAdClosed");
//                    Toast.makeText(BannerXMLSample.this, "onAdClosed", Toast.LENGTH_SHORT).show();
            }

            /**
             * Called when an ad failed to load.
             */
            @Override
            public void onAdFailedToLoad(int error) {
                String message = "onAdFailedToLoad: " + getErrorReason(error);
                Log.d(LOG_TAG, message);
//                    Toast.makeText(BannerXMLSample.this, message, Toast.LENGTH_SHORT).show();

                //   adView.setVisibility(View.GONE);

            }

            /**
             * Called when an ad is clicked and going to start a new Activity that will
             * leave the application (e.g. breaking out to the Browser or Maps
             * application).
             */
            @Override
            public void onAdLeftApplication() {
                Log.d(LOG_TAG, "onAdLeftApplication");
//                    Toast.makeText(BannerXMLSample.this, "onAdLeftApplication", Toast.LENGTH_SHORT).show();
            }

            /**
             * Called when an Activity is created in front of the app (e.g. an
             * interstitial is shown, or an ad is clicked and launches a new Activity).
             */
            @Override
            public void onAdOpened() {
                Log.d(LOG_TAG, "onAdOpened");
//                    Toast.makeText(BannerXMLSample.this, "onAdOpened", Toast.LENGTH_SHORT).show();
            }

            /**
             * Called when an ad is loaded.
             */
            @Override
            public void onAdLoaded() {
                Log.d(LOG_TAG, "onAdLoaded");
//                    Toast.makeText(BannerXMLSample.this, "onAdLoaded", Toast.LENGTH_SHORT).show();
                // adView.setVisibility(View.VISIBLE);
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);


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



