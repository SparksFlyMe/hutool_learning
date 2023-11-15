import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author kaizhang
 * @date 2023年11月15日11:31:38
 */
public class QrCodeTest {
    /**
     * 生成图形二维码
     */
    @Test
    public void qrCodeTest() {
        QrConfig qrConfig = new QrConfig(500, 500);
//        qrConfig.setBackColor(Color.blue);
        String content = "https://m.huajifen.com/hjf/home/197?hjf_source=HUAJIFEN9908HUAJIFEN";
        QrCodeUtil.generate(content, qrConfig, new File("E:\\qrCode.jpg"));
    }
}
