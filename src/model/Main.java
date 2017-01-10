package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Ajnol
 */
public class Main extends Application {
    
    // File types to look for and folders to exclude
    private static String[] extensions = new String[] { ".mkv", ".mp4", ".m4p", ".m4v", ".vob", ".avi", ".mov", ".wmv", ".mpg", ".mpeg", ".m2v" };
    private String[] excFolder = new String[] { "behind the scenes", "deleted scenes", "interviews", "trailers" };

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.getIcons().add(new Image("/assets/icon.png"));
        stage.setTitle("Random Movie");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static String[] getExtensions() {
        return extensions;
    }

    public String[] getExcFolder() {
        return excFolder;
    }
    
}
