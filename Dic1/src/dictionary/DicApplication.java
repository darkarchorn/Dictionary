package dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class DicApplication extends Application {
    Stage window = new Stage();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("/Dictionary.fxml"));
        stage.setTitle("Simple Dictionary");
        window = stage;
        Scene scene1 = new Scene(root1, 710, 610);
        window.setScene(scene1);

        window.show();
    }
}