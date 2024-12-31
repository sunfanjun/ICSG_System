package com.zjut.springbootprj.service;

import com.zjut.springbootprj.pojo.Appeal;
import com.zjut.springbootprj.pojo.dto.AppealDto;
import com.zjut.springbootprj.pojo.dto.AppealPageDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 申诉事件的业务处理接口类
 */
public interface AppealService {
    /**
     * 根据多个id批量删除申诉事件
     *
     * @param ids 申诉事件id
     * @return 判断是否删除成功
     */
    Boolean deleteAppeals(List<Integer> ids);

    /**
     * 修改申诉事件
     *
     * @param appeal 修改的申诉事件实体
     * @return 判断是否修改成功
     */
    Boolean updateAppeal(Appeal appeal);


    /**
     * 查询申诉事件
     *
     * @param  appealPageDto 申诉事件查询实体
     * @return 查找到的申诉事件列表
     */
    List<Appeal> findAppeal(AppealPageDto appealPageDto);

    Appeal findAppealById(Integer id);

    /**
     * 新增申诉事件
     *
     * @param appeal 申诉事件实体
     * @return 判断新增是否成功
     */
    Boolean saveAppeal(Appeal appeal);

    /**
     * 修改申诉事件
     *
     * @param id 修改的申诉事件id
     * @return 判断是否修改成功
     */
    Boolean updateAppealById(Integer id);
    Integer findAllByStatus(Integer status);
}
