package org.jeecg.modules.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.business.entity.AttOutStorehouse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 出库信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
public interface IAttOutStorehouseService extends IService<AttOutStorehouse> {

    /**
     * 分页
     * @param page
     * @param wrapper
     * @return
     */
    IPage<AttOutStorehouse> findPages(Page<AttOutStorehouse> page, LambdaQueryWrapper<AttOutStorehouse> wrapper);
}
