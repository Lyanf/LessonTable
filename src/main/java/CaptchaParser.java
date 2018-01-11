import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

class CaptchaParser {
    private final String APP_ID = "10603741";
    private final String API_KEY = "kqpFZInZlp2tuqGbxodtUudm";
    private final String SECRET_KEY = "TZmarRGqks6r3EjD47yGUb2yztNg7qWe";
    private AipOcr aipOcr = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

    public String getCaptchaText(byte[] image) {
        JSONObject jsonObject;
        jsonObject = aipOcr.basicGeneral(image, new HashMap<String, String>());
        JSONArray maps = (JSONArray) jsonObject.get("words_result");
        JSONObject jsonObject1 = (JSONObject) maps.get(0);
        //
        System.out.println("验证码成功："+jsonObject1.get("words"));
        //
        return (String) jsonObject1.get("words");
    }

}
