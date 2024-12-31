package com.zjut.springbootprj.dao;

import com.zjut.springbootprj.pojo.Appeal;
import com.zjut.springbootprj.pojo.dto.DangerDto;
import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.pojo.Monitor;
import com.zjut.springbootprj.pojo.SecurityGuard;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DangerMapper {

    /**
     * 根据id删除危险事件
     * @param id 危险事件编号
     * @return  删除数目
     */
    @Delete("delete from danger where id=#{id} ")
    Integer deleteById(Integer id);


    /**
     * 查询所有危险事件记录
     * @return 危险事件集合
     */
    @Select("select * from danger")
    @Results(id="resultMap",value={
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "monitor",column = "m_id",
                    javaType = Monitor.class,
                    one = @One(select = "com.zjut.springbootprj.dao.MonitorMapper.queryById")),
            @Result(property = "securityGuard",column = "p_id",
                    javaType = SecurityGuard.class,
                    one = @One(select = "com.zjut.springbootprj.dao.SecurityGuardMapper.queryById")),
            @Result(property = "images",column = "id",
                    javaType =List.class,
                    many = @Many(select = "com.zjut.springbootprj.dao.ImageMapper.queryByDid"))

    })
    List<Danger> query();

    /**
     * 根据id查询危险事件
     * @param id 危险时间编号
     * @return  危险对象
     */
    @Select("select * from danger where id=#{id}")
    @ResultMap("resultMap")
    Danger queryById(Integer id);


    /**
     * 根据危险事件类型，查询所有危险记录
     * @param type 危险事件类型
     * @return 危险事件集合
     */
    @Select("select * from danger where type=#{type}")
    @ResultMap("resultMap")
    List<Danger> queryByType(String type);
    /**
     * 新增危险事件
     * @param danger 危险事件对象
     * @return  添加数目
     */
    @Insert("insert into danger(type,f_time,m_id,dispose,p_id,status,remark,c_time) " +
            "value(#{type},#{fTime},#{monitor.id},#{dispose}," +
            "#{securityGuard.id},#{status},#{remark},#{cTime})")
    Integer save(Danger danger);

    /**
     * 更新危险事件
     * @param danger 危险事件对象
     * @return  更新数目
     */
    @Update("update danger set type=#{type},f_time=#{fTime},m_id=#{monitor.id},dispose=#{dispose}," +
            "p_id=#{securityGuard.id},status=#{status},remark=#{remark},c_time=#{cTime} where id=#{id}")
    Integer update(Danger danger);

    /**
     * 分页查询
     * @param start 开始索引
     * @param pageSize 页数
     * @return 危险事件集合
     */
    @Select("select * from danger limit #{start},#{pageSize}")
    @ResultMap("resultMap")
    List<Danger> queryByPage(@Param("start") int start,@Param("pageSize")int pageSize);

    /**
     * 动态sql查询
     * @param dangerDto 危险事件dto对象
     * @return  查询危险事件列表
     */
    List<Danger> queryDto(DangerDto dangerDto);

    /**
     * 批量删除
     * @param ids 危险事件id集合
     * @return  删除数量
     */
    Integer deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 根据危险事件外键获得appeal的id
     * @param id 危险事件id
     * @return 申诉事件id
     */
    @Select("select id from appeal where d_id=#{id}")
    Integer queryAppealId(Integer id);

    @Update("update danger set m_id=null where m_id=#{id}")
    void updateByMid(@Param("id") Integer id);

    @Update("update danger set p_id=null where p_id=#{id}")
    void updateByPid(@Param("id") Integer id);
    /**
     * 查询已处理告警事件或未处理的告警事件
     * @param status 危险事件处理状态
     * @return 查询个数
     */
    @Select("select count(*) from danger where status = #{status}")
    Integer queryAllByStatus(Integer status);

    @Select("select id from danger where type=#{type} and m_id=#{monitor.id} and f_time=#{fTime}")
    Integer queryId(Danger danger);
}
