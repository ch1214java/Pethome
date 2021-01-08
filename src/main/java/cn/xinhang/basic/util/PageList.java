package cn.xinhang.basic.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageList<T> {
    /*总条数*/
    private Integer total = 0;
    /*页面展示的数据*/
    private List<T> rows = new ArrayList<>();

    public PageList() {
    }

    public PageList(Integer total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
