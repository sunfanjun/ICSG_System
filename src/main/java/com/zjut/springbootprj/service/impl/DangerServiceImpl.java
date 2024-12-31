package com.zjut.springbootprj.service.impl;

import com.github.pagehelper.PageHelper;
import com.zjut.springbootprj.dao.AppealMapper;
import com.zjut.springbootprj.dao.DangerMapper;
import com.zjut.springbootprj.dao.ImageMapper;
import com.zjut.springbootprj.pojo.Appeal;
import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.pojo.Image;
import com.zjut.springbootprj.pojo.dto.DangerDto;
import com.zjut.springbootprj.pojo.dto.DangerPageDto;
import com.zjut.springbootprj.service.DangerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 危险事件的业务处理实现类
 */
@Service
public class DangerServiceImpl implements DangerService {
    @Resource
    private DangerMapper dangerMapper;
    @Resource
    private ImageMapper imageMapper;
    @Resource
    private AppealMapper appealMapper;

    /**
     * 根据多个id批量删除危险事件
     *
     * @param ids 危险事件id
     * @return 判断是否删除成功
     */
    @Override
    @Transactional
    public Boolean deleteDangers(List<Integer> ids) {
        try {
            for (Integer id : ids) {
                imageMapper.deleteByDid(id);
                appealMapper.deleteByDid(id);
            }
            //删除危险事件
            dangerMapper.deleteByIds(ids);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 修改危险事件
     *
     * @param danger 修改的危险事件实体
     * @return 判断是否修改成功
     */
    @Override
    public Boolean updateDanger(Danger danger) {
        try {
            dangerMapper.update(danger);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 危险事件查询
     *
     * @param dangerPageDto 危险事件查询实体
     * @return 查找到的危险事件列表
     */
    @Override
    public List<Danger> findDanger(DangerPageDto dangerPageDto) {
        PageHelper.startPage(dangerPageDto.getPageNum(), dangerPageDto.getPageSize());
        try {
            List<Danger> dangerList = dangerMapper.queryDto(dangerPageDto.getDangerDto());
            return dangerList;
        } catch (Exception e) {
            return null;
        }
    }

    public Danger findDangerById(Integer id) {
        try {
            Danger danger = dangerMapper.queryById(id);
            return danger;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 新增危险事件
     *
     * @param danger 危险事件实体
     * @return 判断新增是否成功
     */
    @Override
    public Boolean saveDanger(Danger danger) {
        try {
            dangerMapper.save(danger);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Integer findAllByStatus(Integer status) {
        try {
            return dangerMapper.queryAllByStatus(status);
        } catch (Exception e) {
            return null;
        }
    }
}
