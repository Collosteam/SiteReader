package com.collosteam.sitereader;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.collosteam.sitereader.adapter.ScoreAdapter;
import com.collosteam.sitereader.db.DBColumns;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.collosteam.sitereader.db.MyContentProvider.CONTENT_URI;
import static com.collosteam.sitereader.db.MyContentProvider.TAG;


public class ScoreActivity extends Activity implements DBColumns, LoaderManager.LoaderCallbacks<Cursor> {

    @InjectView(R.id.editText)
    EditText name;

    @InjectView(R.id.editText2)
    EditText points;

    @InjectView(R.id.tvName)
    TextView time;

    @InjectView(R.id.button)
    Button addButton;

    @InjectView(R.id.listView)
    ListView listView;
    CursorAdapter adapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1) {

                case 120:
                    name.setText(msg.obj.toString());
                    break;
                default:
                    points.setText("Error");
                    break;
            }
        }
    };

    @OnClick(R.id.button)
    void onButtonAddClick() {

        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name.getText().toString());
        cv.put(COL_DATE, System.currentTimeMillis());
        cv.put(COL_POINT, points.getText().toString());
        getContentResolver().insert(Uri.withAppendedPath(CONTENT_URI, TB_NAME_SCORE), cv);
        Log.d(TAG, "Add new score" + cv.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        ButterKnife.inject(this);

        getLoaderManager().initLoader(100001, null, this);

        adapter = new ScoreAdapter(this,
                getContentResolver().query(Uri.withAppendedPath(CONTENT_URI, TB_NAME_SCORE)
                        , null
                        , null
                        , null
                        , null));

        listView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        MyTask task = new MyTask();
        task.execute(100);

        MyTask task2 = new MyTask();
        task2.execute(200);

        task.cancel(true);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ScoreActivity.this, "postDelayed", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 3000L);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.score, menu);
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
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, Uri.withAppendedPath(CONTENT_URI, TB_NAME_SCORE), null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (adapter != null) {
            adapter.swapCursor(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (adapter != null) {
            adapter.swapCursor(null);
        }
    }


    private class MyTask extends AsyncTask<Integer, Character, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d(TAG, "onPreExecute");

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            name.setText(s);
        }

        @Override
        protected void onProgressUpdate(Character... values) {
            super.onProgressUpdate(values);

            points.setText("" + values[0].hashCode());

        }

        @Override
        protected String doInBackground(Integer... params) {

            String string = null;
            for (int i = 0; i < 10; i++) {
                Character c = (char) (Math.random() * Character.MAX_VALUE);
                string += c.toString();

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (isCancelled()) {
                    break;
                }

//                points.setText("" + c);
                publishProgress(c);
            }

            return string;
        }
    }


}
