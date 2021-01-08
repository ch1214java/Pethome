package cn.xinhang.org.domain;

import cn.xinhang.basic.domain.BaseDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
@ToString
public class Department extends BaseDomain {
    /*部门编号*/
    private String sn;
    /*部门名称*/
    private String name;
    /*暂时不用*/
    private String dirPath;
    /*部门状态 0 正常 ，-1 停用*/
    @ApiModelProperty(value = "状态",name = "state")
    private Integer state;
    /*引用对象*/
    /*部门经理 和员工关联*/
    private Employee manager;
    private Long parent_id;
    /*上级部门*/
    private Department parent;
    private List<Department> children = new ArrayList<>();

}
