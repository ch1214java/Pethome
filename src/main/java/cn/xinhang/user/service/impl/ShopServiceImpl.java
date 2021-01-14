package cn.xinhang.user.service.impl;

import cn.xinhang.basic.constant.Constant;
import cn.xinhang.basic.service.impl.BaseServiceImpl;
import cn.xinhang.basic.util.AjaxResult;
import cn.xinhang.basic.util.MailUtils;
import cn.xinhang.basic.util.StrUtils;
import cn.xinhang.org.domain.Employee;
import cn.xinhang.org.domain.Shop;
import cn.xinhang.org.mapper.EmployeeMapper;
import cn.xinhang.org.mapper.ShopMapper;
import cn.xinhang.user.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class ShopServiceImpl extends BaseServiceImpl<Shop> implements IShopService {

    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private MailUtils mailUtils;

    @Override
    public AjaxResult settledIn(Shop shop) {
        //1.先保存t_employee表的数据，返回主键，因为t_shop表中有一个admin_id外键列 关联员工表的主键
        Employee admin = shop.getAdmin();
        admin.setSalt(StrUtils.getComplexRandomString(32));
        //密码加密
        admin.setPassword(admin.getPassword() + admin.getSalt());
        employeeMapper.save(admin);

        //2.保存t_shop表的数据，返回主键
        shopMapper.save(shop);

        //3.修改员工表的shop_id列的值
        admin.setShop_id(shop.getId());
        employeeMapper.update(admin);
        return AjaxResult.me();
    }

    /**
     * 平台工作人员：入驻审核
     * @param id 店铺id
     * @param type 审核操作类型：1表示拒绝 2表示驳回 3表示审核通过
     */
    @Override
    @Transactional
    public void shopAudit(Long id, Integer type) {
        //1.根据id查询店铺对象
        Shop shop = shopMapper.loadById(id);
        switch (type){
            case 1:break;
            case 2:break;
            case 3:
                //2.修改店铺的状态为2，  待激活
                shop.setState(Constant.STATE_ACTIVE);
                shopMapper.update(shop);

                //3.修改店铺关联的员工对象的状态为2  待激活
                Employee admin = shop.getAdmin();
                admin.setState(Constant.STATE_ACTIVE);
                employeeMapper.update(admin);

                //4.发送激活邮件    vualliuswaspcdia
                String content = "<h2 style='color:red;'>恭喜你成功入驻宠物之家平台！</h2><br/><br/> " +
                        "点击以下链接即可激活！<br/><br/> " +
                        "<a href='http://localhost:8080/lr/shopRegActive/"+id+"'>点击激活</a>";
                mailUtils.send("1633105493@qq.com","入驻激活",content);
                System.out.println(shop.getName() + "的审核操作完成！");
                break;
        }
    }

    /**
     * 激活店铺 state为0（正常）
     * @param id
     */
    @Override
    @Transactional
    public void shopRegActive(Long id) {
        //1.根据id查询店铺对象
        Shop shop = shopMapper.loadById(id);
        //2.修改店铺的状态为0，  激活
        shop.setState(Constant.STATE_NORMAL);
        shopMapper.update(shop);

        //3.修改店铺关联的员工对象的状态为0  激活
        Employee admin = shop.getAdmin();
        admin.setState(Constant.STATE_NORMAL);
        employeeMapper.update(admin);
        System.out.println(shop.getName()+"state:"+shop.getState() + "的审核操作完成！");
    }
}
