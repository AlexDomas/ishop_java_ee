package main.java.by.bntu.poisit.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.Part;

public class FileUtils {

    public static void downloadImage(Part part, String imagePath) {

        String fileName = part.getSubmittedFileName();
        String requestPath = imagePath + "" + fileName;

        OutputStream out = null;
        InputStream fileContent = null;

        try {
            out = new FileOutputStream(new File(requestPath));
            fileContent = part.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            out.close();
            fileContent.close();
        } catch (FileNotFoundException fne) {
        } catch (IOException ex) {
        }
    }

}
