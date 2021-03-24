import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.Converter;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author KaiZhang
 * date 2021-03-18
 */

public class ConvertDemoTest {
    /**
     * 转换为字符串
     */
    @Test
    public void toStr() {
        int a = 1;
        // aStr为"1"
        String aStr = Convert.toStr(a);
        System.out.println(StrFormatter.format("int类型转String类型:{}", aStr));

        long[] b = {1, 2, 3, 4, 5};
        // bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);
        System.out.println(StrFormatter.format("数组转字符串数组；{}", bStr));
    }


    /**
     * 转换为指定数组
     */
    @Test
    public void toArray() {
        String[] b = {"1", "2", "3", "4"};
        // 结果为Integer数组
        Integer[] intArray = Convert.toIntArray(b);
        // 结果为Long数组
        Long[] longArray = Convert.toLongArray(b);

        long[] c = {1, 2, 3, 4, 5};
        // 结果为Integer数组
        Integer[] intArray2 = Convert.toIntArray(c);
    }

    /**
     * 转换为日期
     * {@link cn.hutool.core.date.DateUtil#parse(CharSequence)}
     */
    @Test
    public void toDate() {
        // 转换为Date格式
        String a = "2017-05-06";
        Date dateValue = Convert.toDate(a, new Date());
        System.out.println(dateValue);

        // 转换为LocalDateTime
        String b = "20170506";
        LocalDateTime localDateTime = Convert.toLocalDateTime(b);
        System.out.println(localDateTime);
    }

    /**
     * 转换为集合
     */
    @Test
    public void toList() {
        Object[] a = {"a", "你", "好", "", 1};
        List<?> objectList = Convert.toList(a);
        System.out.println(objectList);
        // 转换为字符串集合
        List<String> stringList = Convert.toList(String.class, a);
        System.out.println(stringList);
    }


    /**
     * 转换为数字格式
     * 999.44d => 玖佰玖拾玖点肆肆
     * 999.44d => NINE HUNDRED AND NINETY NINE AND CENTS FORTY FOUR ONLY
     * 999.44d => NINE HUNDRED AND NINETY NINE AND CENTS FORTY FOUR ONLY
     * 注意范围： -99999999999999.99 <= amount <= 99999999999999.99
     */
    @Test
    public void toNumber() {
        double number = 99999.44d;
        // 将阿拉伯数字转为英文表达方式
        String numberToWord = Convert.numberToWord(number);
        System.out.println(numberToWord);
        // 将阿拉伯数字转为中文表达方式
        String numberToChinese = Convert.numberToChinese(number, true);
        System.out.println(numberToChinese);
        // 将金钱数转换为大写形式 => 玖万玖仟玖佰玖拾玖元肆角肆分
        String digitToChinese = Convert.digitToChinese(number);
        System.out.println(digitToChinese);
    }

    /**
     * 其他类型转换
     */
    @Test
    public void toGeneric() {
        // 1、泛型类型转换：通过convert(TypeReference<T> reference, Object value)方法，自行new一个TypeReference对象可以对嵌套泛型进行类型转换。
        // 通过TypeReference实例化后制定泛型类型，即可转换对象为我们想要的目标类型
        Object[] a = {'a', "你", "好", "", 1};
        List<String> list = Convert.convert(new TypeReference<List<String>>() {}, a);

        Map<String, Long> map = new HashMap<>(16);
        map.put("a", 2L);
        map.put("b", 3L);
        System.out.println(list);
        Map<String, String> convertMap = Convert.convert(new TypeReference<Map<String, String>>() {}, map);
        System.out.println(convertMap);
    }

    /**
     * 16进制转换
     * {@link HexUtil#encodeHexStr(java.lang.String, java.nio.charset.Charset)}
     */
    @Test
    public void toHex() {
        // 将字符串转换为16进制
        String a = "我是一个字符串";
        String hex = Convert.toHex(a, CharsetUtil.CHARSET_UTF_8);
        System.out.println(StrFormatter.format("将字符串转为16进制：{}", hex));

        // 将16进制转换为字符串
        String hexToStr = Convert.hexToStr(hex, CharsetUtil.CHARSET_UTF_8);
        System.out.println(StrFormatter.format("将16进制转换为字符串：{}", hexToStr));
    }

    /**
     * 转换为Unicode
     * {@link UnicodeUtil#toUnicode(java.lang.String)}
     */
    @Test
    public void toUnicode() {
        // 将字符串转换为Unicode
        String a = "我是一个字符串";
        String unicode = Convert.strToUnicode(a);
        System.out.println(unicode);

        // 将Unicode转换为字符串
        String raw = Convert.unicodeToStr(unicode);
        System.out.println(raw);
    }

    /**
     * 编码转换
     * {@link CharsetUtil#convert(java.lang.String, java.lang.String, java.lang.String)}
     */
    @Test
    public void toCharset() {
        String a = "我不是乱码";
        // 转换后result为乱码
        String result = Convert.convertCharset(a, CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        System.out.println(result);
        String raw = Convert.convertCharset(result, CharsetUtil.ISO_8859_1, "UTF-8");
        System.out.println(raw);
    }


    /**
     * 时间单位转换
     */
    @Test
    public void toTimeUnit() {
        long a = 4535345;
        //结果为：75
        long minutes = Convert.convertTime(a, TimeUnit.MILLISECONDS, TimeUnit.MINUTES);
        System.out.println(minutes);
    }


    /**
     * 自定义类型转换
     * Converter 类型转换接口，通过实现这个接口，重写convert方法，以实现不同类型的对象转换
     * ConverterRegistry 类型转换登记中心。将各种类型Convert对象放入登记中心，通过convert方法查找目标类型对应的转换器，将被转换对象转换之。在此类中，存放着默认转换器和自定义转换器，默认转换器是Hutool中预定义的一些转换器，自定义转换器存放用户自定的转换器。
     */
    @Test
    public void toUserDefined () {
        int a = 3423;
        ConverterRegistry converterRegistry = ConverterRegistry.getInstance();
        String result = converterRegistry.convert(String.class, a);
        System.out.println(result);
    }

    @Test
    public void toUserFined2() {
        ConverterRegistry converterRegistry = ConverterRegistry.getInstance();
        //  此处做为示例自定义String转换，因为Hutool中已经提供String转换，请尽量不要替换
        //  替换可能引发关联转换异常（例如覆盖String转换会影响全局）
        converterRegistry.putCustom(String.class, MyCustomConverter.class);
        int a = 454553;
        String result = converterRegistry.convert(String.class, a);
        System.out.println(result);

    }

    /**
     * 自定义转换器
     */
    public static class MyCustomConverter implements Converter<String> {
        @Override
        public String convert(Object value, String defaultValue) throws IllegalArgumentException {
            return "Custom: " + value.toString();
        }
    }
}
