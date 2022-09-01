package com.bingo.springmongodb.util;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class DateUtil {
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 在一个日期值上往前或往后推n天,往前推为负值，往后推为正值
     *
     * @param date
     * @param diff
     * @return
     */
    public static Date operDate(Date date, int diff) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, diff);
        return calendar.getTime();
    }

    public static Date getDateBegin(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDateEnd(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            date = new Date();
        }
        if (pattern == null) {
            pattern = DEFAULT_PATTERN;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static void main(String[] args) {
        // Date date = new Date();
        // System.out.println(formatDate(getDateBegin(operDate(date, -7)), null));
        // System.out.println(formatDate(getDateEnd(operDate(date, -1)), null));
        // Calendar calendar = Calendar.getInstance();
        // System.out.println(calendar.get(Calendar.MILLISECOND));
        String str = "{" +
                "\"str\"" + ":" + "\"zifuchuan\"" + "," +
                "\"id\"" + ":" + "123" + "," +
                "\"maps\"" + ":" + "{" +
                        "\"openid\"" + ":" + "\"mapcontent\"" +
                        "}" +
                "}";


        String str2 = "{\n" +
                "  \"channelCode\": \"wx_lite\",\n" +
                "  \"channelExtras\": {\n" +
                "      \"openid\" :\"ocjmD5VRY3quJN0dFMLWwhA8pZP4\"\n" +
                "  },\n" +
                "  \"id\": 125\n" +
                "}";
        TestJson ts = JSON.parseObject(str2,TestJson.class);

        System.out.println(ts);
    }
}

class TestJson{
    private String channelCode;

    private Long id;

    private Map<String,String> channelExtras;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, String> getChannelExtras() {
        return channelExtras;
    }

    public void setChannelExtras(Map<String, String> channelExtras) {
        this.channelExtras = channelExtras;
    }

    @Override
    public String toString() {
        return "TestJson{" +
                "channelCode='" + channelCode + '\'' +
                ", id=" + id +
                ", channelExtras=" + channelExtras +
                '}';
    }
}