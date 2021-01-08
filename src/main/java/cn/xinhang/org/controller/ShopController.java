package cn.xinhang.org.controller;

import cn.xinhang.basic.util.AjaxResult;
import cn.xinhang.basic.util.PageList;
import cn.xinhang.org.domain.Shop;
import cn.xinhang.org.query.ShopQuery;
import cn.xinhang.org.service.IShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*编写 门店业务接口
* 1.返回类型
* 2.接口做什么事情
* 3.需要什么差参数
* 4.
* */
/*
@RequestBody  表单格式使用的是json的话，后端接收必须用@RequestBody（获取请求体中的数据）转换json
@RequestMapping
@RequestParam                                  @RequestParam（获取请求头中的数据）
* */

/*@GetMapping
@PostMapping
@PutMapping
@DeleteMapping*/
@RestController
@RequestMapping("/shop")
@Api(tags = " 门店接口",description = " 门店接口详细描述")
public class ShopController {

    @Autowired
    IShopService shopService;

    /**
     * 查询一条数据
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation(value = "需要 门店id",notes = "查询 门店指定id数据")
    public Shop getById(@PathVariable("id") Long id){
        return shopService.loadById(id);
    }

    /**
     * 添加或更新
     * @param shop
     */
    @PutMapping
    @ApiOperation(value = "添加/更新",notes = "添加数据（id=null 更新指定id的数据）")
    public AjaxResult addOrUpdate(@RequestBody Shop shop){
        try {
            if (shop.getId() == null){
                shopService.add(shop);
            }else {
                shopService.update(shop);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me()
                    .setSuccess("系统繁忙请稍后在试！！！");
        }
    }

    /**
     * 删除数据
     * @param id
     */
    @DeleteMapping("{id}")
    @ApiOperation(value = "删除数据",notes = "删除指定id数据")
    public AjaxResult delById(@PathVariable("id") Long id){
        try {
            shopService.del(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me()
                    .setSuccess("系统繁忙请稍后在试！！！");
        }
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询所有数据",notes = "查询所有数据")
    public List<Shop> getAll(){
        return shopService.getall();
    }

    /**
     * 通过@RequestBody来获取请求体中的数据
     * @param shopQuery
     * @return
     */
    @PostMapping
    @ApiOperation(value = "分页查询数据",notes = "分页查询数据")
    public PageList<Shop> getPage(@RequestBody ShopQuery shopQuery){
        return shopService.queryPage(shopQuery);
    }

    /**
     * json数组
     * 可以用list和数组接受
     * @return
     */
    @PatchMapping
    @ApiOperation(value = "批量删除",notes = "批量删除id")
    public AjaxResult bulkDel(@RequestBody List<Long> ids){
        try {
            shopService.patchDel(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess("系统繁忙请稍后在试！！！"+e.getMessage());
        }
    }
}
