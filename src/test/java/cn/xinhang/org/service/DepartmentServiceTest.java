package cn.xinhang.org.service;

import cn.xinhang.basic.util.PageList;
import cn.xinhang.org.domain.Department;
import cn.xinhang.org.query.DepartmentQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class DepartmentServiceTest extends BaseTest {

    @Autowired
    IDepartmentService departmentService;

    @Test
    public void add() {
        Department department = departmentService.loadById(45L);
        department.setId(null);
        departmentService.add(department);
    }

    @Test
    public void update() {
        Department department = departmentService.loadById(45L);
        department.setName(department.getName()+"48数据");
        departmentService.update(department);
    }

    @Test
    public void del() {
        departmentService.del(45L);
    }

    @Test
    public void loadById() {
        System.out.println(departmentService.loadById(44L));
    }

    @Test
    public void getall() {
        departmentService.getall().forEach(a->{
            System.out.println(a);
        });
    }

    @Test
    public void queryPage() {
        PageList<Department> pageList = departmentService.queryPage(new DepartmentQuery());
        System.out.println(pageList.getTotal());
        pageList.getRows().forEach(a->{
            System.out.println(a);
        });
    }
}