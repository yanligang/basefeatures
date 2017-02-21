package cn.vlooks.www.app.tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author
 */
public class JsonResponseParser {
    private String errcode;
    private JSONArray data;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }

    public JsonResponseParser(String data) throws JSONException {
        JSONObject jsonObject=new JSONObject(data);
        this.errcode = jsonObject.optString("errcode");
        this.data = jsonObject.optJSONArray("data");
    }


}
