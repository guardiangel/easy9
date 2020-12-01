package org.colin.controller;

import org.colin.OaApplication;
import org.colin.mapper.SysUserMapper;
import org.colin.model.dto.UserPhotoTemp;
import org.colin.model.vo.req.UserAddReqVO;
import org.colin.service.UserService;
import org.colin.utils.Tool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @ClassName: UserControllerTest
 * @Author: wujiangbo
 * @Date: 2020/7/22 0022 10:17
 * @Version: 1.1.0
 */
@RunWith(SpringJUnit4ClassRunner.class) // 随着工厂环境的运行
@SpringBootTest(classes = OaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerTest {

    @Resource
    private UserService userService;

    @Resource
    private SysUserMapper sysUserMapper;


    @Test
    public void add() {
        //新增测试用户数据
        UserAddReqVO vo = new UserAddReqVO();
        List<String> roleIds = new ArrayList<String>();
        roleIds.add("2d56198c-d14b-4d02-a625-7559815b62fb");//角色
        for(int i=11; i<=20; i++){
            String name = "王五" + i + "号";
            vo.setUsername("wangwu" + i);
            vo.setMilitaryService("0");//是否服军役(0:否;1:是;)
            vo.setGraduationSchool("武汉工业职业技术学院");
            vo.setIdentity("1005");//1001:党员;1002:预备党员;1005:群众
            vo.setBloodType("1003");//1002:A;1003:B;1004:O
            vo.setHeight("175");//身高
            vo.setWeight("82");//体重
            vo.setMajor("1001");//1001、1002、1004、1007、1008
            vo.setMarriage("1001");//1001:已婚;1002:未婚
            vo.setDeptId("202007221324157887439");//部门
            vo.setSex("1");//性别(1:男;2:女;)
            vo.setPhone("170" + Tool.getRandomNum(8));
            vo.setEducation("105");//本科107;大专105

            vo.setPassword("123456");
            vo.setCreateWhere(1);
            vo.setEmail(Tool.getRandomNum(12) + "@qq.com");
            vo.setRealName(name);
            vo.setNickName(name);
            vo.setStatus(1);
            vo.setRoleIds(roleIds);
            vo.setWagesNumber("001" + Tool.getRandomNum(4) + Tool.getRandomNum(4) + Tool.getRandomNum(4));
            vo.setSocialNumber("002" + Tool.getRandomNum(4) + Tool.getRandomNum(4) + Tool.getRandomNum(4));
            vo.setProvidentFundNumber("003" + Tool.getRandomNum(4) + Tool.getRandomNum(4) + Tool.getRandomNum(4));
            vo.setNation("101");//汉族
            vo.setCountry("101");//中国
            vo.setHomeAddress("湖北省武汉市洪山区关山大道关谷新世界小区15栋2单元" + i + "号");
            vo.setLiveAddress("湖北省武汉市洪山区关山大道关谷新世界小区15栋2单元" + i + "号");
            vo.setIdNumber(Tool.getRandomNum(18));
            vo.setQq(Tool.getRandomNum(10));
            vo.setWebchat(Tool.getRandomNum(11));
            vo.setMsn(Tool.getRandomNum(13));
            userService.addUser(vo, "fcf34b56-a7a2-4719-9236-867495e74c31");
        }
    }

    @Test
    public void addOne() {
        //新增测试用户数据
        UserAddReqVO vo = new UserAddReqVO();
        List<String> roleIds = new ArrayList<String>();
        roleIds.add("2d56198c-d14b-4d02-a625-7559815b62fb");//角色(普通用户角色)
        //张三、李四、王五、刘三妹、张笑笑、彭丽丽
        String name = "彭丽丽";
        vo.setUsername("penglili");
        vo.setPassword("penglili123456");
        vo.setSex("2");//性别(1:男;2:女;)
        vo.setMilitaryService("1");//是否服军役(0:否;1:是;)
        vo.setGraduationSchool("宇宙理工大学");
        vo.setIdentity("1001");//1001:党员;1002:预备党员;1005:群众
        vo.setBloodType("1004");//1002:A;1003:B;1004:O
        vo.setHeight("168");//身高
        vo.setWeight("54");//体重
        vo.setMajor("1004");//1001、1002、1004、1007、1008
        vo.setMarriage("1001");//1001:已婚;1002:未婚
        vo.setDeptId("202006301429312201997");//部门（202006301429312201997-北方社区）
        vo.setPhone("185" + Tool.getRandomNum(8));
        vo.setEducation("105");//本科107;大专105
        vo.setCreateWhere(1);
        vo.setEmail(Tool.getRandomNum(10) + "@qq.com");
        vo.setRealName(name);
        vo.setNickName(name);
        vo.setStatus(1);
        vo.setRoleIds(roleIds);
        vo.setWagesNumber("001" + Tool.getRandomNum(4) + Tool.getRandomNum(4) + Tool.getRandomNum(4));
        vo.setSocialNumber("002" + Tool.getRandomNum(4) + Tool.getRandomNum(4) + Tool.getRandomNum(4));
        vo.setProvidentFundNumber("003" + Tool.getRandomNum(4) + Tool.getRandomNum(4) + Tool.getRandomNum(4));
        vo.setNation("101");//汉族
        vo.setCountry("101");//中国
        vo.setHomeAddress("湖北省武汉市洪山区关山大道关谷新世界小区15栋2单元9999号");
        vo.setLiveAddress("湖北省武汉市洪山区关山大道关谷新世界小区15栋2单元9999号");
        vo.setIdNumber(Tool.getRandomNum(18));
        vo.setQq(Tool.getRandomNum(10));
        vo.setWebchat(Tool.getRandomNum(11));
        vo.setMsn(Tool.getRandomNum(13));
        vo.setMonthSalary(new BigDecimal("7000"));
        userService.addUser(vo, "fcf34b56-a7a2-4719-9236-867495e74c31");
    }

    @Test
    public void addUserPhotoTemp() {
        String userPhotoPath = "D:/Easy9/userPhoto";
        File file = new File(userPhotoPath);
        String[] fileList = file.list();
        for(int i=0; i<fileList.length; i++){
            UserPhotoTemp temp = new UserPhotoTemp();
            String newFilePath = userPhotoPath + "/" + fileList[i];
            temp.setId(Tool.getPrimaryKey());
            temp.setUserPhoto(Tool.ImageToBase64(newFilePath));
            sysUserMapper.batchInsertUserPhotoTemp(temp);
        }
    }

}
