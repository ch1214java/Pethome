package cn.xinhang.org.controller;

import cn.xinhang.basic.util.AjaxResult;
import cn.xinhang.basic.util.PageList;
import cn.xinhang.org.domain.Department;
import cn.xinhang.org.query.DepartmentQuery;
import cn.xinhang.org.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*编写部门业务接口
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
@RequestMapping("/dept")
@Api(tags = "部门接口",description = "部门接口详细描述")
public class DepartmentController {

    @Autowired
    IDepartmentService departmentService;

    /**
     * 查询一条数据
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation(value = "需要部门id",notes = "查询部门指定id数据")
    public Department getById(@PathVariable("id") Long id){
        return departmentService.loadById(id);
    }

    /**
     * 添加或更新
     * @param department
     */
    @PutMapping
    @ApiOperation(value = "添加/更新",notes = "添加数据（id=null 更新指定id的数据）")
    public AjaxResult addOrUpdate(@RequestBody Department department){
        try {
            if (department.getId() == null){
                departmentService.add(department);
            }else {
                departmentService.update(department);
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
            departmentService.del(id);
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
    public List<Department> getAll(){
        return departmentService.getall();
    }

    /**
     * 获取数据树的所有部门数据
     * @return
     */
    @GetMapping("/deptTree")
    @ApiOperation(value = "查询上级部门",notes = "查询上级部门数据")
    public List<Department> deptTree(){
        return departmentService.queryDeptTree();
    }
    /**
     * 通过@RequestBody来获取请求体中的数据
     * @param departmentQuery
     * @return
     */
    @PostMapping
    @ApiOperation(value = "分页查询数据",notes = "分页查询数据")
    public PageList<Department> getPage(@RequestBody DepartmentQuery departmentQuery){
        return departmentService.queryPage(departmentQuery);
    }

    /**
     * json数组
     * 可以用list和数组接受
     * @return
     */
    @PatchMapping
    @ApiOperation(value = "批量删除",notes = "批量删除id")
    public AjaxResult patchRemove(@RequestBody List<Long> ids){
        try {
            departmentService.patchDel(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess("系统繁忙请稍后在试！！！"+e.getMessage());
        }
    }
}
