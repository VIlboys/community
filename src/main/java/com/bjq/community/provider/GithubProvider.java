package com.bjq.community.provider;

import com.alibaba.fastjson.JSON;
import com.bjq.community.entity.AccessTokenEntity;
import com.bjq.community.entity.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 2019/4/24
 */
@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenEntity accessTokenEntity) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenEntity));

        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
       try(Response response = client.newCall(request).execute()){
           String string = response.body().string();
           String[] split = string.split("&");
           String tokenstr = split[0];
           String token = tokenstr.split("=")[1];
           return token;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }

    public GithubUser githubUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser user = JSON.parseObject(string, GithubUser.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
