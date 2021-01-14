package cn.xinhang.user.domain.dto;

import cn.xinhang.user.domain.User;
import lombok.Data;

@Data
public class UserDto extends User {
    private String verifyCode;
    //phone_reg表示手机验证码注册，email_reg表示邮箱注册
    //front  表示前台用户登录     admin  表示后台用户登录
    private String type;
}
