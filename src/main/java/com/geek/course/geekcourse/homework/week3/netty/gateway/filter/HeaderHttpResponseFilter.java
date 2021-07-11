package com.geek.course.geekcourse.homework.week3.netty.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;
import org.springframework.util.StringUtils;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("kk", "java-1-nio");
        if (!StringUtils.isEmpty(response.headers().get("server"))) {
            response.headers().set("server", response.headers().get("server"));
        }
    }
}
