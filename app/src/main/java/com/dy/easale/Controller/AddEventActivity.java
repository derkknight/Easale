package com.dy.easale.Controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.dy.easale.FileHelper;
import com.dy.easale.Model.Event;
import com.dy.easale.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class AddEventActivity extends Activity {
    String _imagePath;
    Bitmap _imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void selectImage(View view)
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent)
    {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode)
        {
            case 100:
                if (resultCode == RESULT_OK)
                {
                    try
                    {
                        Uri imageUri = imageReturnedIntent.getData();
                        InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        _imageBitmap = BitmapFactory.decodeStream(imageStream);
                        ImageButton imageButton = (ImageButton) findViewById(R.id.edit_event_image_button);
                        imageButton.setImageBitmap(_imageBitmap);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
        }
    }
    public void openAddEventArtActivity(long eventId)
    {
        Intent intent = new Intent(this, AddEventArtActivity.class);
        intent.putExtra("eventId", eventId);
        Log.d("data", eventId+"");
        this.startActivity(intent);
    }

    public void saveEvent(View view)
    {
        EditText editTitle = (EditText) findViewById(R.id.edit_event_title);
        EditText editDescription = (EditText) findViewById(R.id.edit_event_description);

        String title = editTitle.getText().toString();
        String description = editDescription.getText().toString();

        FileHelper.ImageProvider imageProvider = new FileHelper.ImageProvider();
        FileHelper.DbProvider dbProvider = new FileHelper.DbProvider(view.getContext());

        _imagePath = imageProvider.saveImage(_imageBitmap);

        long eventId = dbProvider.createEvent(new Event(title, description, _imagePath));

        String message = "'" + title + "' has been saved";
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

        finish();

        openAddEventArtActivity(eventId);

    }
}
