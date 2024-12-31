package com.zjut.springbootprj.service;

import com.zjut.springbootprj.pojo.Account;
import com.zjut.springbootprj.pojo.dto.AccountDto;

import java.util.List;

/**
 * 账号的业务处理接口类
 */
public interface AccountService {

    /**
     * 根据多个id批量删除账号
     *
     * @param ids 账号id
     * @return 判断是否删除成功
     */
    Boolean deleteAccounts(List<Integer> ids);

    /**
     * 修改账号
     *
     * @param account 修改的账号实体
     * @return 判断是否修改成功
     */
    Boolean updateAccount(Account account);

    /**
     * 查询账号信息
     *
     * @param id 账号id
     * @return 查找到的账号列表
     */
     Account findAccountById(Integer id);

    /**
     * 新增账号
     *
     * @param account 账号实体
     * @return 判断新增是否成功
     */
    Boolean saveAccount(Account account);

    /**
     *登录
     *
     * @param account 账号和密码
     * @return 判断登录是否成功
     */
    Boolean login(AccountDto account);

    Account loginApp(AccountDto accountDto);
    /**
     *注册
     *
     * @param account 账号和密码
     * @return 判断注册是否成功
     */
    Boolean register(Account account);

    /**
     * 修改密码
     *
     * @param account 账号和密码
     * @return 判断修改密码是否成功
     */
    Boolean changePassword(AccountDto account);
    /**
     * 查询账号信息
     *
     * @param username 账号
     * @return 查找到的账号列表
     */
    Account findAccountByUserName(String username);
}
