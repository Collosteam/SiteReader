package com.collosteam.sitereader;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.collosteam.sitereader.data.SempleUser;
import com.collosteam.sitereader.data.User;
import com.collosteam.sitereader.data.UsersWraper;
import com.collosteam.sitereader.db.DBColumns;
import com.collosteam.sitereader.db.MyContentProvider;

import java.util.List;


public class SignUpActivity extends Activity implements DBColumns {

    public static final String EXTRAS_NAME = "name";
    private String TAG = "{SignUpActivity}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Intent intent = getIntent();

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etPass = (EditText) findViewById(R.id.etPass);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        if (intent.hasExtra(EXTRAS_NAME)) {
            etName.setText(intent.getStringExtra(EXTRAS_NAME));
        }

        Button buttonNext = (Button) findViewById(R.id.button);
        buttonNext.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (TextUtils.isEmpty(etName.getText())) {
                            etName.setError(getString(R.string.error_text_empty_name));
                            return;
                        }

                        if (etPass.getText().length() == 0) {
                            etPass.setError("Пароль не может быть пустым");
                            return;
                        }

                        if (etEmail.getText().length() == 0) {
                            etEmail.setError("Почта не может быть пустой");
                            return;
                        } else if (!etEmail.getText().toString().contains("@")) {
                            etEmail.setError("Неправильная почта");
                            return;
                        }
                        User user1 = new SempleUser(
                                etName.getText().toString(),
                                etPass.getText().toString(),
                                etEmail.getText().toString());
                        /*if (MyApp.userMap.containsKey(user1.hashCode())) {
                          etName.setError("Этот пользователь чем то занят!");
                          return; }
                          MyApp.userMap.put(user1.hashCode(), user1);
                          saveUserToSP(user1);
                          User savedUser = getCurrentUser();*/

                        /*DATABASE BEGIN*/
                        /*DBHelper helper = new DBHelper(SignUpActivity.this);
                        SQLiteDatabase database = helper.getReadableDatabase();*/

                        Cursor cursor =  getContentResolver().query(MyContentProvider.CONTENT_URI_USERS, null, null, null, null, null);

                        List<User> users = new UsersWraper(cursor).getData();

                        Log.d(TAG,"All users - " + users);

                        ContentValues contentValues = new ContentValues();
                        contentValues.put(COL_NAME, user1.getName());
                        contentValues.put(COL_EMAIL, user1.getEmail());
                        contentValues.put(COL_PASS, user1.getPass());

                        // writableDatabase.insert(TB_NAME_USERS, null, contentValues);

                        getContentResolver().insert(MyContentProvider.CONTENT_URI_USERS, contentValues);

                        /*DATABASE END*/

                        Toast.makeText(SignUpActivity.this, getString(R.string.msg_user_add, user1)
                                ,
                                Toast.LENGTH_SHORT).show();

                        finish();
                    }
                }
                
        );
    }

    public static final String PREF_KEY_NAME = "p.name";
    public static final String PREF_KEY_PASSW = "p.pass";
    public static final String PREF_KEY_EMAIL = "p.mail";


    private void saveUserToSP(User user) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.putString(PREF_KEY_NAME, user.getName());
        edit.putString(PREF_KEY_PASSW, user.getPass());
        edit.putString(PREF_KEY_EMAIL, user.getEmail());

        edit.commit();
    }


    public User getCurrentUser() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String name = sharedPreferences.getString(PREF_KEY_NAME, "default");
        String pass = sharedPreferences.getString(PREF_KEY_PASSW, "default");
        String email = sharedPreferences.getString(PREF_KEY_EMAIL, "default@");

        User user = new SempleUser(name, pass, email);

        return user;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_up, menu);
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
