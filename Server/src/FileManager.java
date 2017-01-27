import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private final static String path = "Server/buckedFiles";

    public static List<String> getFilesAsList() {
        List<String> results = new ArrayList<>();
        File[] files = new File(path).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return results;
    }

    public static File getFile(String fileName) {
        return new File(path + "/" + fileName);
    }
}
