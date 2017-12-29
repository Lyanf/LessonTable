import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;

class HtmlParser {
    private Document document = null;

    public HtmlParser(File in) {
        try {
            document = Jsoup.parse(in, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Elements getAllLesson() {
        Element courseTable = document.getElementsByClass("infolist_tab").get(0);
        return courseTable.getElementsByClass("infolist_common");
    }

    private String getLessonName(Element e) {
        Elements elements = e.getElementsByTag("a");
        return elements.get(0).text();
    }

    private String getTeacherName(Element e) {
        Elements elements = e.getElementsByTag("a");
        return elements.get(1).text();
    }

    private Element getLessonElement(int i) {
        return getAllLesson().get(i);
    }

    public ArrayList<String> getTimeAndRoomString(Element e) {
        ArrayList<String> tempStrings = new ArrayList<String>();
        Elements elements = e.getElementsByTag("tbody").get(0).getElementsByTag("tr");
        for (Element element : elements) {
            tempStrings.add(element.text());
        }
        return tempStrings;
    }

    public Lesson[] getLessonList() {
        Elements elements = this.getAllLesson();
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        for (Element element : elements) {
            lessons.add(new Lesson(this.getTimeAndRoomString(element), this.getLessonName(element), this.getTeacherName(element)));
        }
        return lessons.toArray(new Lesson[lessons.size()]);
    }

}
