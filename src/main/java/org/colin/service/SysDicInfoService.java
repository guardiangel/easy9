package org.colin.service;

import org.colin.model.ro.SysDicInfo;
import org.colin.model.vo.resp.DicSelectInfoVO;
import org.colin.model.vo.resp.SysDicInfoVO;

import java.util.List;

public interface SysDicInfoService {

    List<DicSelectInfoVO> selectDicByTypeCode();

    List<SysDicInfoVO> getAllDicByTypeCode(String typeCode);

    List<SysDicInfoVO> getAllOKDicByTypeCode(String typeCode);

    void add(SysDicInfo obj, String userId);

    void deleteById(String id);

    void update(SysDicInfo obj, String userId);
}
