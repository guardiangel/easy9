package org.colin.service.impl;

import com.github.pagehelper.PageHelper;
import org.colin.mapper.YwCbfqMapper;
import org.colin.model.entity.YwCbfq;
import org.colin.model.vo.resp.PageVO;
import org.colin.service.YwCbfqService;
import org.colin.utils.PageUtils;
import org.colin.utils.Tool;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author : wujiangbo(QQ:1134135987)
 * @version : 1.1.0.1
 * @description : TODO
 * @date : 2020-09-22 08:31:14
 */
@Service
public class YwCbfqServiceImpl implements YwCbfqService {

    @Resource
    private YwCbfqMapper ywCbfqMapper;

    @Override
    public void addObject(YwCbfq obj) {
        obj.setId(Tool.getPrimaryKey());
        obj.setCjsj(new Date());
        ywCbfqMapper.insertSelective(obj);
    }

    @Override
    public void deleteObject(String id) {
        ywCbfqMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageVO<YwCbfq> selectPageInfo(YwCbfq obj) {
        PageHelper.startPage(obj.getPageNum(), obj.getPageSize());
        List<YwCbfq> objList = ywCbfqMapper.selectAll(obj);
        return PageUtils.getPageVO(objList);
    }

    @Override
    public YwCbfq selectById(String id) {
        return ywCbfqMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateObj(YwCbfq obj) {
        ywCbfqMapper.updateByPrimaryKeySelective(obj);
    }
}
