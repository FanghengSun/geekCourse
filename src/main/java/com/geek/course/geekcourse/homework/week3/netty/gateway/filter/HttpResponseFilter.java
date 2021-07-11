package com.geek.course.geekcourse.homework.week3.netty.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public interface HttpResponseFilter {

    void filter(FullHttpResponse response);

}
