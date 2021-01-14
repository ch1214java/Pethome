package cn.xinhang.user.controller;

import cn.xinhang.basic.util.AjaxResult;
import cn.xinhang.org.domain.Shop;
import cn.xinhang.user.service.IShopService;
import cn.xinhang.user.domain.dto.UserDto;
import cn.xinhang.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.UserDataHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/lr")
public class LoginAndRegisterController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IShopService shopService;


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

    @PostMapping("/settledIn")
    public AjaxResult settledIn(@RequestBody Shop shop){
        try {
            return shopService.settledIn(shop);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess("入驻失败!!!" + e.getMessage());
        }
    }

    /**
     * 激活店铺 state状态为0（正常）
     * @param id
     * @return
     */
    @GetMapping("/shopRegActive/{id}")
    public AjaxResult shopRegActive(@PathVariable("id") Long id,HttpServletResponse resp){
        try {
            shopService.shopRegActive(id);
            resp.sendRedirect("http://localhost:8081/#/login");
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess("激活失败！"+e.getMessage());
        }
    }

    /**
     * 普通用户登录
     * @param userDto  type属性为front表示前台用户登录，admin表示后台用户登录
     * @return
     */
    @PostMapping("/userLogin")
    public AjaxResult userLogin(@RequestBody UserDto userDto, HttpSession session){
        return userService.userLogin(userDto,session);
    }
}
