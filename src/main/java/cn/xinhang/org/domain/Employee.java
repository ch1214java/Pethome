package cn.xinhang.org.domain;

import cn.xinhang.basic.domain.BaseDomain;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Employee extends BaseDomain {
    private String username;
    private String email;
    private String phone;
    //加密盐值
    private String salt;
    private String password;
    private Integer age;
    private Integer state;
    private Department department;

    private Long department_id;
    private Long logininfo_id;
    private Logininfo logininfo;

    private Long shop_id;
    private Shop shop;

}
