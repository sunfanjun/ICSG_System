package com.zjut.springbootprj.service.impl;

import com.github.pagehelper.PageHelper;
import com.zjut.springbootprj.dao.AppealMapper;
import com.zjut.springbootprj.pojo.Appeal;
import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.pojo.dto.AppealDto;
import com.zjut.springbootprj.pojo.dto.AppealPageDto;
import com.zjut.springbootprj.service.AppealService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 申诉事件的业务处理实现类
 */
@Service
public class AppealServiceImpl implements AppealService {
    @Resource
    private AppealMapper appealMapper;

    /**
     * 根据多个id批量删除申诉事件
     *
     * @param ids 申诉事件id
     * @return 判断是否删除成功
     */
    @Override
    public Boolean deleteAppeals(List<Integer> ids) {
        try {
            appealMapper.deleteByIds(ids);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 修改申诉事件
     *
     * @param appeal 修改的申诉事件实体
     * @return 判断是否修改成功
     */
    @Override
    public Boolean updateAppeal(Appeal appeal) {
        try {
            appealMapper.update(appeal);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 修改申诉事件
     *
     * @param id 修改的申诉事件id
     * @return 判断是否修改成功
     */
    @Override
    public Boolean updateAppealById(Integer id) {
        try {
            appealMapper.updateById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查询申诉事件
     *
     * @param appealPageDto 申诉事件查询实体
     * @return 查找到的申诉事件列表
     */
    @Override
    public List<Appeal> findAppeal(AppealPageDto appealPageDto) {
        PageHelper.startPage(appealPageDto.getPageNum(), appealPageDto.getPageSize());
        try {
            List<Appeal> appealList = appealMapper.queryDto(appealPageDto.getAppealDto());
            return appealList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Appeal findAppealById(Integer id) {
        try {
            Appeal appeal = appealMapper.queryById(id);
            return appeal;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 新增申诉事件
     *
     * @param appeal 申诉事件实体
     * @return 判断新增是否成功
     */
    @Override
    public Boolean saveAppeal(Appeal appeal) {
        try {
            appealMapper.save(appeal);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public Integer findAllByStatus(Integer status) {
        try {
            return appealMapper.queryAllByStatus(status);
        } catch (Exception e) {
            return null;
        }
    }
}
