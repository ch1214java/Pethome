package cn.xinhang.basic.config;

import cn.xinhang.basic.constant.Constant;
import cn.xinhang.basic.util.AjaxResult;
import cn.xinhang.basic.util.RedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截
 * 为何一个类实现一个接口后，没有重写任何抽象方法，也不会报错？？？？
 * jdk8新特性：default去修饰这个借口
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    //实现接口中的方法：Alt + Shift + P

    /**
     * 在controller方法之前进行拦截【判断是否已登录】
     * @param request   请求
     * @param response  响应
     * @param handler   处理器
     * @return          返回true表示放行，返回false表示已拦截
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*//1.从session中获取当前登录用户对象
        Object session = request.getSession().getAttribute(Constant.KEY_OF_LOGINUSER);
        //2。判断对象是否为空
        if(session != null){
            return true;
        }*/
        //1.先从请求头中获取userToken
        String userToken = request.getHeader("userToken");

        //2.判断
        if(StringUtils.hasText(userToken)){
            String loginUserJsonStr = RedisUtils.INSTANCE.get(userToken);
            if(StringUtils.hasText(loginUserJsonStr)){
                //刷新Redis中用户信息的过期时间魏30分钟
                RedisUtils.INSTANCE.set(userToken,loginUserJsonStr,Constant.EXPIRE_TIME_IN_REDIS);
                //一登录，直接放放心
                return true;
            }
        }
        //3.未登录时，给出响应提示
        AjaxResult noUser = AjaxResult.me().setSuccess("noUser");
        //4.将AjaxResult对象转换为json字符串格式输出：
        /**
         * 下面这一堆代码：就是SpringMVC的@ResponseBody注解的底层实现代码
         */
        //利用jackson工具：
        ObjectMapper mapper=new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(noUser);
        //利用响应对象的输出流输出这个json字符串
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(jsonStr);
        response.getWriter().flush();
        response.getWriter().close();
        return false;
    }
}
