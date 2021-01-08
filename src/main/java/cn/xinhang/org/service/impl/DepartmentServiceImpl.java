package cn.xinhang.org.service.impl;

import cn.xinhang.basic.service.impl.BaseServiceImpl;
import cn.xinhang.org.domain.Department;
import cn.xinhang.org.mapper.DepartmentMapper;
import cn.xinhang.org.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {

    @Autowired
    //测试
    DepartmentMapper departmentMapper;

    @Override//父类的add方法用不了，自己实现业务逻辑
    public void add(Department department) {
        departmentMapper.save(department);
        this.update(department);
        //先通过parentID 查询出父亲的dirpath
        //在拼接上自己
    }
    @Override
    public void update(Department department) {
        //如果新增的是顶级部门，直接拼接上自身ID就可以了
        if(department.getParent() == null){
            department.setDirPath("/"+department.getParent().getId()+"/"+department.getId());
        }else{
            //通过父亲 ID查询好处父亲的dirpath
            String pdirPath = departmentMapper.loadById(department.getParent().getId()).getDirPath();
            department.setDirPath(pdirPath+"/"+department.getId());
        }
        departmentMapper.update(department);
    }

    @Override
    public List<Department> queryDeptTree() {
        return departmentMapper.loadDeptTree();
    }
}
