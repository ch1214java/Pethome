package cn.xinhang.basic.mapper;
import cn.xinhang.basic.query.BaseQuery;

import java.util.List;

/**
 * 泛型：在编译的时候不指定类型
 * 在使用的时候指定操作的对象
 * @param <T>
 */
public interface BaseMapper<T> {
    /**
     * 保存数据
     * @param t
     */
    void save(T t);

    /**
     * 更新数据
     * @param t
     */
    void update(T t);

    /**
     * 删除数据
     * @param id
     */
    void remove(Long id);

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
    List<T> loadAll();

    /**
     * 查询数据总条数
     * @param baseQuery
     * @return
     */
    Integer queryCount(BaseQuery baseQuery);

    /**
     * 查询分页数据
     * @param baseQuery
     * @return
     */
    List<T> queryDate(BaseQuery baseQuery);
    /**
     * 批量删除
     * @param ids
     */
    void patchDel(List<Long> ids);

}
