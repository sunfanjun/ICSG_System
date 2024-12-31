package com.zjut.springbootprj.service;

import com.zjut.springbootprj.pojo.Area;
import com.zjut.springbootprj.pojo.dto.AreaDto;
import com.zjut.springbootprj.pojo.dto.AreaPageDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 区域的业务处理接口类
 */
public interface AreaService {
    /**
     * 根据多个id批量删除区域
     *
     * @param ids 区域id
     * @return 判断是否删除成功
     */
    Boolean deleteAreas(List<Integer> ids);

    /**
     * 修改区域
     *
     * @param area 修改的区域实体
     * @return 判断是否修改成功
     */
    Boolean updateArea(Area area);

    /**
     * 区域查询
     *
     * @param areaPageDto 区域实体类
     * @return 查找到的区域列表
     */
    List<Area> findArea(AreaPageDto areaPageDto);

    /**
     * 新增区域
     *
     * @param area 区域实体
     * @return 判断新增是否成功
     */
    Boolean saveArea(Area area);

    List<Area> findAll();
}
