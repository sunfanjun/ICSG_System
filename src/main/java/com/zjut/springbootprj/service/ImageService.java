package com.zjut.springbootprj.service;

import com.zjut.springbootprj.pojo.Image;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 图片的业务处理接口类
 */
public interface ImageService {

    /**
     * 根据多个id批量删除图片
     *
     * @param ids 图片id
     * @return 判断是否删除成功
     */
    Boolean deleteImages(List<Integer> ids);

    /**
     * 修改图片
     *
     * @param image 修改的图片实体
     * @return 判断是否修改成功
     */
    Boolean updateImage(Image image);

    /**
     * 新增图片
     *
     * @param image 图片实体
     * @return 判断新增是否成功
     */
    Boolean saveImage(Image image);

}
