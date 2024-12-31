package com.zjut.springbootprj.controller;


import com.zjut.springbootprj.common.Constants;
import com.zjut.springbootprj.common.Result;
import com.zjut.springbootprj.pojo.Account;
import com.zjut.springbootprj.pojo.dto.AccountDto;
import com.zjut.springbootprj.service.AccountService;
import org.springframework.web.bind.annotation.*;
import com.zjut.springbootprj.utils.JwtUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;

    @GetMapping("/{id}")
    public Result<Account> findAccount(@PathVariable Integer id){
        Account account=accountService.findAccountById(id);
        return Result.success(account);
    }

    /**
     * 注册模块
     * @param account 账号实体
     */
    @PostMapping("/register")
    public Result register(@RequestBody Account account){
        if(accountService.register(account))
            return Result.success();
        else
            return Result.error("该用户名已被注册，请重新输入！");
    }

    /**
     * 登录模块
     * @param account 账号实体
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody AccountDto account){
        if(accountService.login(account)) {
            Account user = accountService.findAccountByUserName(account.getUsername());
            System.out.println(user);
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            String token= JwtUtil.getToken(claims);
            return Result.success(token);
        }
        else{
            return Result.error(Constants.CODE_401,"登录失败！");
        }
    }

    /**
     * 修改密码模块
     * @param account 账号实体
     */
    @PostMapping("/password")
    public Result changePassword(@RequestBody AccountDto account){
        if(accountService.changePassword(account))
            return Result.success();
        else
            return Result.error("修改密码失败！");
    }

    @PostMapping("/loginApp")
    public Result loginApp(@RequestBody AccountDto accountDto){
//        Account account=accountService.loginApp(accountDto);
//        return Result.success(account);
        if(accountService.login(accountDto)) {
            Account user = accountService.findAccountByUserName(accountDto.getUsername());
            System.out.println(user);
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            String token= JwtUtil.getToken(claims);
            return Result.success(token);
        }
        else{
            return Result.error(Constants.CODE_401,"登录失败！");
        }
    }

    @PostMapping
    public Result saveAccount(@RequestBody Account account){
        if(accountService.saveAccount(account))
            return Result.success();
        else
            return Result.error("数据插入失败！");
    }
    @PutMapping
    public Result updateAccount(@RequestBody Account account){
        if(accountService.updateAccount(account))
            return Result.success();
        else
            return Result.error("数据更新失败！");
    }
    @DeleteMapping
    public Result deleteAccount(@RequestParam List<Integer> ids){
        if(accountService.deleteAccounts(ids))
            return Result.success();
        else
            return Result.error("数据删除失败！");
    }
}
