import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RootBasic.fxml"));
        try {
            Parent parent = fxmlLoader.load();
            primaryStage.setTitle("兰大课程表");
            primaryStage.setScene(new Scene(parent));
//            AnchorPane anchorPane = fxmlLoader.load();
//            primaryStage.setScene(new Scene(anchorPane,Color.TRANSPARENT));
//            primaryStage.initStyle(StageStyle.TRANSPARENT);
//            LessonTableView lessonTableView = fxmlLoader.getController();
//            InitTable initTable = new InitTable(lessonTableView);
//            initTable.init();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}