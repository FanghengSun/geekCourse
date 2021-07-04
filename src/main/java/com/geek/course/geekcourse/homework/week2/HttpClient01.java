package com.geek.course.geekcourse.homework.week2;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpHeaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Fangheng Sun on 2021/7/4
 */
public class HttpClient01 {


    public static void main(String[] args) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:8801");
        HttpResponse response = client.execute(request);

        // Get the response
        BufferedReader rd = new BufferedReader
                (new InputStreamReader(
                        response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }
}
