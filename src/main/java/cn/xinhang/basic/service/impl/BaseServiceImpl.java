package cn.xinhang.basic.service.impl;

import cn.xinhang.basic.mapper.BaseMapper;
import cn.xinhang.basic.query.BaseQuery;
import cn.xinhang.basic.service.IBaseService;
import cn.xinhang.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * BaseService不需要交给Spring容器管理
 * 是用来给子类继承的
 */

@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    BaseMapper<T> mapper;

    @Transactional
    @Override
    public void add(T t) {
        mapper.save(t);
    }

    @Transactional
    @Override
    public void update(T t) {
        mapper.update(t);
    }

    @Transactional
    @Override
    public void del(Long id) {
        mapper.remove(id);
    }

    @Override
    public T loadById(Long id) {
        return loadById(id);
    }

    @Override
    public List<T> getall() {
        return mapper.loadAll();
    }

    @Override
    public PageList<T> queryPage(BaseQuery baseQuery) {
        /*查询总条数*/
        Integer queryCount = mapper.queryCount(baseQuery);
        /*查询数据*/
        List<T> date = mapper.queryDate(baseQuery);
        return new PageList<>(queryCount,date);
    }

    @Override
    public void patchDel(List<Long> ids) {
        mapper.patchDel(ids);
    }
}
