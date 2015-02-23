package com.dy.easale;

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

        public void addArtwork(String title, String price, String description, String imageURI)
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
                String artworkString = String.format("%s,%s,%s,%s%n", title, price, description, imageURI);
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
                    if (!(artworkString.length < 4))
                    {
                        Artwork artwork = new Artwork(artworkString[0], artworkString[1], artworkString[2], artworkString[3]);
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
}
