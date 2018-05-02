import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Link {
    private String userID, userPassword;

    public Link(String userID, String userPassword) {
        this.userID = userID;
        this.userPassword = userPassword;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Lesson[] getLessons() {
        try {
            String picUrl = "http://jwk.lzu.edu.cn/academic/getCaptcha.do?";
            String loginCheck = "http://jwk.lzu.edu.cn/academic/j_acegi_security_check";
            String lesson = "http://jwk.lzu.edu.cn/academic/student/currcourse/currcourse.jsdo?groupId=&moduleId=2000";
            OkHttpClient okHttpClient;
            Request picRequest;
            Response response;
            Request request;
            while (true) {
                okHttpClient = new OkHttpClient.Builder().cookieJar(new MyCookieJar()).build();
                picRequest = new Request.Builder().url(picUrl).build();
                response = okHttpClient.newCall((picRequest)).execute();
                byte[] img = new byte[2000];
                InputStream inputStream = response.body().byteStream();
                int j = inputStream.read(img);
                //以上是用来获取图片并且有了cookie，下面吧cookie用起来，并且去登录
                CaptchaParser captchaParser = new CaptchaParser();
                String captcha = captchaParser.getCaptchaText(img);
                FormBody formBody = new FormBody.Builder()
                        .add("groupId", "")
                        .add("j_username", userID)
                        .add("j_password", userPassword)
                        .add("j_captcha", captcha)
                        .add("button1", "%B5%C7%C2%BC")
                        .build();
                request = new Request.Builder()
                        .url(loginCheck)
                        .post(formBody)
                        .build();
                response = okHttpClient.newCall(request).execute();
                String rebody = response.body().string();
                if (judgePass(rebody) == 0) {
                    continue;
                }
                else if (judgePass(rebody) ==1){
                    break;
                }
                else {
                    return null;
                }
            }
            request = new Request.Builder()
                    .url(lesson)
                    .get()
                    .build();
            response = okHttpClient.newCall(request).execute();
            String get = response.body().string();
            HtmlParser htmlParser = new HtmlParser(get);
            Lesson[] lessons = htmlParser.getLessonList();
            return lessons;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private int judgePass(String html) {
        org.jsoup.nodes.Document document = Jsoup.parse(html);
        if (!html.contains("error")) {
            return 1;
        }
        Element element = document.getElementById("error");
        String error = element.text().toString();
        if (error.contains("验证码")) {
            return 0;
        } else return -1;


    }

    class MyCookieJar implements CookieJar {
        private HashMap<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();

        public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
            cookieStore.put(httpUrl.host(), list);
        }

        public List<Cookie> loadForRequest(HttpUrl httpUrl) {
            if (cookieStore.get(httpUrl.host()) == null) {
                return new ArrayList<Cookie>();
            } else {
                return cookieStore.get(httpUrl.host());
            }
        }
    }
}
