package cn.xinhang.org.domain;

import cn.xinhang.basic.domain.BaseDomain;
import lombok.Data;

import java.util.Date;

@Data
public class Shop extends BaseDomain {
    private Long id;
    private String name;
    private String tel;
    private Date registerTime = new Date();
    //状态：0表示正常 -1表示禁用 1表示待审核 2表示待激活
    private Integer state = 1;
    private String address;
    private String logo;
    private Long admin_id;
    //商家用户管理员
    private Employee admin;
}
