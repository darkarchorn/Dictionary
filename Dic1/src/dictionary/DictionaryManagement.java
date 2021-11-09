package dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class DictionaryManagement {

    HashMap<String, String> dictionary = new HashMap<>();
    ArrayList<String> words = new ArrayList<>(

    );

    String new_word = "";
    String new_explain = "";
    String new_pronounce = "";

    public DictionaryManagement() throws Exception {
        this.insertFromFile();
    }

    public void insertFromFile() throws Exception {
        //setProperty("file.encoding", "utf-8");
        int dem = 0;
        InputStream is = DictionaryManagement.class.getResourceAsStream("/dictionary.txt");
        InputStreamReader isr = new InputStreamReader(is, "utf8");
        File file = new File("C:/Users/darka/IdeaProjects/Dic1/src/resources/dictionary.txt");
        BufferedReader bf = new BufferedReader(isr);
        String ln = "";
        while (ln != null) {
            while (!ln.startsWith("@")) {
                ln = bf.readLine();
            }
            new_word = "";
            new_explain = "";
            if (ln.startsWith("@")) {
                new_word += ln.substring(1);
                while ((ln = bf.readLine()) != null && !ln.startsWith("@")) {
                    new_explain += ln + "\n";
                }
            }
            //System.out.println(new_word + '\n' + new_explain);

            words.add(new_word);
            if(dictionary.containsKey(new_word))
            {
                dictionary.replace(new_word, new_explain);
                continue;
            }
            dictionary.put(new_word, new_explain);
            dem++;
        }
        System.out.println(dem);
        bf.close();
        isr.close();
        is.close();
    }
}
