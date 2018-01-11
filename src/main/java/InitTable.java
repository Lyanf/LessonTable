import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

public class InitTable {
    LessonTableView lessonTableView;
    Link link;
    Lesson[] lessons;

    public LessonTableView getLessonTableView() {
        return lessonTableView;
    }

    public void setLessonTableView(LessonTableView lessonTableView) {
        this.lessonTableView = lessonTableView;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Lesson[] getLessons() {
        return lessons;
    }

    public void setLessons(Lesson[] lessons) {
        this.lessons = lessons;
    }

    public InitTable(LessonTableView lessonTableView) {
        this.lessonTableView = lessonTableView;
    }

    public boolean init(Link link) {
        this.link = link;
        lessons = link.getLessons();
        if (lessons == null) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert  = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("用户名或密码错误");
                    alert.showAndWait();
                }
            });
            return false;
        }
        return true;
    }

    public void showWeekLessons(int week) {
        Color color = new Color(0.1412, 1, 0.2039, 0.4745);
        lessonTableView.clearLessons();
        for (Lesson lesson : lessons) {
            lessonTableView.setLesson(lesson, color, week);
        }
    }

}
