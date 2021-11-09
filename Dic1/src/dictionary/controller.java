package dictionary;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class controller implements Initializable {
    File f = new File("C:\\Users\\darka\\IdeaProjects\\Dic1\\src\\resources\\dictionary.txt");
    DictionaryManagement dic = new DictionaryManagement();
    HashMap<String, String> controllerHashmap = dic.dictionary;
    ArrayList<String> words = dic.words;
    ArrayList<String> searched = new ArrayList<>();
    ArrayList<String> deleted = new ArrayList<>();
    String s = "";
    @FXML
    private TextField myTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Label result;
    @FXML
    private Button displayAllButton;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button delete;
    @FXML
    private TextField meaning;

    public controller() throws Exception {
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().startsWith(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    public void dictionaryLookup(javafx.event.ActionEvent actionEvent) {
        s = myTextField.getText();
        listView.getItems().clear();
        listView.getItems().addAll(searchList(myTextField.getText(), words));
    }

    public void displayAll(javafx.event.ActionEvent actionEvent) throws FileNotFoundException {
        File file = new File("C:/Users/darka/IdeaProjects/Dic1/src/resources/dictionary.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
        result.setText("All the words have been displayed on your console!");
    }

    public void deleteAWord(javafx.event.ActionEvent actionEvent) {
        if (searched.size() != 0) {
            controllerHashmap.put(searched.get(searched.size() - 1), "Word deleted!");
            result.setText(s + "\n\n" + "Word deleted!");
            try {
                write("\n@" + s + "\n" + "Word deleted!" + "\n", f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addAWord(javafx.event.ActionEvent actionEvent) {
        words.add(myTextField.getText() + "  ");
        if (meaning.getText().equals("")) {
            controllerHashmap.put(myTextField.getText() + "  ", "* " + "Meaning undetermined!");
        } else {
            controllerHashmap.put(myTextField.getText() + "  ", "* " + meaning.getText());
        }
        result.setText(myTextField.getText() + "\n\n" + "Word added!");
    }

    public void about(javafx.event.ActionEvent actionEvent) {
        result.setText("\n\n\n\n\n\nThis dictionary was created by Phan Duy Thang!\n\nhttps://github.com/darkarchorn/Dictionary");
    }

    public void write(String s, File f) throws IOException {
        FileWriter fw = new FileWriter(f, true);
        fw.write(s);
        fw.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                s = listView.getSelectionModel().getSelectedItem();
                searched.add(s);
                result.setText(s + "\n\n" + controllerHashmap.get(s));
            }
        });
    }
}
