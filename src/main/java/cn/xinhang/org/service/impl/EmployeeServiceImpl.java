package cn.xinhang.org.service.impl;

import cn.xinhang.basic.service.impl.BaseServiceImpl;
import cn.xinhang.basic.util.PageList;
import cn.xinhang.org.domain.Employee;
import cn.xinhang.org.mapper.EmployeeMapper;
import cn.xinhang.org.query.EmployeeQuery;
import cn.xinhang.org.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

}
