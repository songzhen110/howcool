package com.songzhen.howcool.auth;

import com.songzhen.howcool.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义Controller
     * 返回值：true表示继续流程（如调用下一个拦截器或处理器）；false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        // 判断对象是否是映射到一个方法，如果不是则直接通过
        if (!(object instanceof HandlerMethod)) {
            // instanceof运算符是用来在运行时指出对象是否是特定类的一个实例
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查方法是否有NeedLogin注解，无则跳过认证
        if (method.isAnnotationPresent(NeedLogin.class)) {
            NeedLogin needLogin = method.getAnnotation(NeedLogin.class);
            if (needLogin.required()) {
                // 从HTTP请求头中获取TOKEN信息
                String token = httpServletRequest.getHeader("Authorization");

                // HTTP请求头中TOKEN解析出的用户信息
                String uid = JwtUtil.getUid(token);
                String userName = JwtUtil.getUserName(token);
                String realName = JwtUtil.getRealName(token);
                // 检查TOKEN
                boolean available = checkToken(token);

                if (available) {
                    // 组装用户信息到REQUEST中
                    Map<String, Object> currentUser = new HashMap<>(16);
                    currentUser.put("uid", uid);
                    currentUser.put("userName", userName);
                    currentUser.put("realName", realName);
                    httpServletRequest.setAttribute("currentUser", currentUser);
                }

                return true;
            }
        }
        return true;
    }

    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    /**
     * 检查TOKEN
     *
     * @param token token
     *              要校验的token
     * @return boolean
     * true:通过 false:失败
     */
    private boolean checkToken(String token) {
        // ------------------------认证------------开始-----------------
        if (null == token) {
            return false;
        }

        // 获取TOKEN中的用户信息
        String uid = JwtUtil.getUid(token);

        // 根据uid从redis中获取用户tokenInRedis
        String tokenInRedis = redisTemplate.opsForValue().get(uid);
        if (null == tokenInRedis) {
            // 如果REDIS异常，返回成功保证正常处理业务可以继续处理
            return true;
        }

        // HTTP请求头中TOKEN解析出的用户信息
        String userName = JwtUtil.getUserName(token);
        String realName = JwtUtil.getRealName(token);
        String deviceId = JwtUtil.getDeviceId(token);
        long expireIn = JwtUtil.getExpireIn(token);
        // REDIS服务器中TOKEN解析出的用户信息
        String userNameInRedis = JwtUtil.getUserName(tokenInRedis);
        String realNameInRedis = JwtUtil.getRealName(tokenInRedis);
        String deviceIdInRedis = JwtUtil.getDeviceId(tokenInRedis);
        long expireInInRedis = JwtUtil.getExpireIn(tokenInRedis);

        if (null == userName || null == realName || null == deviceId) {
            return false;
        }
        if (null == userNameInRedis || null == realNameInRedis || null == deviceIdInRedis) {
            return false;
        }
        // 判断TOKEN是否过期
        if (expireIn != expireInInRedis) {
            return false;
        }
        if (expireIn < System.currentTimeMillis()) {
            return false;
        }
        // ------------------------认证------------结束-----------------
        return true;
    }

}