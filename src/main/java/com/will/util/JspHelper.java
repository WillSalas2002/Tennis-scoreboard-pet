package com.will.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {
    private final static String JSP_FORMAT = "/WEB-INF/tennis/%s.jsp";

    public static String getPath(String jsp) {
        return JSP_FORMAT.formatted(jsp);
    }
}
