package com.mtime.wordbank.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Mtime on 2016/3/1.
 */
public class ExceptionUtils {
    /**
     * 将异常转化为字符串
     *
     */
    public static String getStackTraceStr(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
