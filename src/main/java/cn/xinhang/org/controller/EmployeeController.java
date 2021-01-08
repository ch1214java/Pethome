package cn.xinhang.org.controller;

import cn.xinhang.basic.util.AjaxResult;
import cn.xinhang.basic.util.PageList;
import cn.xinhang.org.domain.Employee;
import cn.xinhang.org.query.EmployeeQuery;
import cn.xinhang.org.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*编写 员工业务接口
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
@RequestMapping("/employee")
@Api(tags = " 员工接口",description = " 员工接口详细描述")
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    /**
     * 查询一条数据
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation(value = "需要 员工id",notes = "查询 员工指定id数据")
    public Employee getById(@PathVariable("id") Long id){
        return employeeService.loadById(id);
    }

    /**
     * 添加或更新
     * @param employee
     */
    @PutMapping
    @ApiOperation(value = "添加/更新",notes = "添加数据（id=null 更新指定id的数据）")
    public AjaxResult addOrUpdate(@RequestBody Employee employee){
        try {
            if (employee.getId() == null){
                employeeService.add(employee);
            }else {
                employeeService.update(employee);
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
            employeeService.del(id);
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
    public List<Employee> getAll(){
        return employeeService.getall();
    }

    /**
     * 通过@RequestBody来获取请求体中的数据
     * @param employeeQuery
     * @return
     */
    @PostMapping
    @ApiOperation(value = "分页查询数据",notes = "分页查询数据")
    public PageList<Employee> getPage(@RequestBody EmployeeQuery employeeQuery){
        return employeeService.queryPage(employeeQuery);
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
            employeeService.patchDel(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess("系统繁忙请稍后在试！！！"+e.getMessage());
        }
    }
}
