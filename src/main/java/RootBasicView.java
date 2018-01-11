import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    public boolean isHasInit() {
        return hasInit;
    }

    public void setHasInit(boolean hasInit) {
        this.hasInit = hasInit;
    }

    public LessonTableView getLessonTableView() {
        return lessonTableView;
    }

    public void setLessonTableView(LessonTableView lessonTableView) {
        this.lessonTableView = lessonTableView;
    }

    public InitTable getStroedInitTable() {
        return stroedInitTable;
    }

    public void setStroedInitTable(InitTable stroedInitTable) {
        this.stroedInitTable = stroedInitTable;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int i;
        for (i = 1; i <= 18; i++) {
            weekSelect.getItems().add("第" + i + "周");
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LessonTableView.fxml"));
        try {
            tableRootPane.getChildren().add(fxmlLoader.load());
            lessonTableView = fxmlLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
        linkButton.setOnAction(new HandleLink());
        weekSelect.getSelectionModel().select(0);
        weekSelect.getSelectionModel().selectedIndexProperty().addListener(new HandleChoice());
    }

    class HandleChoice implements ChangeListener {
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
            HandleButton handleButton = new HandleButton();
            handleButton.start();
        }

        class HandleButton extends Thread {
            @Override
            public void run() {
                String ID = userID.getText();
                String PW = userPassword.getText();
                Link link = new Link(ID, PW);
                String week = weekSelect.getValue();
                int weekNum;
                if (week.length() == 3) {
                    weekNum = Integer.parseInt(week.substring(1, 2));
                } else weekNum = Integer.parseInt(week.substring(1, 3));
                InitTable initTable = new InitTable(lessonTableView);
                if(!initTable.init(link)){
                    return;
                }
                final int x = weekNum;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        initTable.showWeekLessons(x);
                    }
                });
                stroedInitTable = initTable;
                hasInit = true;
            }
        }
    }
}
