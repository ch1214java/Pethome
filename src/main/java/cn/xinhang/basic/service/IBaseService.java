package cn.xinhang.basic.service;

import cn.xinhang.basic.query.BaseQuery;
import cn.xinhang.basic.util.PageList;

import java.util.List;

public interface IBaseService<T> {
    /**
     * 添加数据
     * @param t
     */
    void add(T t);

    /**
     * 更新数据
     * @param t
     */
    void update(T t);

    /**
     * 删除数据
     * @param id
     */
    void del(Long id);

    /**
     * 查询一条数据
     * @param id
     * @return
     */
    T loadById(Long id);

    /**
     * 查询所有数据
     * @return
     */
    List<T> getall();
    /**
     * 分页查询
     * @param baseQuery
     * @return
     */
    PageList<T> queryPage(BaseQuery baseQuery);

    /**
     * 批量删除
     * @param ids
     */
    void patchDel(List<Long> ids);

}
