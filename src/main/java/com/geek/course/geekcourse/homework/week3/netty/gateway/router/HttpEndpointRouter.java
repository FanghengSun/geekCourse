package com.geek.course.geekcourse.homework.week3.netty.gateway.router;

import java.util.List;

public interface HttpEndpointRouter {
    
    String route(List<String> endpoints);
    
    // Load Balance
    // Random
    // RoundRibbon 
    // Weight
    // - server01,20
    // - server02,30
    // - server03,50
    
}
