package org.colin.service.impl;

import org.colin.mapper.yw.BbsUserMapper;
import org.colin.model.ro.BbsUser;
import org.colin.service.BbsUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author : wujiangbo(QQ:1134135987)
 * @version : 1.1.0.1
 * @description : TODO
 * @date : 2020-10-21 15:10:23
 */
@Service
public class BbsUserServiceImpl implements BbsUserService {

    @Resource
    private BbsUserMapper bbsUserMapper;

    @Override
    public void addBbsUser(BbsUser user) {
        bbsUserMapper.insertSelective(user);
    }
}
