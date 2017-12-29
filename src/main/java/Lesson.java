import java.util.ArrayList;

class Lesson {
    private ArrayList<LessonTimeAndRoom> lessonTimeAndRoomList;
    private String lessonName;
    private String teacherName;

    public Lesson(ArrayList<String> lessonTimeAndRooms, String lessonName, String teacherName) {
        this.lessonTimeAndRoomList = new ArrayList<LessonTimeAndRoom>();
        for (String lessonTimeAndRoom : lessonTimeAndRooms) {
            LessonTimeAndRoom t = new LessonTimeAndRoom(lessonTimeAndRoom);
            lessonTimeAndRoomList.add(t);
        }
        this.lessonName = lessonName;
        this.teacherName = teacherName;
    }

    public ArrayList<LessonTimeAndRoom> getLessonTimeAndRoomList() {
        return lessonTimeAndRoomList;
    }

    public String getLessonName() {
        return lessonName;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
