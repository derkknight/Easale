package com.dy.easale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;
import com.dy.easale.Model.Artwork;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Derick Yung on 2/19/2015.
 */
public class FileHelper {
    private static String folderPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.easale/";
    private static String imageFolderPath = "artworkthumbs/";
    private static String artworkFileName = "artworks.txt";

    public static class ImageProvider
    {
        File folderDirectory = new File(folderPath + imageFolderPath);
        String imageName = new SimpleDateFormat("yyyyMMddhhmmssSS").format(new Date());
        File image = new File(folderPath + imageFolderPath + imageName);
        public String saveImage(Bitmap targetImage)
        {   try
            {
            folderDirectory.mkdirs();
                Log.d("THING", image.getAbsolutePath());
            image.createNewFile();
            FileOutputStream fos = null;

                fos = new FileOutputStream(image);
                targetImage.compress(Bitmap.CompressFormat.PNG, 80, fos);
                fos.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return image.getAbsolutePath();
        }
    }

    public static class TextProvider
    {
        File artworkText = new File(folderPath + artworkFileName);

        public void addArtwork(Artwork targetArtwork)
        {
            File folderDirectory = new File(folderPath);
            folderDirectory.mkdirs();

            if (!artworkText.exists())
            {
                try
                {
                    artworkText.createNewFile();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            try
            {
                BufferedWriter buff = new BufferedWriter(new FileWriter(artworkText, true));
                String artworkString = String.format("%s,%s,%s%n", targetArtwork.getTitle(), targetArtwork.getPrice(), targetArtwork.getIcon());
                buff.append(artworkString);
                buff.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        public void addArtwork(String title, String price, String imageURI)
        {
            File folderDirectory = new File(folderPath);
            folderDirectory.mkdirs();

            if (!artworkText.exists())
            {
                try
                {
                    artworkText.createNewFile();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            try
            {
                BufferedWriter buff = new BufferedWriter(new FileWriter(artworkText, true));
                String artworkString = String.format("%s,%s,%s%n", title, price, imageURI);
                buff.append(artworkString);
                buff.close();
                Log.d("HERPS", artworkText.getAbsolutePath());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        public ArrayList<Artwork> getAllArtwork()
        {
            ArrayList<Artwork> artworksArray = new ArrayList<Artwork>();
            BufferedReader buff = null;

            try
            {
                String currentLine;
                buff = new BufferedReader(new FileReader(artworkText));

                while((currentLine = buff.readLine()) != null)
                {
                    String[] artworkString = currentLine.split(",");
                    if (!(artworkString.length < 3))
                    {
                        Artwork artwork = new Artwork(artworkString[0], artworkString[1], artworkString[2]);
                        artworksArray.add(artwork);
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if (buff != null) buff.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
            return artworksArray;
        }
    }

    public static class DbProvider extends SQLiteOpenHelper
    {

        private static final String DATABASE_NAME = "artworksManager";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_ARTWORK = "artworks";
        private static final String TABLE_EVENT = "events";
        private static final String TABLE_ARTWORK_EVENT = "artwork_event";
        private static final String TABLE_SALE = "sales";
        private static final String TABLE_SALE_EVENT = "sale_event";

        //Common column names
        private static final String KEY_ID = "id";
        private static final String KEY_NAME = "name";

        //TABLE_ARTWORK columns
        private static final String KEY_PRICE = "price";
        private static final String KEY_URI = "imguri";

        //TABLE_EVENT columns
        private static final String KEY_DESCRIPTION = "description";

        //TABLE_ARTWORK_EVENT columns
        private static final String KEY_ARTWORK_ID = "artwork_id";
        private static final String KEY_EVENT_ID = "event_id";

        private static final String CREATE_TABLE_ARTWORK = "CREATE TABLE "
                + TABLE_ARTWORK + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT," + KEY_PRICE + " INTEGER," + KEY_URI +
                " TEXT" + ")";

        private static final String CREATE_TABLE_EVENT = "CREATE TABLE "
                + TABLE_EVENT + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT," + KEY_DESCRIPTION + " TEXT" + ")";

        private static final String CREATE_TABLE_ARTWORK_EVENT = "CREATE TABLE "
                + TABLE_ARTWORK_EVENT + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_ARTWORK_ID + " INTEGER," + KEY_EVENT_ID + " INTEGER" + ")";

        public DbProvider(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase database)
        {
            database.execSQL(CREATE_TABLE_ARTWORK);
            database.execSQL(CREATE_TABLE_EVENT);
            database.execSQL(CREATE_TABLE_ARTWORK_EVENT);
        }

        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
        {
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTWORK);
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTWORK_EVENT);
            onCreate(database);
        }

        //Create
        public long createArtwork(Artwork artwork)
        {
            SQLiteDatabase database = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, artwork.getTitle());
            values.put(KEY_PRICE, artwork.getPrice());
            values.put(KEY_URI, artwork.getIcon());

            long artwork_id = database.insert(TABLE_ARTWORK, null, values);

            return artwork_id;
        }

        //Read
        public Artwork getArtwork(long artwork_id)
        {
            SQLiteDatabase database = this.getReadableDatabase();

            String selectQuery = "SELECT * FROM " + TABLE_ARTWORK + " WHERE " + KEY_ID + " = " + artwork_id;

            Cursor c = database.rawQuery(selectQuery, null);

            if (c != null) c.moveToFirst();

            int id = c.getInt(c.getColumnIndex(KEY_ID));
            String title = c.getString(c.getColumnIndex(KEY_NAME));
            String price = c.getString(c.getColumnIndex(KEY_PRICE));
            String uri = c.getString(c.getColumnIndex(KEY_URI));

            return new Artwork(id, title, price, uri);
        }

        public ArrayList<Artwork> getArtworks()
        {
            ArrayList<Artwork> artworks = new ArrayList<Artwork>();
            String selectQuery = "SELECT * FROM " + TABLE_ARTWORK;

            SQLiteDatabase database = this.getReadableDatabase();
            Cursor c = database.rawQuery(selectQuery, null);

            if (c.moveToFirst())
            {
                while(!c.isAfterLast())
                {
                    int id = c.getInt(c.getColumnIndex(KEY_ID));
                    String title = c.getString(c.getColumnIndex(KEY_NAME));
                    String price = c.getString(c.getColumnIndex(KEY_PRICE));
                    String uri = c.getString(c.getColumnIndex(KEY_URI));
                    artworks.add(new Artwork(id, title, price, uri));
                    c.moveToNext();
                }
            }

            return artworks;
        }

        public ArrayList<Artwork> getArtworksByEvent()
        {
            ArrayList<Artwork> artworks =  new ArrayList<Artwork>();
            return artworks;
        }
    }
}
