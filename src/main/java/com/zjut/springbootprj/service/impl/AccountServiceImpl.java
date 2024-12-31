package com.zjut.springbootprj.service.impl;

import com.zjut.springbootprj.dao.AccountMapper;
import com.zjut.springbootprj.pojo.Account;
import com.zjut.springbootprj.pojo.dto.AccountDto;
import com.zjut.springbootprj.service.AccountService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * 账号的业务处理实现类
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    /**
     * 根据多个id批量删除账号
     *
     * @param ids 账号id
     * @return 判断是否删除成功
     */
    @Override
    public Boolean deleteAccounts(List<Integer> ids) {
        try {
            accountMapper.deleteByIds(ids);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 修改账号
     *
     * @param account 修改的账号实体
     * @return 判断是否修改成功
     */
    @Override
    public Boolean updateAccount(Account account) {
        try {
            accountMapper.update(account);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查询账号信息
     *
     * @param id 账号id
     * @return 查找到的账号列表
     */
    @Override
    public Account findAccountById(Integer id) {
        try{
            Account account = accountMapper.queryById(id);
            return account;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 新增账号
     *
     * @param account 账号实体
     * @return 判断新增是否成功
     */
    @Override
    public Boolean saveAccount(Account account) {
        try {
            accountMapper.save(account);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 登录
     *
     * @param account 账号和密码
     * @return 判断登录是否成功
     */
    @Override
    public Boolean login(AccountDto account) {
        try {
            String password=accountMapper.queryPassword(account.getUsername());
            if(password.equals(account.getPassword())){
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public Account loginApp(AccountDto accountDto) {
        try {
            String password=accountMapper.queryPassword(accountDto.getUsername());
            if(password.equals(accountDto.getPassword())){
                Account account = accountMapper.queryByUsername(accountDto.getUsername());
                return account;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * 管理员注册
     * @param account 账号实体
     * @return 判断注册是否成功
     */
    @Override
    public Boolean register(Account account) {
        try {
            if(validate(account)){
                accountMapper.save(account);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * 修改密码
     * @param account 账号和密码
     * @return 判断修改是否成功
     */
    @Override
    public Boolean changePassword(AccountDto account) {
        try {
            if(accountMapper.updatePassword(account)>0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 注册验证账号合法性
     * @param account 账号实体
     * @return 账号合法性
     */
    private Boolean validate(Account account){
        if(account!=null){
            String username=account.getUsername();
            String password=account.getPassword();
            int usernameLength=username.length();
            int passwordLength=password.length();
            if(usernameLength>5&&usernameLength<13&&passwordLength>5&&passwordLength<21
                &&accountMapper.queryPassword(username)==null){
                    //可以注册username没有被占用
                    return true;
            }
        }
        return false;
    }

    @Override
    public Account findAccountByUserName(String username) {
        try{
            Account account = accountMapper.queryByUserName(username);
            return account;
        }catch (Exception e){
            return null;
        }
    }
}
