package com.zjut.springbootprj.dao;

import com.zjut.springbootprj.pojo.Area;
import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.pojo.Monitor;
import com.zjut.springbootprj.pojo.SecurityGuard;
import com.zjut.springbootprj.pojo.dto.SecurityGuardDto;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface SecurityGuardMapper {

    /**
     * 查询保安信息
     * @return 保安对象集合
     */
    @Select("select * from securityguard")
    @Results(id="resultMap",value={
            @Result(column="a_id",property="area",
                    javaType = Area.class,
                    one=@One(select="com.zjut.springbootprj.dao.AreaMapper.queryById"))
    })
    List<SecurityGuard> query();

    /**
     * 分页查询
     * @param start 开始索引
     * @param pageSize 页数
     * @return 保安集合
     */
    @Select("select * from securityguard limit #{start},#{pageSize}")
    @ResultMap("resultMap")
    List<SecurityGuard> queryByPage(@Param("start") int start, @Param("pageSize")int pageSize);

    /**
     * 通过id查询保安信息
     * @param id 保安编号
     * @return 保安信息 SecurityGuard
     */
    @Select("select * from securityguard where id=#{id}")
    @ResultMap("resultMap")
    SecurityGuard queryById(Integer id);

    /**
     * 插入保安信息
     * @param securityGuard 保安信息
     * @return 影响行数
     */
    @Insert("insert into securityguard(name,sex,contact,level,a_id) " +
            "value(#{name},#{sex},#{contact},#{level},#{area.id})")
    Integer save(SecurityGuard securityGuard);

    /**
     * 通过id删除保安信息
     * @param id 保安id
     * @return 影响行数
     */
    @Delete("delete from securityguard where id=#{id}")
    Integer deleteById(Integer id);

    /**
     * 更新保安信息
     * @param securityGuard 保安信息
     * @return 影响行数
     */
    @Update("update securityguard set name=#{name},sex=#{sex},contact=#{contact}," +
            "level=#{level},a_id=#{area.id} where id=#{id}")
    Integer update(SecurityGuard securityGuard);

    /**
     * 动态sql查询
     * @param securityGuardDto 监控dto对象
     * @return  查询危险事件列表
     */
    List<SecurityGuard> queryDto(SecurityGuardDto securityGuardDto);

    /**
     * 批量删除
     * @param ids 监控id集合
     * @return  删除数量
     */
    Integer deleteByIds(@Param("ids") List<Integer> ids);

    @Update("update securityguard set a_id= null " +
            " where a_id=#{aId}")
    Integer updateByAid(Integer aId);
}
