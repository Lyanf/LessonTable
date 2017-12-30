import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class LessonTableView extends Control {
    @FXML
    private AnchorPane root;
    @FXML
    private GridPane table;
    @FXML
    private Canvas testCanvas;

    private void setTableCell(String lessonName, String room, Color color, int column, int row, int columnSpan, int rowSpan) {
        FlowPane flowPane = new FlowPane();
        flowPane.setPrefWidth(80);
        flowPane.setMaxWidth(80);
        flowPane.setBackground(new Background(new BackgroundFill(color, null, null)));
        flowPane.setStyle("-fx-background-radius: 20px;" +
                "-fx-background-color: aqua;" +
                "-fx-border-radius: 20px;" +
                "-fx-padding: 5,5,5,5;" +
                "-fx-pref-width: 80;" +
                "-fx-max-width: 80;");
        int nameLength = lessonName.length();
        if (nameLength>18){
            lessonName = lessonName.substring(0,15)+"...";
        }
//        flowPane.setStyle("-fx-background-color: ");
        Label text = new Label(lessonName + " " + room);
//        text.setMaxWidth(80);
//        text.setPrefWidth(80);
//        text.setWrapText(true);
//        text.setStyle("fxfi");
//        text.setStyle("-fx-font: 900");
        text.setStyle("-fx-text-fill: white;" +
                "-fx-stroke:white;" +
                "-fx-wrap-text: true;" +
                "-fx-max-width: 80;" +
                "-fx-pref-width: 80;" +
                "-fx-color-label-visible: true;" +
                "-fx-progress-color: white;");
//        text.setStyle("-fx-stroke: snow;-fx-fill: snow");
        text.setLayoutX(20);
        text.setLayoutY(20);
        flowPane.getChildren().add(text);
        table.add(flowPane, column, row, columnSpan, rowSpan);
    }
    public void setLesson(Lesson lesson,Color color,int week){
        String lessonName = lesson.getLessonName();
        String teacherName = lesson.getTeacherName();
        ArrayList<LessonTimeAndRoom> lessonTimeAndRooms = lesson.getLessonTimeAndRoomList();
        root.setStyle("-fx-background-color: transparent");
        table.setStyle("-fx-background-color:rgba(230,229,234,0.55)");
        for (LessonTimeAndRoom lessonTimeAndRoom : lessonTimeAndRooms) {
            if (!lessonTimeAndRoom.isTrueLesson())  continue;;
            if (week>=lessonTimeAndRoom.getWeekStart()
                    &&week<=lessonTimeAndRoom.getWeekEnd()){
                if (lessonTimeAndRoom.getWeekType() == LessonTimeAndRoom.WeekType.quanzhou){
                    setTableCell(lessonName,lessonTimeAndRoom.getRoom(),color,
                            lessonTimeAndRoom.getDay(),lessonTimeAndRoom.getTimeStart(),
                            1,lessonTimeAndRoom.getTimeEnd()-lessonTimeAndRoom.getTimeStart()+1 );
                }
                else if (lessonTimeAndRoom.getWeekType() == LessonTimeAndRoom.WeekType.danzhou&&week%2!=0){
                    setTableCell(lessonName,lessonTimeAndRoom.getRoom(),color,
                            lessonTimeAndRoom.getDay(),lessonTimeAndRoom.getTimeStart(),
                            1,lessonTimeAndRoom.getTimeEnd()-lessonTimeAndRoom.getTimeStart()+1 );
                }
                else if (lessonTimeAndRoom.getWeekType() == LessonTimeAndRoom.WeekType.shuangzhou&&week%2==0){
                    setTableCell(lessonName,lessonTimeAndRoom.getRoom(),color,
                            lessonTimeAndRoom.getDay(),lessonTimeAndRoom.getTimeStart(),
                            1,lessonTimeAndRoom.getTimeEnd()-lessonTimeAndRoom.getTimeStart()+1 );
                }
            }
        }
    }
}

