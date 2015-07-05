package com.dy.easale.Controller.AddActivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.dy.easale.FileHelper;
import com.dy.easale.Model.Artwork;
import com.dy.easale.R;

import java.io.IOException;
import java.io.InputStream;


public class AddArtworkActivity extends Activity {
    String imagePath;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artwork);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_artwork, menu);
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
                        imageBitmap = BitmapFactory.decodeStream(imageStream);
                        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
                        imageButton.setImageBitmap(imageBitmap);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
        }
    }

    public void saveArtwork(View view)
    {
        EditText edit_title = (EditText) findViewById(R.id.edit_title);
        EditText edit_price = (EditText) findViewById(R.id.edit_price);

        String title = edit_title.getText().toString();
        String price = edit_price.getText().toString();

        if (title.length() == 0)
        {
            Toast.makeText(view.getContext(),"Please include a title", Toast.LENGTH_SHORT).show();
            return;
        }

        if (price.length() == 0)
        {
            Toast.makeText(view.getContext(),"Please include the price", Toast.LENGTH_SHORT).show();
            return;
        }

        FileHelper.ImageProvider imageProvider = new FileHelper.ImageProvider();
        FileHelper.DbProvider dbProvider = new FileHelper.DbProvider(view.getContext());

        imagePath = imageProvider.saveImage(imageBitmap);
        dbProvider.createArtwork(new Artwork(title, Integer.parseInt(price), imagePath, 0, 0));


        String message = "'" + title + "' has been saved";
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

        finish();
    }
}
