package com.zjut.springbootprj.dao;

import com.zjut.springbootprj.pojo.*;
import com.zjut.springbootprj.pojo.dto.MonitorDto;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface MonitorMapper {
    /**
     * 查询所有监控器信息
     * @param
     * @return 监控器信息列表 List<Monitor>
     */
    @Select("select * from monitor")
    @Results(id="resultMap", value={
            @Result(column="a_id",property="area",
                    javaType = Area.class,
                    one=@One(select="com.zjut.springbootprj.dao.AreaMapper.queryById"))
    })
    List<Monitor> query();

    /**
     * 分页查询
     * @param start 开始索引
     * @param pageSize 页数
     * @return 监控集合
     */
    @Select("select * from monitor limit #{start},#{pageSize}")
    @ResultMap("resultMap")
    List<Monitor> queryByPage(@Param("start") int start,@Param("pageSize")int pageSize);

    /**
     * 通过id查询监控器信息
     * @param id 监控器编号
     * @return 监控器信息 Monitor
     */
    @Select("select * from monitor where id=#{id}")
    @ResultMap("resultMap")
    Monitor queryById(Integer id);

    /**
     * 插入监控器信息
     * @param monitor 监控器信息
     * @return 影响行数
     */
    @Insert("insert into monitor(a_id,time,status) values(#{area.id},#{time},#{status})")
    Integer save(Monitor monitor);

    /**
     * 通过id删除监控信息
     * @param id 监控id
     * @return 影响行数
     */
    @Delete("delete from monitor where id=#{id}")
    Integer deleteById(Integer id);

    /**
     * 通过监控器对象修改账号信息
     * @param monitor 监控器对象
     * @return 影响行数
     */
    @Update("update monitor set a_id=#{area.id}, time=#{time}, status=#{status} where id=#{id}")
    Integer update(Monitor monitor);
    /**
     * 动态sql查询
     * @param monitorDto 监控dto对象
     * @return  查询危险事件列表
     */
    List<Monitor> queryDto(MonitorDto monitorDto);

    /**
     * 批量删除
     * @param ids 监控id集合
     * @return  删除数量
     */
    Integer deleteByIds(@Param("ids") List<Integer> ids);

    @Update("update monitor set a_id= null where a_id=#{aId}")
    Integer updateByAid(Integer aId);
}
