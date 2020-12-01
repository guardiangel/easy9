package org.colin.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.colin.aop.annotation.CheckLicense;
import org.colin.code.BaseResponseCode;
import org.colin.utils.DbBackup;
import org.colin.utils.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 返回菜单对应的视图HTML，有少部分需要返回页面生成的数据在这里
 * @ClassName: IndexController
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 11:33
 * @Version: 1.1.0
 */
@Api(tags = "视图", value = "负责返回视图")
@Controller
@RequestMapping("/index")
@Slf4j
public class IndexController {


    @Value("${system.sql_path}")
    private String sql_path;

    @Value("${system.mysql_db_restore}")
    private String mysql_db_restore;

    //财务报表
    @GetMapping("/cwbb")
    public String cwbb() {
        return "cw/cwbb";
    }

    //进销存管理-库存管理
    @GetMapping("/jxcStore")
    public String jxcStore() {
        return "jxc/store";
    }

    //进销存管理-商品出库管理
    @GetMapping("/goodsOut")
    public String goodsOut() {
        return "jxc/goodsOut";
    }

    //进销存管理-商品入库管理
    @GetMapping("/goodsIn")
    public String goodsIn() {
        return "jxc/goodsIn";
    }

    //进销存管理-商品管理
    @GetMapping("/jxcGoods")
    public String jxcGoods() {
        return "jxc/goods";
    }

    //进销存管理-仓库管理
    @GetMapping("/wareHouse")
    public String wareHouse() {
        return "jxc/wareHouse";
    }

    //消息发布页面
    @GetMapping("/publishNotice")
    public String publishNotice() {
        return "ifreamPage/publishNotice";
    }

    //消息发布
    @GetMapping("/noticeManager")
    public String noticeManager() {
        return "sys/SysNotice";
    }

    //系统参数管理页面
    @GetMapping("/sysConfig")
    public String sysConfig() {
        return "sys/SysConfig";
    }

    //个人中心-个人信息
    @GetMapping("/userInfoDetail")
    public String userInfoDetail() {
        return "userCenter/userInfoDetail";
    }

    //个人中心-审核中心
    @GetMapping("/checkCenter")
    public String checkCenter() {
        return "userCenter/check_center";
    }

    //个人中心-流程申请
    @GetMapping("/flwRequest")
    public String flw_request() {
        return "userCenter/flw_request";
    }

    @GetMapping("/userEcharts")
    public String echartsTest() {
        return "echarts/userAnalyze";
    }

    //license测试
    @CheckLicense
    @GetMapping("/license")
    public String licenseTest() {
        return "login";
    }

    //行政管理-工资管理
    @GetMapping("/salary")
    public String salaryList() {
        return "xzgl/salaryList";
    }

    //平台-意见反馈管理页面
    @GetMapping("/suggestionList")
    public String suggestionList() {
        return "message/suggestionList";
    }

    //平台-跳转到意见反馈页面
    @GetMapping("/addSuggestion")
    public String addSuggestion() {
        return "message/addSuggestion";
    }

    //跳转至评论管理页面
    @GetMapping("/reply")
    public String reply() {
        return "bbs/reply";
    }

    //跳转至帖子管理页面
    @GetMapping("/post")
    public String post() {
        return "bbs/post";
    }

    //跳转至数据字典类型页面
    @GetMapping("/table")
    public String table() {
        return "table/table";
    }

    //跳转至数据字典类型页面
    @GetMapping("/dictionary")
    public String dicType() {
        return "dictionary/dictionary";
    }

    //跳转至群聊页面
    @GetMapping("/goChat")
    public String goChat() {
        return "chat/chat";
    }

    //查询订单页面
    @GetMapping("/order")
    public String order() {
        return "buy/order";
    }

    //商品管理
    @GetMapping("/goods")
    public String goods() {
        return "buy/goods";
    }

    //商家管理order
    @GetMapping("/store")
    public String store() {
        return "buy/store";
    }

    //社区管理
    @GetMapping("/community")
    public String community() {
        return "buy/community";
    }

    //购买页面
    @GetMapping("/buy")
    public String buy() {
        return "buy/buy";
    }

    //跳转到账号密码登录页面
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //跳转到人脸登录页面
    @GetMapping("/faceLogin")
    public String faceLogin() {
        return "faceLogin";
    }

    //进入首页
    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        return "home";
    }

    //更改密码页面
    @GetMapping("/users/updatePassword")
    public String updatePassword() {
        return "users/updatePassword";
    }

    //用户编辑个人信息
    @GetMapping("/users/info")
    public String userDetail(Model model) {
        return "users/user_edit";
    }

    //菜单权限列表
    @GetMapping("/menus")
    public String menusList() {
        return "menus/menu_list";
    }

    //角色列表
    @GetMapping("/roles")
    public String roleList() {
        return "roles/role_list";
    }

    //用户列表
    @GetMapping("/users")
    public String userList() {
        return "users/user_list";
    }

    //系统操作日志
    @GetMapping("/logs")
    public String logList() {
        return "logs/log_list";
    }

    //登录日志
    @GetMapping("/loginLog")
    public String loginLogList() {
        return "logs/loginLog_list";
    }

    //系统消息
    @GetMapping("/message")
    public String messageList() {
        return "message/message_list";
    }

    //组织机构列表
    @GetMapping("/depts")
    public String deptList() {
        return "depts/dept_list";
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

    @GetMapping("/404")
    public String error404() {
        return "error/404";
    }

    @GetMapping("/500")
    public String error405() {
        return "error/500";
    }

    @GetMapping("/main")
    public String indexHome() {
        return "main";
    }

    //关于我们
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    //还原数据库
    @GetMapping("/resetDB")
    public void resetDB(HttpServletResponse response) {
        log.info("手动还原数据库开始");
        try {
            DbBackup.restoreMysqlDB(sql_path, mysql_db_restore);
        } catch (Exception e) {
            log.info("手动还原数据库，发生异常：" + e.getLocalizedMessage());
        }
        log.info("手动还原数据库结束--------还原成功");
        Tool.responseMessage(response, BaseResponseCode.DB_RESET_SUCCESS);
    }
}
