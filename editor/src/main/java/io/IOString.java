/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author UZIEL
 */
public class IOString
{
    File file;

    public IOString(File file)
    {
        this.file = file;
    }
    
    public void writeString(String text, boolean append) throws IOException
    {
        try(FileWriter fw = new FileWriter(file, append))
        {
            fw.write(text);
        }
    }
    
    public String readString() throws FileNotFoundException, IOException
    {
        if(!file.exists())
            return "";
        String text = "";
        int n;
        
        try(FileReader fr = new FileReader(file))
        {
            while((n = fr.read()) != -1)
                text += (char) n;
        }
        return text;
    }
}
