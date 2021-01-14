package cn.xinhang.org.domain;

import cn.xinhang.basic.domain.BaseDomain;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Employee extends BaseDomain {
    private Long id;
    private String username;
    private String email;
    private String phone;
    //加密盐值
    private String salt;
    private String password;
    private Integer age;
    //状态：0表示正常 -1表示禁用 1表示待审核 2表示待激活
    private Integer state = 1;
    private Department department;

    private Long department_id;
    private Long logininfo_id;
    private Logininfo logininfo;

    private Long shop_id;
    private Shop shop;

}
