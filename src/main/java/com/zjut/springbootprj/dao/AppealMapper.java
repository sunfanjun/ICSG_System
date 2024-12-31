package com.zjut.springbootprj.dao;

import com.zjut.springbootprj.pojo.Appeal;
import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.pojo.dto.AppealDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppealMapper {
    /**
     * 根据id删除申诉
     * @param id 申诉编号
     * @return  删除数目
     */
    @Delete("delete from appeal where id=#{id} ")
    Integer deleteById(Integer id);


    /**
     * 根据id查询申诉
     * @param id 申诉编号
     * @return  申诉对象pojo
     */
    @Select("select * from appeal where id=#{id}")
    @Results(id="resultMap", value={
            @Result(property = "danger",column = "d_id",
                    javaType = Danger.class,
                    one = @One(select = "com.zjut.springbootprj.dao.DangerMapper.queryById"))
    })
    Appeal queryById(Integer id);

    /**
     * 查询所有申诉记录
     * @return 申诉对象集合
     */
    @Select("select * from appeal")
    @ResultMap("resultMap")
    List<Appeal> query();

    /**
     * 分页查询
     * @param start 开始索引
     * @param pageSize 页数
     * @return 申诉集合
     */
    @Select("select * from appeal limit #{start},#{pageSize}")
    @ResultMap("resultMap")
    List<Appeal> queryByPage(@Param("start") int start,@Param("pageSize")int pageSize);

    /**
     * 根据申诉是否处理，查询所有申诉记录
     * @param status 处理状态
     * @return 申诉对象集合
     */
    @Select("select * from appeal status=#{status}")
    @Results(
            @Result(property = "danger",column = "d_id",
                    javaType = Danger.class,
                    one = @One(select = "com.zjut.springbootprj.dao.DangerMapper.queryById"))
    )
    List<Appeal> queryByStatus(Integer status);
    /**
     * 新增申诉对象
     * @param appeal 申诉对象
     * @return  添加数目
     */
    @Insert("insert into appeal(d_id,time,reason,status) value(#{danger.id},#{time},#{reason},#{status})")
    Integer save(Appeal appeal);

    /**
     * 同意处理申诉，将状态设置为1
     * @param id 申诉编号
     * @return  更新数目
     */
    @Update("update appeal set status=1 where id=#{id}")
    Integer updateById(Integer id);

    /**
     * 更新申诉记录
     * @param appeal 申诉编号
     * @return  更新数目
     */
    @Update("update appeal set d_id=#{danger.id},time=#{time},reason=#{reason},status=#{status} where id=#{id}")
    Integer update(Appeal appeal);

    /**
     * 动态sql查询
     * @param appealDto 申诉事件dto对象
     * @return  查询危险事件列表
     */
    List<Appeal> queryDto(AppealDto appealDto);

    /**
     * 批量删除
     * @param ids 危险事件id集合
     * @return  删除数量
     */
    Integer deleteByIds(@Param("ids") List<Integer> ids);

    @Delete("delete from appeal where d_id=#{dId}")
    Integer deleteByDid(Integer dId);
    /**
     * 查询已处理告警事件或未处理的告警事件
     * @param status 申诉事件处理状态
     * @return 查询个数
     */
    @Select("select count(*) from appeal where status=#{status}")
    Integer queryAllByStatus(Integer status);
}
