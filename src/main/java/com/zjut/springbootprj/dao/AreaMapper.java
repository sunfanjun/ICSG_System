package com.zjut.springbootprj.dao;

import com.zjut.springbootprj.pojo.Area;
import com.zjut.springbootprj.pojo.Monitor;
import com.zjut.springbootprj.pojo.dto.AreaDto;
import org.apache.ibatis.annotations.*;


import java.util.List;
/**
 * 区域与数据库mapper映射
 */
@Mapper
public interface AreaMapper {
    /**
     * 根据id删除记录
     * @param id 区域编号
     * @return  删除数目
     */
    @Delete("delete from area where id=#{id}")
    Integer deleteById(Integer id);

    /**
     * 根据id查询区域
     * @param areaDto 区域dto对象
     * @return  区域对象
     */

    List<Area> queryById(AreaDto areaDto);

    /**
     * 查询所有区域记录
     * @return 图片集合
     */
    @Select("select * from area")
    List<Area> query();

    /**
     * 根据区域名查询区域
     * @param name 区域名称
     * @return 区域对象集合
     */
    @Select("select * from area name=#{name}")
    List<Area> queryByName(String name);
    /**
     * 新增区域
     * @param area 区域对象
     * @return  添加数目
     */
    @Insert("insert into area(name) value(#{name})")
    Integer save(Area area);

    /**
     * 更新区域记录
     * @param area 区域对象
     * @return  更新数目
     */
    @Update("update area set name=#{name} where id=#{id}")
    Integer update(Area area);

    /**
     * 批量删除
     * @param ids 区域id集合
     * @return  删除数量
     */
    Integer deleteByIds(@Param("ids") List<Integer> ids);
}
