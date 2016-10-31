import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerProperties extends Properties {
    private static final String pathToServerProperties = "Server/res/server_strings.properties";
    public static Properties INSTANCE = getServerProperty();

    private static Properties getServerProperty() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(pathToServerProperties);
            prop.load(input);
            return prop;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}