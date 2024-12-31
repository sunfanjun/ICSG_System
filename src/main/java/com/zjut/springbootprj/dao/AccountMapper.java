package com.zjut.springbootprj.dao;

import com.zjut.springbootprj.pojo.Account;;
import com.zjut.springbootprj.pojo.Appeal;
import com.zjut.springbootprj.pojo.dto.AccountDto;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface AccountMapper {

    /**
     * 查询所有账号信息
     * @param
     * @return 账号信息列表 List<Account>
     */
    @Select("select * from account")
    @Results(id="resultMap",value = {
            @Result(column="p_id",property="securityGuard",
                    javaType = SecurityGuardMapper.class,
                    one=@One(select="com.zjut.springbootprj.dao.SecurityGuardMapper.queryById"))
    })
    List<Account> query();

    @Select("select * from account where username=#{username}")
    @ResultMap("resultMap")
    Account queryByUsername(String username);

    /**
     * 通过id查询账号信息
     * @param id 账号编号
     * @return 账号信息 Account
     */
    @Select("select * from account where id=#{id}")
    @ResultMap("resultMap")
    Account queryById(Integer id);

    /**
     * 分页查询
     * @param start 开始索引
     * @param pageSize 页数
     * @return 账号集合
     */
    @Select("select * from account limit #{start},#{pageSize}")
    @ResultMap("resultMap")
    List<Account> queryByPage(@Param("start") int start, @Param("pageSize")int pageSize);

    /**
     * 插入账号信息
     * @param account 保安信息
     * @return 影响行数
     */
    @Insert("insert into account(username,password,type,p_id) " +
            "values(#{username},#{password},#{type},#{securityGuard.id})")
    Integer save(Account account);

    /**
     * 通过id删除账号信息
     * @param id 账号id
     * @return 影响行数
     */
    @Delete("delete from account where id=#{id}")
    Integer deleteById(Integer id);

    /**
     * 通过账号对象修改账号信息
     * @param account 账号对象
     * @return 影响行数
     */
    @Update("update account set username=#{username},password=#{password}, " +
            "type=#{type},p_id=#{securityGuard.id} where id=#{id}")
    Integer update(Account account);

    /**
     * 批量删除
     * @param ids 监控id集合
     * @return  删除数量
     */
    Integer deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 给账号外键设置为空
     * @param id 外键
     * @return  更新数量
     */
    @Update("update account set p_id=null where p_id=#{id}")
    Integer updateByPid(@Param("id") Integer id);

    /**
     * 根据外键查询账号主键
     * @param pId 外键
     * @return 主键值
     */
    @Select("select id from account where p_id=#{pId}")
    Integer queryByPid(Integer pId);

    /**
     * 根据账号查询密码
     * @param username 用户名
     * @return 密码
     */
    @Select("select password from account where username=#{username}")
    String queryPassword(String username);

    /**
     * 根据用户名修改用户密码
     * @param account 账号和新密码组成的实体
     * @return 更新数目
     */
    @Update("update account set password = #{password} where username=#{username}")
    Integer updatePassword(AccountDto account);
    /**
     * 查询账号信息
     *
     * @param username 账号
     * @return 查找到的账号列表
     */
    @Select("select * from account where username=#{username}")
    Account queryByUserName(String username);
}
