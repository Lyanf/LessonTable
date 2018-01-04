import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class RootBasicView implements Initializable {
    @FXML
    AnchorPane tableRootPane;
    @FXML
    TextField userID;
    @FXML
    TextField userPassword;
    @FXML
    Button linkButton;
    @FXML
    ChoiceBox<String> weekSelect;
    private boolean hasInit = false;
    LessonTableView lessonTableView;
    InitTable stroedInitTable;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int i;
        for (i = 1; i <= 18; i++) {
            weekSelect.getItems().add("第" + i + "周");
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LessonTableView.fxml"));
        try {
            tableRootPane.getChildren().add( fxmlLoader.load());
            lessonTableView = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        linkButton.setOnAction(new HandleLink());
        weekSelect.getSelectionModel().selectedIndexProperty().addListener(new HandleChoice());
        File file = new File("123.txt");


    }
    class HandleChoice implements ChangeListener{
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            if (hasInit) {
                int week = (int) newValue + 1;
                stroedInitTable.showWeekLessons(week);
            }
        }
    }
    class HandleLink implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
//            InitTable initTable = new InitTable((AnchorPane)tableRootPane);
            String ID = userID.getText();
            String PW = userPassword.getText();
            Link link = new Link(ID,PW);
            String week = weekSelect.getValue();
            int weekNum;
            if (week.length() == 3){
                weekNum = Integer.parseInt(week.substring(1,2));
            }
            else {
                weekNum = Integer.parseInt(week.substring(1,3));
            }
            InitTable initTable = new InitTable(lessonTableView);
            initTable.init(link);
            initTable.showWeekLessons(weekNum);
            stroedInitTable = initTable;
            hasInit = true;

        }
    }
}
