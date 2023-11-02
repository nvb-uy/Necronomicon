package elocindev.necronomicon.api.json;

import java.nio.file.Path;

import elocindev.necronomicon.util.FileUtils;

public class JsonFileAPI {
    /**
     * Reads the JSON file, parses it then writes a pretty-printed version of it back to the file.
     * 
     * @param path
     * @since 1.2.0
     * 
     * @author ElocinDev
     */
    public static void setPrettyPrint(Path path) {
        FileUtils.setPrettyPrint(path);
    }
}
