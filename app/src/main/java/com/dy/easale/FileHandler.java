package com.dy.easale;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Derick Yung on 9/22/2014.
 */
public class FileHandler {
    FileOutputStream fos;
    public FileHandler()
    {
    }

    public void writeFile(ArrayList<Artwork> aa, Context context)
    {
        BufferedWriter out = null;
        try {
            String path = context.getFilesDir().getAbsolutePath();

            FileOutputStream fos = context.openFileOutput("artworks.txt", Context.MODE_PRIVATE);
            for (int i = 0; i < aa.size(); i++)
            {
                Artwork artwork = aa.get(i);
                String to_write = artwork.getTitle() + "," + artwork.getPrice() +
                        "," + artwork.getIcon() + "\n";
                fos.write("12345".getBytes());
                fos.write(to_write.getBytes());
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Artwork> readFile()
    {
        ArrayList<Artwork> aa = new ArrayList<Artwork>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("artworks.txt"));
            String contentLine = reader.readLine();
            while (contentLine != null)
            {
                String[] artwork_info;
                artwork_info = parse_csv(contentLine);
                Artwork to_be_added = new Artwork(artwork_info[0], artwork_info[1], artwork_info[2]);
                aa.add(to_be_added);
                contentLine = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aa;
    }

    public String[] parse_csv(String line)
    {
        String[] artwork_info;
        artwork_info = line.split(",");
        return artwork_info;
    }
}
