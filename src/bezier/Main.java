package bezier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Controller controller;

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader();

        Parent root = loader.load(getClass().getResource("sample.fxml").openStream());
        controller = loader.getController();

        stage.setTitle("CS4102");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
