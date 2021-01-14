package cn.xinhang.user.service;

import cn.xinhang.basic.service.IBaseService;
import cn.xinhang.basic.util.AjaxResult;
import cn.xinhang.org.domain.Shop;

public interface IShopService extends IBaseService<Shop> {

    AjaxResult settledIn(Shop shop);

    void shopAudit(Long id, Integer type);

    void shopRegActive(Long id);
}
