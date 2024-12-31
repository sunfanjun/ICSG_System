package com.zjut.springbootprj.service.impl;

import com.github.pagehelper.PageHelper;
import com.zjut.springbootprj.dao.AccountMapper;
import com.zjut.springbootprj.dao.DangerMapper;
import com.zjut.springbootprj.dao.SecurityGuardMapper;
import com.zjut.springbootprj.pojo.Account;
import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.pojo.Monitor;
import com.zjut.springbootprj.pojo.SecurityGuard;
import com.zjut.springbootprj.pojo.dto.SecurityGuardDto;
import com.zjut.springbootprj.pojo.dto.SecurityGuardPageDto;
import com.zjut.springbootprj.service.SecurityGuardService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 保安的业务处理实现类
 */
@Service
public class SecurityGuardServiceImpl implements SecurityGuardService {
    @Resource
    private SecurityGuardMapper securityGuardMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private DangerMapper dangerMapper;

    /**
     * 根据多个id批量删除保安
     *
     * @param ids 保安id
     * @return 判断是否删除成功
     */
    @Override
    @Transactional
    public Boolean deleteSecurityGuards(List<Integer> ids) {
        try {
            List<Integer> accountIdList = new ArrayList<>();
            for (Integer id : ids) {
                accountIdList.add(accountMapper.queryByPid(id));
                dangerMapper.updateByPid(id);
                accountMapper.updateByPid(id);
            }
            accountMapper.deleteByIds(accountIdList);
            securityGuardMapper.deleteByIds(ids);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 修改保安
     *
     * @param securityGuard 修改的保安实体
     * @return 判断是否修改成功
     */
    @Override
    @Transactional
    public Boolean updateSecurityGuard(SecurityGuard securityGuard) {
        try {
            securityGuardMapper.update(securityGuard);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查询保安信息
     *
     * @param securityGuardPageDto 保安实体
     * @return 查找到的保安列表
     */
    @Override
    public List<SecurityGuard> findSecurityGuard(SecurityGuardPageDto securityGuardPageDto) {
        PageHelper.startPage(securityGuardPageDto.getPageNum(), securityGuardPageDto.getPageSize());
        try {
            List<SecurityGuard> securityGuardList = securityGuardMapper.queryDto(securityGuardPageDto.getSecurityGuardDto());
            return securityGuardList;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 新增保安
     *
     * @param securityGuard 保安实体
     * @return 判断新增是否成功
     */
    @Override
    public Boolean saveSecurityGuard(SecurityGuard securityGuard) {
        try {
            securityGuardMapper.save(securityGuard);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
