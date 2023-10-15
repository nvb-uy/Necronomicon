package elocindev.necronomicon;

import elocindev.necronomicon.config.NecConfig;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;

import java.util.List;

/**
 * This is an example config class. Must be initialized using {@link NecConfigAPI#registerConfig(Class)}.
 * 
 * @apiNote     The instance of this class needs the {@link NecConfig} annotation.
 * @apiNote     All fields from this class will be registered as config entries.
 * 
 * @see         NecConfigAPI
 * @platform    Both
 * 
 * @since       1.0.5
 * @author      ElocinDev
 */
public class NecronomiconConfig {
    /**
     * The instance of this class.
     * 
     * Must be implemented with the annotation or the config will not load properly.
     */
    @NecConfig
    public static NecronomiconConfig INSTANCE;

    /**
     * The main getter of the config class.
     * 
     * @apiNote Must not have any parameters and must return a String using {@link elocindev.necronomicon.api.config.v1.NecConfigAPI#getFile(String) NecConfigAPI#getFile}.
     * 
     * @return  String : The instance of this class.
     */
    public static String getFile() {
        return NecConfigAPI.getFile("necronomicon.json");
    }

    /**
     * The entries.
     * All fields from this class will be registered as config entries, they must be public and serializable.
     * Their values will be saved as default in the file, but can be changed later by the user.
    **/

    public boolean debug = false;
    public boolean test = false;
    public List<String> list = List.of("test", "test2");
}