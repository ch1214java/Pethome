package cn.xinhang.org.service;

import cn.xinhang.basic.service.IBaseService;
import cn.xinhang.org.domain.Department;

import java.util.List;

public interface IDepartmentService extends IBaseService<Department> {
    /**
     * 获取父部门树
     * @return
     */
    List<Department> queryDeptTree();
}
