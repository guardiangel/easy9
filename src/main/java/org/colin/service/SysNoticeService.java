package org.colin.service;

import org.colin.model.ro.SysNotice;
import org.colin.model.vo.resp.SysNoticeRespVO;
import java.util.List;

public interface SysNoticeService {

    //新增
    void add(SysNotice obj);

    //删除
    void deleteById(String id);

    //根据类型type查询详情
    SysNoticeRespVO getNoticeByType(String type);

    //根据主键ID查询详情
    SysNoticeRespVO getNoticeById(String id);

    //查询所有
    List<SysNoticeRespVO> selectAll();

    //修改
    void updateObj(SysNotice obj);
}
