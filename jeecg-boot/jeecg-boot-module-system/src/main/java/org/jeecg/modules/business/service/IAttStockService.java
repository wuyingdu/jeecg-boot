package org.jeecg.modules.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.business.entity.AttStock;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 仓库库存表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
public interface IAttStockService extends IService<AttStock> {

    /**
     * 分页
     * @param page
     * @param wrapper
     * @return
     */
    IPage<AttStock> findPages(Page<AttStock> page, LambdaQueryWrapper<AttStock> wrapper);
}
