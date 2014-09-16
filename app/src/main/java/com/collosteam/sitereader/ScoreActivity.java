package com.collosteam.sitereader;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

    @OnClick(R.id.button)
    void onButtonAddClick() {

        ContentValues cv = new ContentValues();
        cv.put(COL_NAME , name.getText().toString());
        cv.put(COL_DATE , System.currentTimeMillis());
        cv.put(COL_POINT , points.getText().toString());

        getContentResolver().insert(Uri.withAppendedPath(CONTENT_URI, TB_NAME_SCORE), cv);

        Log.d(TAG, "Add new score" + cv.toString());

    }

    CursorAdapter adapter;
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
          return new CursorLoader(this,Uri.withAppendedPath(CONTENT_URI, TB_NAME_SCORE),null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(adapter!=null){
            adapter.swapCursor(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if(adapter!=null){
            adapter.swapCursor(null);
        }
    }
}
