package cn.xinhang.user.service;

import cn.xinhang.basic.service.IBaseService;
import cn.xinhang.basic.util.AjaxResult;
import cn.xinhang.user.domain.User;
import cn.xinhang.user.domain.dto.UserDto;

public interface IUserService extends IBaseService<User> {

    AjaxResult validatePhone(String type, String phone);

    AjaxResult sendMobileCode(String type, String phone);

    AjaxResult phoneReg(UserDto userDto);
}
