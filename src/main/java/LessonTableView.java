import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
    private ArrayList<FlowPane> flowPanes = new ArrayList<>();

    public ArrayList<FlowPane> getFlowPanes() {
        return flowPanes;
    }

    public void setFlowPanes(ArrayList<FlowPane> flowPanes) {
        this.flowPanes = flowPanes;
    }

    private void setTableCell(String lessonName, String room, Color color, int column, int row, int columnSpan, int rowSpan) {
        FlowPane flowPane = new FlowPane();
        flowPane.setPrefWidth(80);
        flowPane.setMaxWidth(80);
        flowPane.setBackground(new Background(new BackgroundFill(color, null, null)));
        flowPane.setStyle("" +
                "-fx-background-radius: 3px;" +
                "-fx-background-color: rgb(181,177,136);" +
                "-fx-border-radius: 3px;" +
                "-fx-padding: 5,5,5,5;" +
                "-fx-pref-width: 80;" +
                "-fx-max-width: 80;");
        int nameLength = lessonName.length();
        if (nameLength > 8) {
            lessonName = lessonName.substring(0, 8) + "...";
        }
        Label text = new Label(lessonName + " " + room);
        text.setStyle("-fx-text-fill: white;" +
                "-fx-stroke:white;" +
                "-fx-wrap-text: true;" +
                "-fx-max-width: 80;" +
                "-fx-pref-width: 80;" +
                "-fx-color-label-visible: true;" +
                "-fx-progress-color: white;");
        text.setLayoutX(20);
        text.setLayoutY(20);
        flowPane.getChildren().add(text);
        flowPanes.add(flowPane);
        table.add(flowPane, column, row, columnSpan, rowSpan);
        GridPane.setMargin(flowPane, new Insets(2, 0, 2, 0));
//        flowPane.setPrefHeight(30*columnSpan-3);
//        flowPane.setMaxHeight(30*columnSpan-3);
    }

    public void setLesson(Lesson lesson, Color color, int week) {
        String lessonName = lesson.getLessonName();
        String teacherName = lesson.getTeacherName();
        ArrayList<LessonTimeAndRoom> lessonTimeAndRooms = lesson.getLessonTimeAndRoomList();
        root.setStyle("-fx-background-color: rgb(255,255,255)");
        table.setStyle("-fx-background-color:rgb(255,255,255)");
        for (LessonTimeAndRoom lessonTimeAndRoom : lessonTimeAndRooms) {
            if (!lessonTimeAndRoom.isTrueLesson()) continue;
            ;
            if (week >= lessonTimeAndRoom.getWeekStart()
                    && week <= lessonTimeAndRoom.getWeekEnd()) {
                if (lessonTimeAndRoom.getWeekType() == LessonTimeAndRoom.WeekType.quanzhou) {
                    setTableCell(lessonName, lessonTimeAndRoom.getRoom(), color,
                            lessonTimeAndRoom.getDay(), lessonTimeAndRoom.getTimeStart(),
                            1, lessonTimeAndRoom.getTimeEnd() - lessonTimeAndRoom.getTimeStart() + 1);
                } else if (lessonTimeAndRoom.getWeekType() == LessonTimeAndRoom.WeekType.danzhou && week % 2 != 0) {
                    setTableCell(lessonName, lessonTimeAndRoom.getRoom(), color,
                            lessonTimeAndRoom.getDay(), lessonTimeAndRoom.getTimeStart(),
                            1, lessonTimeAndRoom.getTimeEnd() - lessonTimeAndRoom.getTimeStart() + 1);
                } else if (lessonTimeAndRoom.getWeekType() == LessonTimeAndRoom.WeekType.shuangzhou && week % 2 == 0) {
                    setTableCell(lessonName, lessonTimeAndRoom.getRoom(), color,
                            lessonTimeAndRoom.getDay(), lessonTimeAndRoom.getTimeStart(),
                            1, lessonTimeAndRoom.getTimeEnd() - lessonTimeAndRoom.getTimeStart() + 1);
                }
            }
        }
    }

    public void clearLessons() {
        FlowPane[] temp = new FlowPane[flowPanes.size()];
        flowPanes.toArray(temp);
        table.getChildren().removeAll(temp);
    }
}

