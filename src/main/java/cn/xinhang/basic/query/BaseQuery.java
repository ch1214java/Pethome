package cn.xinhang.basic.query;

import lombok.Data;

@Data
public class BaseQuery {
    private Integer currentPage = 1;
    private Integer pageSize = 5;
    private String keyword;

    public Integer getStart(){
        return (this.currentPage - 1) *this.pageSize;
    }
}
