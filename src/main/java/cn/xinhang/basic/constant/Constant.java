package cn.xinhang.basic.constant;

public class Constant {
    /**
     * 通过手机验证码注册
     */
    public static final String PHONE_REG = "phone_reg";
    /**
     * 通过手机验证码登录
     */
    public static final String PHONE_LOGIN = "phone_login";
    /**
     * 通过邮箱注册
     */
    public static final String EMAIL_REG = "email_reg";

    /**
     * SMS短信平台相关常量
     */
    public static final String SMS_UID = "chenhong_nb";
    public static final String SMS_KEY = "d41d8cd98f00b204e980";

    /**
     * 数据状态：正常
     */
    public static final Integer STATE_NORMAL = 0;
    /**
     * 数据状态：待审核
     */
    public static final Integer STATE_AUDIT = 1;
    /**
     * 数据状态：待激活
     */
    public static final Integer STATE_ACTIVE = 2;
    /**
     * 数据状态：禁用
     */
    public static final Integer STATE_DISABLED = -1;
    /**
     * 前台用户登录
     */
    public static final String FRONT = "front";
    /**
     * 后台用户登录
     */
    public static final String ADMIN = "admin";
    /**
     * 当前登录用户在Redis中的key
     */
    public static final String KEY_OF_LOGINUSER = "loginUser";
    /**
     * Redis中存贮登录用户对象的过期时间 30分钟
     */
    public static final Integer EXPIRE_TIME_IN_REDIS = 30 * 60;

}
