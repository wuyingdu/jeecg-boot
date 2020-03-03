package org.jeecg.modules.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.business.entity.AttInStorehouse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 入库信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
public interface IAttInStorehouseService extends IService<AttInStorehouse> {

    /**
     * 分页
     * @param page
     * @param wrapper
     * @return
     */
    IPage<AttInStorehouse> findPages(Page<AttInStorehouse> page, LambdaQueryWrapper<AttInStorehouse> wrapper);
}
