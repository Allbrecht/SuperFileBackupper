import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClientProperties {
    private static final String pathToClientProperties = "Client/res/client_strings.properties";
    public static Properties INSTANCE = getClientProperty();

    private static Properties getClientProperty() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(pathToClientProperties);
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
