package cn.xinhang.org.service;

import cn.xinhang.basic.service.IBaseService;
import cn.xinhang.basic.util.PageList;
import cn.xinhang.org.domain.Department;
import cn.xinhang.org.query.DepartmentQuery;

import java.util.List;

public interface IDepartmentService extends IBaseService<Department> {
    /**
     * 获取父部门树
     * @return
     */
    List<Department> queryDeptTree();
}
