import javafx.scene.paint.Color;

public class InitTable {
    LessonTableView lessonTableView;
    Link link;
    Lesson[] lessons;

    public InitTable(LessonTableView lessonTableView) {
        this.lessonTableView = lessonTableView;
    }

    public void init(Link link) {
        this.link = link;
        lessons = link.getLessons();
        for (Lesson lesson : lessons) {
            System.out.println(lesson.getLessonName());
            for (LessonTimeAndRoom lessonTimeAndRoom : lesson.getLessonTimeAndRoomList()) {
                System.out.println(lessonTimeAndRoom.getWeekType());
            }
        }
//        Color color = new Color(0.6588, 0.9725, 1, 0.8);
        if (lessons == null) {
            System.out.println("用户名或者密码错误");
        }
//      else {
//            for (Lesson lesson : lessons) {
//                lessonTableView.setLesson(lesson, color, 2);
//            }
//        }
    }
    public void showWeekLessons(int week){
                Color color = new Color(0.6588, 0.9725, 1, 0.8);
        lessonTableView.clearLessons();

        for (Lesson lesson : lessons) {
            lessonTableView.setLesson(lesson,color,week);
        }
    }

}
