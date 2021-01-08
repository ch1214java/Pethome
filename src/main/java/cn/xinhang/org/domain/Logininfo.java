package cn.xinhang.org.domain;

import cn.xinhang.basic.domain.BaseDomain;
import lombok.Data;

@Data
public class Logininfo extends BaseDomain {
    private String username;
    private String phone;
    private String email;
    private String salt;
    private String password;
    private Byte type;
    private Byte disable;

}
