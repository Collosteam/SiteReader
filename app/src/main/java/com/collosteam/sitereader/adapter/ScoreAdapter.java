package com.collosteam.sitereader.adapter;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.collosteam.sitereader.R;
import com.collosteam.sitereader.db.DBColumns;

import java.awt.font.TextAttribute;

/**
 * Created by Admin on 9/15/2014.
 */
public class ScoreAdapter extends CursorAdapter implements DBColumns {

    TextView position;
    TextView name;
    TextView points;
    TextView date;

    int IDposition;
    int IDname;
    int IDpoints;
    int IDdate;



    public ScoreAdapter(Context context, Cursor c) {
        super(context, c, FLAG_REGISTER_CONTENT_OBSERVER);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.item_score, parent,false);

        IDdate = cursor.getColumnIndex(COL_DATE);
        IDname = cursor.getColumnIndex(COL_NAME);
        IDpoints = cursor.getColumnIndex(COL_POINT);

        position = (TextView) rootView.findViewById(R.id.tvPosition);
        name = (TextView) rootView.findViewById(R.id.tvName);
        points = (TextView) rootView.findViewById(R.id.tvPoint);
        date = (TextView) rootView.findViewById(R.id.tvDate);

        return rootView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        position.setText("1");
        name.setText(cursor.getString(IDname));
        points.setText(cursor.getString(IDpoints));
        date.setText(cursor.getString(IDdate));


    }
}
