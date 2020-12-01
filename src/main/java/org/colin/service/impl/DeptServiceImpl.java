package org.colin.service.impl;

import com.github.pagehelper.PageHelper;
import org.colin.code.BaseResponseCode;
import org.colin.constants.Constant;
import org.colin.exception.ServiceException;
import org.colin.mapper.SysDeptMapper;
import org.colin.model.entity.SysDept;
import org.colin.model.entity.SysUser;
import org.colin.model.vo.req.DeptAddReqVO;
import org.colin.model.vo.req.DeptPageReqVO;
import org.colin.model.vo.req.DeptUpdateReqVO;
import org.colin.model.vo.req.UserPageUserByDeptReqVO;
import org.colin.model.vo.resp.DeptRespNodeVO;
import org.colin.model.vo.resp.PageVO;
import org.colin.service.DeptService;
import org.colin.service.RedisService;
import org.colin.service.UserService;
import org.colin.utils.CodeUtil;
import org.colin.utils.PageUtils;
import org.colin.utils.Tool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @Description: DeptServiceImpl
 * @ClassName: DeptServiceImpl
 * @Author: wujiangbo(QQ : 1134135987)
 * @Date: 2020/2/17 20:19
 * @Version: 1.1.0
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Override
    public SysDept detailInfo(String id) {
        return sysDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DeptRespNodeVO> deptTreeList(String deptId) {
        List<SysDept> list;
        if (Tool.isBlank(deptId)) {
            list = sysDeptMapper.selectAll();
        } else {
            SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);
            if (sysDept == null) {
                throw new ServiceException(BaseResponseCode.DATA_ERROR);
            }
            List<String> childIds = sysDeptMapper.selectChildIds(sysDept.getRelationCode());
            list = sysDeptMapper.selectAllByNotContainChild(childIds);
        }
        //默认加一个顶级方便新增顶级部门
        DeptRespNodeVO respNodeVO = new DeptRespNodeVO();
        respNodeVO.setTitle("默认顶级部门");
        respNodeVO.setId("0");
        respNodeVO.setSpread(true);
        respNodeVO.setChildren(getTree(list));
        List<DeptRespNodeVO> result = new ArrayList<>();
        result.add(respNodeVO);
        return result;
    }

    private List<DeptRespNodeVO> getTree(List<SysDept> all) {
        List<DeptRespNodeVO> list = new ArrayList<>();
        for (SysDept sysDept : all) {
            if (sysDept.getPid().equals("0")) {
                DeptRespNodeVO deptTree = new DeptRespNodeVO();
                BeanUtils.copyProperties(sysDept, deptTree);
                deptTree.setTitle(sysDept.getName());
                deptTree.setSpread(true);
                deptTree.setChildren(getChild(sysDept.getId(), all));
                list.add(deptTree);
            }
        }
        return list;
    }

    private List<DeptRespNodeVO> getChild(String id, List<SysDept> all) {
        List<DeptRespNodeVO> list = new ArrayList<>();
        for (SysDept sysDept : all) {
            if (sysDept.getPid().equals(id)) {
                DeptRespNodeVO deptTree = new DeptRespNodeVO();
                BeanUtils.copyProperties(sysDept, deptTree);
                deptTree.setTitle(sysDept.getName());
                deptTree.setChildren(getChild(sysDept.getId(), all));
                list.add(deptTree);
            }
        }
        return list;
    }

    @Override
    public List<SysDept> selectAll() {
        List<SysDept> list = sysDeptMapper.selectAll();
        for (SysDept sysDept : list) {
            SysDept parent = sysDeptMapper.selectByPrimaryKey(sysDept.getPid());
            if (parent != null) {
                sysDept.setPidName(parent.getName());
            }
        }
        return list;
    }

    @Override
    public PageVO<SysUser> pageDeptUserInfo(UserPageUserByDeptReqVO vo) {
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(vo.getDeptId());
        if (null == sysDept) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
        List<String> strings = sysDeptMapper.selectChildIds(sysDept.getRelationCode());
        return userService.selectUserInfoByDeptIds(vo.getPageNum(), vo.getPageSize(), strings);
    }

    @Override
    public SysDept addDept(DeptAddReqVO vo) {
        String relationCode;
//        long result = redisService.incrby(Constant.DEPT_CODE_KEY, 1);
//        String deptCode = CodeUtil.deptCode(String.valueOf(result), 6, "0");
        String deptCode = CodeUtil.deptCode(Tool.getRandomNum(5), 6, "0");
        SysDept parent = sysDeptMapper.selectByPrimaryKey(vo.getPid());
        if (vo.getPid().equals("0")) {
            relationCode = deptCode;
        } else if (null == parent) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        } else {
            relationCode = parent.getRelationCode() + deptCode;
        }
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(vo, sysDept);
        sysDept.setCreateTime(new Date());
        sysDept.setId(Tool.getPrimaryKey());
        sysDept.setDeptNo(deptCode);
        sysDept.setRelationCode(relationCode);
        int count = sysDeptMapper.insertSelective(sysDept);
        if (count != 1) {
            throw new ServiceException(BaseResponseCode.OPERATION_ERROR);
        }
        return sysDept;
    }

    @Override
    public void deleted(String id) {
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(id);
        if (null == sysDept) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
        List<String> deptIds = sysDeptMapper.selectChildIds(sysDept.getRelationCode());
        List<SysUser> list = userService.getUserListByDeptIds(deptIds);
        if (!list.isEmpty()) {
            throw new ServiceException(BaseResponseCode.NOT_PERMISSION_DELETED_DEPT);
        }
        sysDept.setDeleted(0);
        sysDept.setUpdateTime(new Date());
        int count = sysDeptMapper.updateByPrimaryKeySelective(sysDept);
        if (count != 1) {
            throw new ServiceException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDept(DeptUpdateReqVO vo) {
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(vo.getId());
        if (null == sysDept) {
            throw new ServiceException(BaseResponseCode.DATA_ERROR);
        }
        SysDept update = new SysDept();
        BeanUtils.copyProperties(vo, update);
        update.setUpdateTime(new Date());
        int count = sysDeptMapper.updateByPrimaryKeySelective(update);
        if (count != 1) {
            throw new ServiceException(BaseResponseCode.OPERATION_ERROR);
        }
        /**
         * 说明层级发生了变化
         */
        if (!StringUtils.isEmpty(vo.getPid()) && !vo.getPid().equals(sysDept.getPid())) {
            SysDept parent = sysDeptMapper.selectByPrimaryKey(vo.getPid());
            if (!vo.getPid().equals("0") && null == parent) {
                throw new ServiceException(BaseResponseCode.DATA_ERROR);
            }
            SysDept oldParent = sysDeptMapper.selectByPrimaryKey(sysDept.getPid());
            String oldRelationCode;
            String newRelationCode;
            /**
             * 根目录降到其他目录
             */
            if (sysDept.getPid().equals("0")) {
                oldRelationCode = sysDept.getDeptNo();
                newRelationCode = parent.getRelationCode() + sysDept.getDeptNo();
            } else if (vo.getPid().equals("0")) {//其他目录升级到跟目录
                oldRelationCode = sysDept.getRelationCode();
                newRelationCode = sysDept.getDeptNo();
            } else {
                oldRelationCode = oldParent.getRelationCode();
                newRelationCode = parent.getRelationCode();
            }
            sysDeptMapper.updateRelationCode(oldRelationCode, newRelationCode, sysDept.getRelationCode());
        }
    }

    @Override
    public PageVO<SysDept> pageInfo(DeptPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<SysDept> sysDepts = sysDeptMapper.selectAll();
        return PageUtils.getPageVO(sysDepts);
    }
}
