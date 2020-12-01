package org.colin.constants;

/**
 * @Description: 常量类
 * @ClassName: Constant
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 10:15
 * @Version: 1.1.0
 */
public class Constant {

    public static final String CHANGE_LINE = "\r\n";

    public static final String SYSTEM_CHARACTER_ENCODING = "UTF-8";// 系统编码格式

    /**
     * 正常token
     */
    public static final String ACCESS_TOKEN = "authorization";

    /**
     * 刷新token
     */
    public static final String REFRESH_TOKEN = "refresh_token";

    /**
     * 创建时间
     */
    public static final String CREATED = "created";

    /**
     * 权限key
     */
    public static final String JWT_PERMISSIONS_KEY = "jwt-permissions-key";

    /**
     * 用户名称 key
     */
    public static final String JWT_USER_NAME = "jwt-user-name-key";

    /**
     * 角色key
     */
    public static final String JWT_ROLES_KEY = "jwt-roles-key:";

    /**
     * 主动去刷新 token key(适用场景 比如修改了用户的角色/权限去刷新token)
     */
    public static final String JWT_REFRESH_KEY = "jwt-refresh-key:";

    /**
     * 刷新状态(适用场景如：一个功能点要一次性请求多个接口，当第一个请求刷新接口时候 加入一个节点下一个接口请求进来的时候直接放行)
     */
    public static final String JWT_REFRESH_STATUS = "jwt-refresh-status:";

    /**
     * 标记新的access_token
     */
    public static final String JWT_REFRESH_IDENTIFICATION = "jwt-refresh-identification:";

    /**
     * access_token 主动退出后加入黑名单 key
     */
    public static final String JWT_ACCESS_TOKEN_BLACKLIST = "jwt-access-token-blacklist:";

    /**
     * refresh_token 主动退出后加入黑名单 key
     */
    public static final String JWT_REFRESH_TOKEN_BLACKLIST = "jwt-refresh-token-blacklist:";

    /**
     * 组织机构编码key
     */
    public static final String DEPT_CODE_KEY = "dept-code-key:";

    /**
     * 菜单权限编码key
     */
    public static final String PERMISSION_CODE_KEY = "permission-code-key:";

    /**
     * 标记用户是否已经删除
     */
    public static final String DELETED_USER_KEY = "deleted-user-key:";

    /**
     * 标记用户是否已经被锁定
     */
    public static final String ACCOUNT_LOCK_KEY = "account-lock-key:";

    /**
     * 用户权鉴缓存 key
     */
    public static final String IDENTIFY_CACHE_KEY = "shiro-cache:org.colin.shiro.CustomRealm.authorizationCache:";

    /**
     * 登录图片验证码图片存Redis的key前缀
     */
    public static final String PICTURE_VERIFICATION = "pictureVerification:";

    // 北方社区
    public static final String PICTURE_VERIFICATION_NORTH = "pictureVerification_north:";

    // 注册验证码的KEY
    public static final String REGISTER_EMAIL_CODE = "pictureVerification_register_code:";

    // 修改登录密码验证码的KEY
    public static final String UPDATE_PWD_EMAIL_CODE = "pictureVerification_updatePwd_code:";

    // 更新用户信息的KEY
    public static final String UPDATE_USER_INFO = "update_user_info_key:";

    // 更新用户密码信息的KEY
    public static final String UPDATE_USER_PWD_INFO = "update_userPwd_info_key:";

    // 更新大头照的KEY
    public static final String UPDATE_USER_PHOTO = "update_user_photo_key:";

    /**
     * 登录方式（1：Web；2：H5；3：Android；4：IOS；；5：其他；）
     */
    public static final int LOGIN_WAY_1 = 1;

    public static final int LOGIN_WAY_2 = 2;

    public static final int LOGIN_WAY_3 = 3;

    public static final int LOGIN_WAY_4 = 4;

    public static final int LOGIN_WAY_5 = 5;

    /**
     * Base64图片数据的头信息
     */
    public static final String BASE64_HEAD = "data:img/jpg;base64,";

    //控制提交意见反馈次数时，存redis的key前缀
    public static final String USER_SUBMIT_SUGGESTION_REDIS_KEY = "insertSuggestion_";
}
