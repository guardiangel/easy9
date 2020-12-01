package org.colin.service;

import org.colin.model.entity.SysDept;
import org.colin.model.entity.SysUser;
import org.colin.model.vo.req.DeptAddReqVO;
import org.colin.model.vo.req.DeptPageReqVO;
import org.colin.model.vo.req.DeptUpdateReqVO;
import org.colin.model.vo.req.UserPageUserByDeptReqVO;
import org.colin.model.vo.resp.DeptRespNodeVO;
import org.colin.model.vo.resp.PageVO;

import java.util.List;
public interface DeptService {

    //根据主键ID获取部门信息
    public SysDept detailInfo(String id);

    public List<DeptRespNodeVO> deptTreeList(String deptId);

    public List<SysDept> selectAll();

    public PageVO<SysUser> pageDeptUserInfo(UserPageUserByDeptReqVO vo);

    public SysDept addDept(DeptAddReqVO vo);

    public void deleted(String id);

    public void updateDept(DeptUpdateReqVO vo);

    public PageVO<SysDept> pageInfo(DeptPageReqVO vo);
}
