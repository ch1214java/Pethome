package cn.xinhang.user.service.impl;

import cn.xinhang.basic.constant.Constant;
import cn.xinhang.basic.service.impl.BaseServiceImpl;
import cn.xinhang.basic.util.*;
import cn.xinhang.user.domain.User;
import cn.xinhang.user.domain.dto.UserDto;
import cn.xinhang.user.mapper.UserMapper;
import cn.xinhang.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Struct;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public AjaxResult validatePhone(String type, String phone) {
        User user = userMapper.findByPhone(phone);
        if (Constant.PHONE_REG.equals(type)){
            if(user != null){
                //手机号被注册过，被占用
                return AjaxResult.me().setSuccess("该手机已被占用！");
            }
        }else if(Constant.PHONE_LOGIN.equals(type)){
            //手机验证码登录
            if(user == null){
                //手机号未被注册过
                return AjaxResult.me();
            }
        }
        return AjaxResult.me();
    }

    @Override
    public AjaxResult sendMobileCode(String type, String phone) {
        //1.随机生成一个验证码
        String verifyCode = StrUtils.getRandomString(6);
        //2.先从Redis中获取一下
        String value = RedisUtils.INSTANCE.get(type + "-" + phone);

        long currentTime = System.currentTimeMillis();
        //3.发送验证码
        //Redis中有储存的验证码和事件毫秒  格式：验证码-时间毫秒
        if(StringUtils.hasText(value)){
            //验证码
            verifyCode = value.split("-")[0];
            //上次获取的验证码的时间毫秒
            Long lastTime = Long.valueOf(value.split("-")[1]);
            //两次操作不超过1分钟，
            if (currentTime - lastTime <= 60*1000){
                return AjaxResult.me().setSuccess("亲，您的操作过于频繁！");
            }
            //两次操作不超过5分钟，
            else if (currentTime - lastTime <= 5*60*1000  ){
                //刷新过期时间5分钟
                RedisUtils.INSTANCE.set(type+"-"+phone, verifyCode+"-"+currentTime, 300);
                //3.调用短信平台接口，发送短信
                String content = "【宠物之家】您注册账户的验证码为：" + verifyCode + "，请在5分钟内完成操作！";
                SmsUtils.send(phone, content);
                System.out.println(content);
            }


        }else{//超过5分钟，或者就是第一次获取
            //2.保存到Redis中，并且设置5分钟过期  key: phone_reg-18996157300  value：验证码-时间毫秒
            RedisUtils.INSTANCE.set(type+"-"+phone, verifyCode+"-"+currentTime, 300);
            //3.调用短信平台接口，发送短信
            String content = "【宠物之家】您注册账户的验证码为：" + verifyCode + "，请在5分钟内完成操作！";
            //SmsUtils.send(phone, content);
            System.out.println(content);
        }
        //4.给前端返回消息
        return AjaxResult.me().setSuccess("短信验证码已发送，请在5分钟内完成操作！");
    }

    @Transactional
    public AjaxResult phoneReg(UserDto userDto) {
        if(Constant.PHONE_REG.equals(userDto.getType())){
            //手机验证码注册
            //先从Redis中获取一下
            String value = RedisUtils.INSTANCE.get(userDto.getType()+"-"+userDto.getPhone());
            //能够从redis中获取到验证码
            if(StringUtils.hasText(value)){
                //验证码
                String verifyCode = value.split("-")[0];
                if(verifyCode.equals(userDto.getVerifyCode())){
                    //验证码正确，将数据保存到t_user表中  save方法需要一个User对象，但是传递的是User的子类对象
                    userDto.setUsername(userDto.getPhone());
                    userDto.setSalt(StrUtils.getComplexRandomString(32));   //盐值
                    userDto.setPassword(MD5Utils.encrypByMd5(userDto.getPassword()+userDto.getSalt()));
                    userMapper.save(userDto);
                    return AjaxResult.me();
                }
            }
            return AjaxResult.me().setSuccess("验证码错误！");
        }else if(Constant.EMAIL_REG.equals(userDto.getType())){
            //邮箱注册
            //......自己实现
        }
        return AjaxResult.me().setSuccess("注册失败");
    }
}
