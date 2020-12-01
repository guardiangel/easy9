package org.colin.service;

import org.colin.model.vo.req.UserRoleOperationReqVO;

import java.util.List;
public interface UserRoleService {

    List<String> getRoleIdsByUserId(String userId);

    public int removeByUserId(String userId);

    public void addUserRoleInfo(UserRoleOperationReqVO vo);

    public int removeByRoleId(String roleId);

    public List<String> getUserIdsByRoleIds(List<String> roleIds);
}
