package com.suixingpay.profit;

import com.suixingpay.profit.utils.DateTimes;
import com.suixingpay.profit.utils.LogUtils;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCopare {
    @Test
    public void test() {
        if (0 == new BigDecimal("-100").compareTo(new BigDecimal("0"))) {

        }
        int i = new BigDecimal("-100").compareTo(new BigDecimal("0"));
        LogUtils.info("查询的数据是" + i);
    }

    @Test
    public void test02(){
        boolean number = isNumber("-2");
        LogUtils.info("校验100的结果是"+number);
    }

    //金额验证
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^(\\-)?\\d+(\\.\\d{1,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match = pattern.matcher(str);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }

    @Test
    public void test03(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 3);
        Date time = cal.getTime();
        String defaultDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int i = currentDate.compareTo(defaultDate);
        LogUtils.info("返回的数是"+i);

    }
    @Test
    public void testMonth() throws ParseException {
        boolean b = DateTimes.checkMonth("2019-03", "2019-03");

        LogUtils.info("返回的数是"+b);

    }

    @Test
    public void testSlf4j() {
        org.slf4j.Logger logger = LoggerFactory.getLogger(Object.class);
            logger.error("123");
         }

   /* @Test
    public void test05(){

        String str = "{\"0\":\"zhangsan\",\"1\":\"lisi\",\"2\":\"wangwu\",\"3\":null}";
        Map mapObj = JSONObject.parseObject(str,Map.class);
        LogUtils.info(JSON.toJSONString( mapObj, SerializerFeature.WriteMapNullValue));
    }*/


}
