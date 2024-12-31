package com.zjut.springbootprj.service;

import com.zjut.springbootprj.pojo.Danger;
import com.zjut.springbootprj.pojo.dto.DangerDto;
import com.zjut.springbootprj.pojo.dto.DangerPageDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 危险事件的业务处理接口类
 */
public interface DangerService {

    /**
     * 根据多个id批量删除危险事件
     *
     * @param ids 危险事件id
     * @return 判断是否删除成功
     */
    Boolean deleteDangers(List<Integer> ids);

    /**
     * 修改危险事件
     *
     * @param danger 修改的危险事件实体
     * @return 判断是否修改成功
     */
    Boolean updateDanger(Danger danger);

    /**
     * 危险事件查询
     *
     * @param dangerPageDto 危险事件实体
     * @return 查找到的危险事件列表
     */
    List<Danger> findDanger(DangerPageDto dangerPageDto);

    Danger findDangerById(Integer id);

    /**
     * 新增危险事件
     *
     * @param danger 危险事件实体
     * @return 判断新增是否成功
     */
    Boolean saveDanger(Danger danger);
    Integer findAllByStatus(Integer status);

}
