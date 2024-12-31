package com.zjut.springbootprj.service;

import com.zjut.springbootprj.pojo.SecurityGuard;
import com.zjut.springbootprj.pojo.dto.SecurityGuardDto;
import com.zjut.springbootprj.pojo.dto.SecurityGuardPageDto;

import java.util.List;

/**
 * 保安的业务处理接口类
 */
public interface SecurityGuardService {
    /**
     * 根据多个id批量删除保安
     *
     * @param ids 保安id
     * @return 判断是否删除成功
     */
    Boolean deleteSecurityGuards(List<Integer> ids);

    /**
     * 修改保安
     *
     * @param securityGuard 修改的保安实体
     * @return 判断是否修改成功
     */
    Boolean updateSecurityGuard(SecurityGuard securityGuard);

    /**
     * 查询保安信息
     *
     * @param securityGuardPageDto 保安实体
     * @return 查找到的保安列表
     */
    List<SecurityGuard> findSecurityGuard(SecurityGuardPageDto securityGuardPageDto);

    /**
     * 新增保安
     *
     * @param securityGuard 保安实体
     * @return 判断新增是否成功
     */
    Boolean saveSecurityGuard(SecurityGuard securityGuard);

}
