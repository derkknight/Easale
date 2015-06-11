package com.dy.easale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import com.dy.easale.Model.Artwork;
import com.dy.easale.Model.Event;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

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
        private static final int DATABASE_VERSION = 5;

        private static final String TABLE_ARTWORK = "artworks";
        private static final String TABLE_EVENT = "events";
        private static final String TABLE_ARTWORK_EVENT = "artwork_event";
        private static final String TABLE_SALE = "sales";

        //Common column names
        private static final String KEY_ID = "id";
        private static final String KEY_NAME = "name";
        private static final String KEY_URI = "imguri";
        private static final String CREATED_AT = "created";

        //TABLE_ARTWORK columns
        private static final String KEY_PRICE = "price";

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
                KEY_NAME + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_URI + " TEXT" + ")";

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

            long artworkId = database.insert(TABLE_ARTWORK, null, values);
            return artworkId;
        }

        public long createEvent(Event event)
        {
            SQLiteDatabase database = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, event.getTitle());
            values.put(KEY_DESCRIPTION, event.getDescription());
            values.put(KEY_URI, event.getIcon());

            long eventId = database.insert(TABLE_EVENT, null, values);
            return eventId;
        }

        public long createArtworkEvent(Artwork artwork, Event event)
        {
            SQLiteDatabase database = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_ARTWORK_ID, artwork.getId());
            values.put(KEY_EVENT_ID, event.getId());

            long artworkEventId = database.insert(TABLE_ARTWORK_EVENT, null, values);

            return artworkEventId;
        }

        public long createArtworkEvent(ArrayList<Artwork> artworkList, long eventId)
        {
            SQLiteDatabase database = this.getWritableDatabase();

            /**
            String args = " SELECT " + artworkList.get(0).getId() + " AS " + KEY_ARTWORK_ID + ", " +
                    event.getId() + " AS " + KEY_EVENT_ID;



            if (artworkList.size() > 1)
            {
                for (int i = 1; i < artworkList.size(); i++)
                {
                    args += "UNION SELECT " + artworkList.get(i).getId() + ", " + event.getId() + " ";
                }
            }
             **/

            String args = "(" + KEY_ARTWORK_ID + "," + KEY_EVENT_ID + ") VALUES ";

            for (int i = 0; i < artworkList.size(); i++)
            {
                args += "(" + artworkList.get(i).getId() + "," + eventId + ")";
                if ((i + 1) == artworkList.size())
                {
                    args += ";";
                }
                else
                {
                    args += ", ";
                }
            }

            String query = "INSERT INTO " + TABLE_ARTWORK_EVENT + " " + args;

            Log.d("data", query);

            database.execSQL(query);
            long artworkEventId = 0;
            return artworkEventId;
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

        public ArrayList<Event> getEvents()
        {
            ArrayList<Event> events = new ArrayList<Event>();
            String selectQuery = "SELECT * FROM " + TABLE_EVENT;

            SQLiteDatabase database = this.getReadableDatabase();
            Cursor c = database.rawQuery(selectQuery, null);

            if (c.moveToFirst())
            {
                while(!c.isAfterLast())
                {
                    int id = c.getInt(c.getColumnIndex(KEY_ID));
                    String title = c.getString(c.getColumnIndex(KEY_NAME));
                    String description = c.getString(c.getColumnIndex(KEY_DESCRIPTION));
                    String uri = c.getString(c.getColumnIndex(KEY_URI));
                    events.add(new Event(id, title, description, uri));
                    c.moveToNext();
                }
            }
            return events;
        }

        public ArrayList<Artwork> GetArtworkEvent(int eventId)
        {
            ArrayList<Artwork> artworks = new ArrayList<Artwork>();
            String selectQuery = "SELECT " + TABLE_ARTWORK + ".* FROM " + TABLE_EVENT + " INNER JOIN " + TABLE_ARTWORK_EVENT + " ON "
                    + TABLE_ARTWORK_EVENT + "." + KEY_EVENT_ID + " = " + TABLE_EVENT +"." + KEY_ID + " INNER JOIN " + TABLE_ARTWORK +
                    " ON " + TABLE_ARTWORK_EVENT +"." + KEY_ARTWORK_ID +"=" +TABLE_ARTWORK +"."+KEY_ID +
                    " WHERE " + TABLE_EVENT + "." + KEY_ID + "= ?"  +";";

            Log.d("DATABASEE", selectQuery + eventId);

            SQLiteDatabase database = this.getReadableDatabase();
            Cursor c = database.rawQuery(selectQuery, new String[]{Integer.toString(eventId)});

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

        //Update
        //Delete
        public void deleteArtwork(int artworkId)
        {
            String selectQuery = "DELETE FROM "  + TABLE_ARTWORK + " WHERE " + KEY_ID + " = " + Integer.toString(artworkId);

            SQLiteDatabase database = this.getWritableDatabase();
            database.execSQL(selectQuery);

        }

        public void deleteArtworks(String[] artworkIds)
        {
            String args = TextUtils.join(", ", artworkIds);
            String selectQuery = "DELETE FROM " + TABLE_ARTWORK + " WHERE " + KEY_ID + " IN (" + args + ")";

            SQLiteDatabase database = this.getWritableDatabase();
            Cursor c = database.rawQuery(selectQuery, null);
        }

        public void DeleteEvent(int eventId)
        {
            String selectQuery = "DELETE FROM " + TABLE_EVENT + " WHERE " + KEY_ID + " = " + Integer.toString(eventId);

            SQLiteDatabase database = this.getWritableDatabase();
            database.execSQL(selectQuery);
        }

        public ArrayList<Artwork> getArtworksByEvent()
        {
            ArrayList<Artwork> artworks =  new ArrayList<Artwork>();
            return artworks;
        }
    }
}
