import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author kaizhang
 * @date 2023年11月15日11:31:49
 */
public class BocUrlGeneratorTest {
    @Test
    public void generateBocUrl() throws UnsupportedEncodingException {
//        String labelUrl = "https://malla.leagpoint.com/hjf/home/262?hjf_source=JX2571196786h8vq9P69&hjf_rm_token=H5";
//        String labelUrl = "https://teste.leagpoint.com/hjf/home/256?hjf_source=JX2571196786h8vq9P69&hjf_rm_token=H5";
//        String labelUrl = "https://m.huajifen.com/hjf/sweepstakes?activity_id=144&hjf_source=Y718m52W2dd3D42x2687&hjf_rm_token=H5";
        String labelUrl = "https://m.huajifen.com/hjf/sweepstakes?activity_id=149&hjf_source=Y718m52W2dd3D42x2687&hjf_rm_token=H5";
        labelUrl = URLEncoder.encode(labelUrl, "UTF-8");
        RightParam rightParam = new RightParam();
        rightParam.setLabelUrl(labelUrl);
        Param param = new Param();
        param.setParams(rightParam);

        String paramStr = JSONUtil.toJsonStr(param);
        System.out.println(paramStr);
        String encodeJsonStr = URLEncoder.encode(paramStr, "UTF-8");
        System.out.println(encodeJsonStr);
    }

    public static class Param {
        private RightParam params;

        public RightParam getParams() {
            return params;
        }

        public void setParams(RightParam params) {
            this.params = params;
        }
    }


    public static class RightParam {
        private String cusNo;

        private String mblNo;

        private String sign;

        private String timestamp;

        private String type;

        private String labelUrl;

        public String getCusNo() {
            return cusNo;
        }

        public void setCusNo(String cusNo) {
            this.cusNo = cusNo;
        }

        public String getMblNo() {
            return mblNo;
        }

        public void setMblNo(String mblNo) {
            this.mblNo = mblNo;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLabelUrl() {
            return labelUrl;
        }

        public void setLabelUrl(String labelUrl) {
            this.labelUrl = labelUrl;
        }
    }
}
