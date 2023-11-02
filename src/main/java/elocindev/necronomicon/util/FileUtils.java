package elocindev.necronomicon.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class FileUtils {
    public static void setPrettyPrint(Path path) {
        try {
            String line;

            FileReader fileReader = new FileReader(path.toFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            
            fileReader.close();
            

            String jsonString = sb.toString();

            JsonElement jsonElement = JsonParser.parseString(jsonString);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJson = gson.toJson(jsonElement);

            FileWriter fileWriter = new FileWriter(path.toFile());
            fileWriter.write(prettyJson);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
