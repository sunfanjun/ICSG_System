package com.zjut.springbootprj.service;

import com.zjut.springbootprj.pojo.Monitor;
import com.zjut.springbootprj.pojo.dto.MonitorDto;
import com.zjut.springbootprj.pojo.dto.MonitorPageDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 摄像头的业务处理接口类
 */
public interface MonitorService {

    /**
     * 根据多个id批量删除摄像头
     *
     * @param ids 摄像头id
     * @return 判断是否删除成功
     */
    Boolean deleteMonitors(List<Integer> ids);

    /**
     * 修改摄像头
     *
     * @param monitor 修改的摄像头实体
     * @return 判断是否修改成功
     */
    Boolean updateMonitor(Monitor monitor);

    /**
     * 按id查找摄像头
     *
     * @param monitorPageDto 摄像头实体
     * @return 查找到的摄像头列表
     */
    List<Monitor> findMonitor(MonitorPageDto monitorPageDto);

    /**
     * 新增摄像头
     *
     * @param monitor 摄像头实体
     * @return 判断新增是否成功
     */
    Boolean saveMonitor(Monitor monitor);
}
