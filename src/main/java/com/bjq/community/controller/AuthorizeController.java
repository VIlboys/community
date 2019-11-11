package com.bjq.community.controller;

import com.bjq.community.entity.AccessTokenEntity;
import com.bjq.community.entity.GithubUser;
import com.bjq.community.mapper.UserMapper;
import com.bjq.community.model.User;
import com.bjq.community.provider.GithubProvider;
import com.bjq.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Credted by
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect_uri}")
    private String redirectUri;



    @Autowired
    private UserService userService;

    @GetMapping("callback")
    public String callback(@RequestParam("code")String code,
                           @RequestParam("state")String state,
                           HttpServletResponse response){
        AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
        accessTokenEntity.setClient_id(clientId);
        accessTokenEntity.setClient_secret(clientSecret);
        accessTokenEntity.setCode(code);
        accessTokenEntity.setRedirect_uri(redirectUri);
        accessTokenEntity.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenEntity);
        GithubUser githubUser = githubProvider.githubUser(accessToken);
        if(githubUser!=null && githubUser.getId()!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();//以token代替session
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatar_url());
            userService.createOrUpdate(user);
            //userMapper.insert(user);//登陆成功把信息存入数据库中
            response.addCookie(new Cookie("token",token));//写入cookie
            return "redirect:/";//redirect重定向的是一个url而不是一个页面,切记切记;不要写成返回index
        }else {
            //登陆失败
            return "redirect:/";
        }

    }

    @GetMapping("/logout")
    public String Logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }


}
