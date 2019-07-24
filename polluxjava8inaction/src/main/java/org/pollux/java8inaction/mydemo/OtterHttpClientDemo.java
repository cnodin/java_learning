package org.pollux.java8inaction.mydemo;

import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-02-21
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class OtterHttpClientDemo {

    public static void main(String[] args) throws IOException {
        
        String loginUrl = "http://192.168.1.49:18080/login.htm";
        String operateChannelUrl = "http://192.168.1.49:18080/?action=channelAction&channelId=2&status=start" +
                "&pageIndex=1&searchKey=&eventSubmitDoStatus=true";
        FormBody formBody = new FormBody.Builder()
                            .add("action", "user_action")
                            .add("event_submit_do_login", "1")
                            .add("_fm.l._0.n", "admin")
                            .add("_fm.l._0.p", "admin")
                            .build();

        final Request loginRequest = new Request.Builder().url(loginUrl).post(formBody).build();

        OkHttpClient httpClient = new OkHttpClient().newBuilder().cookieJar(new CookieJar() {

            private Map<HttpUrl, List<Cookie>> cookieStore = new HashMap();

            @Override
            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> cookies) {
                cookieStore.put(httpUrl, cookies);
                cookieStore.put(HttpUrl.parse(operateChannelUrl), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                List<Cookie> cookies = cookieStore.get(HttpUrl.parse(operateChannelUrl));
                if (cookies == null) {
                    System.out.println("cookies does not loaded");
                }
                return cookies != null ? cookies : new ArrayList<>();
            }
        }).build();

        Call call = httpClient.newCall(loginRequest);
        call.execute();

        final Request operateChannelReqeust = new Request.Builder().url(operateChannelUrl).build();
        Call call2 = httpClient.newCall(operateChannelReqeust);
        call2.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("call operate channel request failure, " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();
                System.out.println("response.code: " + response.code());
                System.out.println(responseContent);
            }
        });
    }

}
