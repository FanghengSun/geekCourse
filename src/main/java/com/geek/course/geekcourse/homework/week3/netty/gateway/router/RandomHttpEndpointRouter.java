package com.geek.course.geekcourse.homework.week3.netty.gateway.router;

import java.util.List;
import java.util.Random;

public class RandomHttpEndpointRouter implements HttpEndpointRouter {
    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        return urls.get(random.nextInt(size));
    }
}
