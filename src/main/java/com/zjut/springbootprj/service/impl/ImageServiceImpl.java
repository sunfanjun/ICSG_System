package com.zjut.springbootprj.service.impl;

import com.zjut.springbootprj.dao.ImageMapper;
import com.zjut.springbootprj.pojo.Image;
import com.zjut.springbootprj.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 图片的业务处理实现类
 */
@Service
public class ImageServiceImpl implements ImageService {
    @Resource
    private ImageMapper imageMapper;

    /**
     * 根据多个id批量删除图片
     *
     * @param ids 图片id
     * @return 判断是否删除成功
     */
    @Override
    @Transactional
    public Boolean deleteImages(List<Integer> ids) {
        try {
            imageMapper.deleteByIds(ids);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 修改图片
     *
     * @param image 修改的图片实体
     * @return 判断是否修改成功
     */
    @Override
    public Boolean updateImage(Image image) {
        try {
            imageMapper.update(image);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 新增图片
     *
     * @param image 图片实体
     * @return 判断新增是否成功
     */
    @Override
    public Boolean saveImage(Image image) {
        try {
            imageMapper.save(image);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
