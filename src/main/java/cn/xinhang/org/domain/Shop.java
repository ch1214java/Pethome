package cn.xinhang.org.domain;

import cn.xinhang.basic.domain.BaseDomain;
import lombok.Data;

import java.util.Date;

@Data
public class Shop extends BaseDomain {
    private String name;
    private String tel;
    private Date registerTime;
    private Integer state;
    private String address;
    private String logo;
    private Long admin_id;
}
