package net.voidfn.textedit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Util {
    private Util() {
    }

    public static String readFile(File file) throws FileNotFoundException, IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        }
    }

    public static void writeFile(File file, String content) {
        try (FileWriter out = new FileWriter(file)) {
            out.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
