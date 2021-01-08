package cn.xinhang.org.service.impl;

import cn.xinhang.basic.service.impl.BaseServiceImpl;
import cn.xinhang.basic.util.PageList;
import cn.xinhang.org.domain.Shop;
import cn.xinhang.org.mapper.ShopMapper;
import cn.xinhang.org.query.ShopQuery;
import cn.xinhang.org.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopServiceImpl extends BaseServiceImpl<Shop> implements IShopService {

    @Autowired
    ShopMapper ShopMapper;
}
