package cn.xinhang.org.service.impl;

import cn.xinhang.basic.service.impl.BaseServiceImpl;
import cn.xinhang.org.domain.Employee;
import cn.xinhang.org.mapper.EmployeeMapper;
import cn.xinhang.org.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

}
