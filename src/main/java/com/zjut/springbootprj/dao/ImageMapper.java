package com.zjut.springbootprj.dao;

import com.zjut.springbootprj.pojo.Area;
import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.pojo.Image;
import com.zjut.springbootprj.pojo.dto.DangerDto;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;


import java.util.List;
/**
 * 图片与数据库mapper映射
 */
@Mapper
public interface ImageMapper {
    /**
     * 根据id删除申诉
     * @param id 图片编号
     * @return  删除数目
     */
    @Delete("delete from image where id=#{id} ")
    Integer deleteById(Integer id);


    /**
     * 根据id查询图片
     * @param id 图片编号
     * @return  图片对象pojo
     */
    @Select("select * from image where id=#{id}")
    @Results(id="resultMap",value={
            @Result(column="d_id",property="danger",
                    javaType = Danger.class,
                    one=@One(select="com.zjut.springbootprj.dao.DangerMapper.queryById"))
    })
    Image queryById(Integer id);

    /**
     * 查询所有图片记录
     * @return 图片集合
     */
    @Select("select * from image")
    @ResultMap("resultMap")
    List<Image> query();

    /**
     * 根据图片链接查询图片
     * @param link 图片链接
     * @return 申诉对象集合
     */
    @Select("select * from image link=#{link}")
    @ResultMap("resultMap")
    List<Image> queryByLink(String link);

    /**
     * 分页查询
     * @param start 开始索引
     * @param pageSize 页数
     * @return 图片集合
     */
    @Select("select * from image limit #{start},#{pageSize}")
    @ResultMap("resultMap")
    List<Image> queryByPage(@Param("start") int start,@Param("pageSize")int pageSize);
    /**
     * 新增图片
     * @param image 图片对象
     * @return  添加数目
     */
    @Insert("insert into image(link,d_id) value(#{link},#{danger.id})")
    Integer save(Image image);

    /**
     * 更新图片记录
     * @param image 图片对象
     * @return  更新数目
     */
    @Update("update image set link=#{link} where id=#{id}")
    Integer update(Image image);


    Integer deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 根据外键获取图像id和link
     * @param id 外键
     * @return  图像id和link
     */
    @Select("select id,link from image where d_id=#{id}")
    List<Image> queryByDid(Integer id);

    @Delete("delete from image where d_id=#{dId}")
    Integer deleteByDid(Integer dId);
}
