package com.collosteam.sitereader.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider implements DBColumns {

    // Uri authority
    static final String AUTHORITY = "com.dewacademy.sitereader";

    // path
    static final String PATH = TB_NAME_USERS;

    // Общий Uri
    public static final Uri CONTENT_URI = Uri.parse("content://"
            + AUTHORITY);

    // Конкретный Uri для пользователей
    public static final Uri CONTENT_URI_USERS = Uri.withAppendedPath(CONTENT_URI, TB_NAME_USERS);

    // Конкретный Uri для очков
    public static final Uri CONTENT_URI_SCORE = Uri.withAppendedPath(CONTENT_URI, TB_NAME_SCORE);

    // Типы данных, набор строк
    static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + PATH;
    // Одна строка
    static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + PATH;

    // UriMatcher, общий Uri
    static final int URI_ALL_USERS= 1;
    // Uri с указанным ID
    static final int URI_USER_BY_ID = 2;



    // UriMatcher, общий Uri
    static final int URI_ALL_SCORE= 11;
    // Uri с указанным ID
    static final int URI_SCORE_BY_ID = 12;


    // описание и создание UriMatcher
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, TB_NAME_USERS, URI_ALL_USERS);
        uriMatcher.addURI(AUTHORITY, TB_NAME_USERS + "/#", URI_USER_BY_ID);

        uriMatcher.addURI(AUTHORITY, TB_NAME_SCORE, URI_ALL_SCORE);
        uriMatcher.addURI(AUTHORITY, TB_NAME_SCORE + "/#", URI_SCORE_BY_ID);
    }

    public static String TAG = "{MyContentProvider}";
    SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG, "insert - " + uri.toString());

        String tbName;

        switch (uriMatcher.match(uri)){
            case URI_ALL_USERS:
                tbName = TB_NAME_USERS;
                break;

            case URI_ALL_SCORE:
                tbName = TB_NAME_SCORE;
                break;

            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        //Открываем соеденение с БД
        db = dbHelper.getWritableDatabase();

        long rowID = -1;

        db.beginTransaction();
        try {
            rowID = db.insert(tbName, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e){
            Log.d(TAG, "insert exception - " + uri.toString());
        }finally {
            db.endTransaction();
        }

        Uri resultUri = ContentUris.withAppendedId(Uri.withAppendedPath(CONTENT_URI,tbName), rowID);
        // уведомляем ContentResolver, что данные по адресу resultUri изменились
        getContext().getContentResolver().notifyChange(Uri.withAppendedPath(CONTENT_URI,tbName), null);

        return resultUri;
    }


    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query - " + uri.toString());
        // проверяем Uri
        String tbName;
        switch (uriMatcher.match(uri)){
            case URI_ALL_USERS :
                tbName = TB_NAME_USERS;
                break;
            case URI_USER_BY_ID :
                tbName = TB_NAME_USERS;
                break;

            case URI_ALL_SCORE:
                tbName = TB_NAME_SCORE;
                break;

            case URI_SCORE_BY_ID:
                tbName = TB_NAME_SCORE;
                break;

            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(tbName, projection, selection, selectionArgs, null, null, sortOrder);
        // просим ContentResolver уведомлять этот курсор об изменениях данных в GEO_URI
        cursor.setNotificationUri(getContext().getContentResolver(), Uri.withAppendedPath(CONTENT_URI,tbName));
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
