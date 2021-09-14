package ws.springcloud.consumer.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author WindShadow
 * @version 2021-05-10.
 */
@Slf4j
@WebFilter("/**")
@Component
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.getSession().setAttribute("getSession","getSession");

        log.info(">>> RequestHeaders ------------------------------------------");
//        log.info("URL: {}",request.getRequestURL());
        doLog(getHeaders(request));
        log.info(">>> RequestInfo ------------------------------------------");
        doLog(getRequestInfo(request));
        log.info(">>> Params ------------------------------------------");
        doLog(getParams(request));
//        log.info(">>> Cookies ------------------------------------------");
//        doLog(getCookies(request));
        chain.doFilter(request,response);
    }

    public static void doLog(Map<String,Object> map) {

        map.forEach((k,v) -> log.info("{} : {}",k,v));
    }

    public static Map<String,Object> getRequestInfo(HttpServletRequest request) {

        Map<String,Object> map = new LinkedHashMap<>();
        map.put("ContextPath",request.getContextPath());
        map.put("URL",request.getRequestURL());
        map.put("URI",request.getRequestURI());
        map.put("QueryString",request.getQueryString());
        map.put("RemoteAddr",request.getRemoteAddr());
        map.put("RemoteHost",request.getRemoteHost());
        return map;
    }

    public static Map<String,Object> getParams(HttpServletRequest request) {

        return request.getParameterMap().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,e ->dealStringArray(e.getValue())));
    }

    public static Map<String,Object> getHeaders(HttpServletRequest request) {

        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String,Object> map = new HashMap<>();
        while (headerNames.hasMoreElements()) {

            String key = headerNames.nextElement();
            map.put(key,request.getHeader(key));
        }
        return map;
    }

    public static Map<String,Object> getCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        Map<String,Object> map = new LinkedHashMap<>();
        if (cookies != null) {

            Arrays.stream(cookies).forEach(c ->{

                String key = c.getName();
                int i = 2;
                while (map.containsKey(key)) {

                    key = key + "$" + i;
                    i++;
                }
                map.put(key,c.getValue() + " path:" + c.getPath() + " age: " + c.getMaxAge());
            });
        }
        return map;
    }

    public static String dealStringArray(String[] strs) {

        return strs != null && strs.length != 0 ?
                strs.length == 1 ? strs[0] : Arrays.toString(strs)
                : "";
    }
}
