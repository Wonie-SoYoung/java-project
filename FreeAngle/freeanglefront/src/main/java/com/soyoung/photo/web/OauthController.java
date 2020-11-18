package com.soyoung.photo.web;


import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.soyoung.photo.entity.User;
import com.soyoung.photo.service.MessagesService;
import com.soyoung.photo.service.OauthService;
import com.soyoung.photo.service.UserService;
import com.soyoung.photo.util.Dict;
import com.soyoung.photo.util.Note;
import com.soyoung.photo.util.UploadingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * <p>
 * 登录表 前端控制器
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-04
 */
@Controller
@RequestMapping("/oauth")
public class OauthController {
    @Autowired
    private OauthService oauthService;
    @Autowired
    private UserService userService;
    @Autowired
    private UploadingUtil uploadingUtil;
    @Autowired
    private MessagesService messagesService;

    //获取验证码
    @PostMapping("/getCode/{phone}")
    @ResponseBody
    public String getCode(@PathVariable("phone") String phone, HttpServletRequest request) {
        String codeError = "0";
        try {
            Map<String, String> stringMap = Note.getCode(phone);
            codeError = stringMap.get("result");
            request.getSession().setAttribute("note", stringMap.get("note"));
            request.getSession().setAttribute("phone", phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codeError;
    }

    //手机验证码登录
    @PostMapping("/phoneLogin")
    public String phoneLogin(String phone, String note, HttpServletRequest request) {
        String error = "";
        String verifyPhone = request.getSession().getAttribute("phone") == null ? null : request.getSession().getAttribute("phone").toString();
        String verifyNote = request.getSession().getAttribute("note") == null ? null : request.getSession().getAttribute("note").toString();
        if (StringUtils.isEmpty(verifyPhone) || StringUtils.isEmpty(verifyNote)) {
            error = "请输入手机号并获取验证码！";
            request.setAttribute("error", error);
            return "login";
        } else if (!verifyPhone.equals(phone) || !verifyNote.equals(note)) {
            error = "手机号或验证码不正确！";
            request.setAttribute("error", error);
            return "login";
        } else {
            //获取登录用户Id
            com.soyoung.photo.entity.Oauth oauth =oauthService.getPhoneUserId(phone);
            Integer uid = oauth.getUid();
            if (uid == null || uid == 0) {
                error = "请先进行注册！";
                request.setAttribute("error", error);
                return "login";
            }
            //根据Id获取用户
            User user = userService.getUserById(uid);
            if(user.getSeal()!=0){
                error = "该账号存在不正当行为!已被管理员🈲封:"+user.getSeal()+"天";
                request.setAttribute("error", error);
                return "login";
            }
            userService.updateLogins(uid);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("oauth",oauth);
            return "redirect:/production/home";
        }
    }

    //手机密码登录方式
    @PostMapping("/phoneBypwdLogin")
    public String phoneBypwdLogin(String phone, String password, HttpServletRequest request) {
        String error = "";
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            error = "请输入手机号和密码！";
            request.setAttribute("error", error);
            return "login";
        } else {
            //获取登录用户Id
            com.soyoung.photo.entity.Oauth oauth=oauthService.getPhoneUserBypwd(phone, password);
            if (oauth == null) {
                error = "账户或密码不正确！";
                request.setAttribute("error", error);
                return "login";
            }
            Integer uid = oauth.getUid();
            //根据Id获取用户
            User user = userService.getUserById(uid);
            if(user.getSeal()!=0){
                error = "该账号存在不正当行为!已被管理员🈲封:"+user.getSeal()+"天";
                request.setAttribute("error", error);
                return "login";
            }
            userService.updateLogins(uid);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("oauth",oauth);
            return "redirect:/production/home";
        }
    }

    //跳转页面
    @RequestMapping("/qqlogin")
    public void qqLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        try {
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
    }

    //获取登录者的基础信息
    @RequestMapping("/qq")
    public String QQAfterlogin(HttpServletRequest request) throws Exception {
        Enumeration<String> parameterNames = request.getParameterNames();
        try {
            // 获取AccessToken(AccessToken用于获取OppendID)
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            // 用于接收AccessToken
            String accessToken = null,
                    openID = null;
            Long tokenExpireIn = 0L; // AccessToken有效时长
            if (accessTokenObj.getAccessToken().equals("")) {
                //      我们的网站被CSRF攻击了或者用户取消了授权
                //      做一些数据统计工作
                System.out.print("没有获取到响应参数");
                return "/";
            } else {
                accessToken = accessTokenObj.getAccessToken();  // 获取AccessToken
                tokenExpireIn = (accessTokenObj.getExpireIn())/86400;   //获取时间
                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj = new OpenID(accessToken);
                // 通过对象获取[OpendId]（OpendID用于获取QQ登录用户的信息）
                openID = openIDObj.getUserOpenID();
                com.soyoung.photo.entity.Oauth oauth=oauthService.getQQOauth(openID);
                if(oauth==null){
                    // 通过OpenID获取QQ用户登录信息对象(Oppen_ID代表着QQ用户的唯一标识)
                    UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                    // 获取用户信息对象(只获取nickename与Gender)
                    UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                    if (userInfoBean.getRet() == 0) {
                        //oss上传图像
                        String oladURL=UploadingUtil.addFileURL(userInfoBean.getAvatar().getAvatarURL100())+".jpg";
                        String imgURl=System.getProperty("user.dir")+"\\File\\"+oladURL;
                        uploadingUtil.uploadNullFile(new File(imgURl), Dict.OBJECTFILEUSERBEAD+oladURL);
                        //添加User数据
                        User user=new User();
                        user.setUserName(userInfoBean.getNickname());
                        user.setSex(userInfoBean.getGender());
                        user.setHeadURL(Dict.OBJECTFILEUSERBEAD+oladURL);
                        user.setStateid(1);
                        userService.addUser(user);
                        //封装Oauth数据
                        com.soyoung.photo.entity.Oauth addoauth=new com.soyoung.photo.entity.Oauth();
                        addoauth.setUid(user.getId());
                        addoauth.setOatype(Dict.QQLOGIN);
                        addoauth.setUuid(openID);
                        addoauth.setAccessToken(accessToken);
                        addoauth.setExpiredTime(tokenExpireIn.intValue());
                        oauthService.addOauth(addoauth);
                        //封装Session
                        request.getSession().setAttribute("user",user);
                        userService.updateLogins(user.getId());
                        return "redirect:/production/home";
                    } else {
                        System.out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
                        return "/";
                    }
                }else{
                    User user=userService.getUserById(oauth.getUid());
                    if(user.getSeal()!=0){
                        String error = "该账号存在不正当行为!已被管理员🈲封:"+user.getSeal()+"天";
                        request.setAttribute("error", error);
                        return "login";
                    }
                    userService.updateLogins(oauth.getUid());
                    //封装Session
                    request.getSession().setAttribute("user",user);
                    request.getSession().setAttribute("oauth",oauth);
                    return "redirect:/production/home";
                }
            }
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return "/";
    }

    //进入修改密码页面
    @GetMapping("/updatepwd")
    public String getdaupdatepwdHtml(){
        return "alterpwd";
    }

    //修改密码
    @PostMapping("/updatepwd")
    public String getupdatepwd(String oldpwd,String newonepwd,String newtwopwd,HttpServletRequest request){
        Map map=new HashMap();
        com.soyoung.photo.entity.Oauth oauth=((com.soyoung.photo.entity.Oauth)request.getSession().getAttribute("oauth"));
        if(oauth.getPassword()!=null && oauth.getPassword().equals(oldpwd)){
            if(newonepwd.equals(newtwopwd)){
                if(oauthService.updatepwd(oauth.getId(),newonepwd)>0){
                    request.getSession().removeAttribute("user");
                    request.getSession().removeAttribute("oauth");
                    return "redirect:/login";
                }else{
                    map.put("1","修改失败!");
                }
            }else{
                map.put("3","两次密码不正确！");
            }
        }else{
            map.put("1","密码错误");
        }
        request.setAttribute("error",map);
        request.setAttribute("oldpwd",oldpwd);
        request.setAttribute("newonepwd",newonepwd);
        request.setAttribute("newtwopwd",newtwopwd);
        return "alterpwd";
    }

    //退出登录
    @GetMapping("/break")
    public String getBreak(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("oauth");
        return "redirect:/login";
    }

    //注册登录
    @PostMapping("/register")
    public String getregister(String uuid,String code,String password,HttpServletRequest request){
        String error = "";
        String verifyPhone = request.getSession().getAttribute("phone") == null ? null : request.getSession().getAttribute("phone").toString();
        String verifyNote = request.getSession().getAttribute("note") == null ? null : request.getSession().getAttribute("note").toString();
        if (StringUtils.isEmpty(verifyPhone) || StringUtils.isEmpty(verifyNote)) {
            error = "请输入手机号并获取验证码！";
            request.setAttribute("error", error);
            return "register";
        } else if (!verifyPhone.equals(uuid) || !verifyNote.equals(code)) {
            error = "手机号或验证码不正确！";
            request.setAttribute("error", error);
            return "register";
        } else if(oauthService.getIsPhone(uuid)){
            error = "手机号已存在！";
            request.setAttribute("error", error);
            return "register";
        }else {
            //创建用户
            User user=new User();
            user.setUserName(uuid);
            user.setSex("男");
            user.setStateid(1);
            user.setHeadURL(Dict.DEFAULTHEADURL);
            user.setBackgroundURL(Dict.DEFAULTBACKURL);
            //创建登录
            com.soyoung.photo.entity.Oauth oauth=new com.soyoung.photo.entity.Oauth();
            oauth.setOatype(Dict.PHONELOGIN);
            oauth.setUuid(uuid);
            oauth.setPassword(password);
            //保存
            if(userService.insert(user)){
                oauth.setUid(user.getId());
                oauthService.insert(oauth);
            }
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("oauth",oauth);
            userService.updateLogins(user.getId());
            return "redirect:/user/getalert";
        }
    }
}
