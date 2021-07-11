package com.geek.course.geekcourse.homework.week3.netty.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpRequestFilter {
    
    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
    
}
