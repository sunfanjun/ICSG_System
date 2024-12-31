package com.zjut.springbootprj.service.impl;

import com.github.pagehelper.PageHelper;
import com.zjut.springbootprj.dao.AreaMapper;
import com.zjut.springbootprj.dao.MonitorMapper;
import com.zjut.springbootprj.dao.SecurityGuardMapper;
import com.zjut.springbootprj.pojo.Area;
import com.zjut.springbootprj.pojo.Monitor;
import com.zjut.springbootprj.pojo.SecurityGuard;
import com.zjut.springbootprj.pojo.dto.AreaDto;
import com.zjut.springbootprj.pojo.dto.AreaPageDto;
import com.zjut.springbootprj.service.AreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 区域的业务处理实现类
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Resource
    private AreaMapper areaMapper;
    @Resource
    private MonitorMapper monitorMapper;
    @Resource
    private SecurityGuardMapper securityGuardMapper;

    /**
     * 根据多个id批量删除区域
     *
     * @param ids 区域id
     * @return 判断是否删除成功
     */
    @Override
    @Transactional
    public Boolean deleteAreas(List<Integer> ids){
        try {
            for (Integer id: ids) {
                securityGuardMapper.updateByAid(id);
                monitorMapper.updateByAid(id);
            }
            areaMapper.deleteByIds(ids);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    /**
     * 修改区域
     *
     * @param area 修改的区域实体
     * @return 判断是否修改成功
     */
    @Override
    public Boolean updateArea(Area area) {
        try {
            areaMapper.update(area);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 区域查询
     *
     * @param areaPageDto 区域查询实体类
     * @return 查找到的区域列表
     */
    @Override
    public List<Area> findArea(AreaPageDto areaPageDto) {
        PageHelper.startPage(areaPageDto.getPageNum(), areaPageDto.getPageSize());
        try{
            List<Area> areaList = areaMapper.queryById(areaPageDto.getAreaDto());
            return areaList;
        }catch (Exception e){
            return null;
        }
    }

    public List<Area> findAll() {
            List<Area> areaList = areaMapper.query();
            return areaList;
    }

    /**
     * 新增区域
     *
     * @param area 区域实体
     * @return 判断新增是否成功
     */
    @Override
    public Boolean saveArea(Area area) {
        try {
            areaMapper.save(area);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
