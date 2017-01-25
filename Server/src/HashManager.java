import java.io.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashManager {

    private Set<Integer> hashes;
    private static final String path = "Server/res/hashes.txt";

    public HashManager() {
        hashes = new HashSet<>();
        readFile();
    }

    public void saveHashes() {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(path));
            Iterator it = hashes.iterator();
            while (it.hasNext()) {
                out.write(it.next() + "\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                hashes.add(Integer.parseInt(line));
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void addHash(int hash) {
        hashes.add(hash);
    }

    public boolean isNewHash(int hash) {
        return hashes.contains(hash) ? false : true;
    }
}
