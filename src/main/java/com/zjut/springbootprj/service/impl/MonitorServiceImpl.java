package com.zjut.springbootprj.service.impl;

import com.github.pagehelper.PageHelper;
import com.zjut.springbootprj.dao.DangerMapper;
import com.zjut.springbootprj.dao.MonitorMapper;
import com.zjut.springbootprj.pojo.Area;
import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.pojo.Monitor;
import com.zjut.springbootprj.pojo.dto.MonitorDto;
import com.zjut.springbootprj.pojo.dto.MonitorPageDto;
import com.zjut.springbootprj.service.MonitorService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 摄像头的业务处理实现类
 */
@Service
public class MonitorServiceImpl implements MonitorService {
    @Resource
    private MonitorMapper monitorMapper;
    @Resource
    private DangerMapper dangerMapper;

    /**
     * 根据多个id批量删除摄像头
     *
     * @param ids 摄像头id
     * @return 判断是否删除成功
     */
    @Override
    @Transactional
    public Boolean deleteMonitors(List<Integer> ids) {
        try {
            for (Integer id : ids) {
                dangerMapper.updateByMid(id);
            }
            monitorMapper.deleteByIds(ids);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 修改摄像头
     *
     * @param monitor 修改的摄像头实体
     * @return 判断是否修改成功
     */
    @Override
    public Boolean updateMonitor(Monitor monitor) {
        try {
            monitorMapper.update(monitor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 按id查找摄像头
     *
     * @param monitorPageDto 摄像头实体
     * @return 查找到的摄像头列表
     */
    @Override
    public List<Monitor> findMonitor(MonitorPageDto monitorPageDto) {
        PageHelper.startPage(monitorPageDto.getPageNum(), monitorPageDto.getPageSize());
        try {
            List<Monitor> monitorList = monitorMapper.queryDto(monitorPageDto.getMonitorDto());
            return monitorList;
        }catch (Exception e){
            return null;
        }

    }

    /**
     * 新增摄像头
     *
     * @param monitor 摄像头实体
     * @return 判断新增是否成功
     */
    @Override
    public Boolean saveMonitor(Monitor monitor) {
        try {
            monitorMapper.save(monitor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
