import javafx.scene.paint.Color;

public class InitTable {
    LessonTableView lessonTableView;
    public InitTable(LessonTableView lessonTableView){
        this.lessonTableView = lessonTableView;
    }
    public void init() {
        Link link = new Link("320150939151", "a7889280");
        Lesson[] lessons = link.getLessons();
        Color color = new Color(0.6588, 0.9725, 1, 0.8);
        if (lessons == null) {
            System.out.println("用户名或者密码错误");
        } else {
            for (Lesson lesson : lessons) {
                lessonTableView.setLesson(lesson, color, 2);
            }
        }
    }
}
