package cn.xinhang.org.mapper;

import cn.xinhang.basic.mapper.BaseMapper;
import cn.xinhang.org.domain.Department;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<Department> {





    /**
     * 查询部门数据树
     * @return
     */
    List<Department> loadDeptTree();
}
