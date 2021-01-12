package cn.xinhang.user.controller;

import cn.xinhang.basic.util.AjaxResult;
import cn.xinhang.user.domain.dto.UserDto;
import cn.xinhang.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lr")
public class LoginAndRegisterController {

    @Autowired
    private IUserService userService;

    @GetMapping("/validatePhone/{type}/{phone}")
    public AjaxResult validatePhone(@PathVariable("type")String type, @PathVariable("phone")String phone){
        return userService.validatePhone(type, phone);
    }

    @GetMapping("/sendMobileCode/{type}/{phone}")
    public AjaxResult sendMobileCode(@PathVariable("type")String type, @PathVariable("phone")String phone){
        return userService.sendMobileCode(type,phone);
    }

    @PutMapping("/phoneReg")
    public AjaxResult phoneReg(@RequestBody UserDto userDto){
        return userService.phoneReg(userDto);
    }
}
