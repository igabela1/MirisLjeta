package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Main class
 */
public class AppFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Home/SignIn.fxml"));
        primaryStage.setTitle("Miris Ljeta");
        primaryStage.setScene(new Scene(root,USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
/*package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

/**
 * The type App fx.
 */
/*public class AppFX extends Application
{
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    /*public static void main(String[] args) {
        // Here you can work with args - command line parameters
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/home/home.fxml")));
      //  primaryStage.getIcons().add(new Image("images/.png"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/stylesheets/styles.css");
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.show();
    }}*/