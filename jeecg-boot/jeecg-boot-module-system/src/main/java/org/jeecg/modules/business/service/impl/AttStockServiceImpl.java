package org.jeecg.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.business.entity.AttOutStorehouse;
import org.jeecg.modules.business.entity.AttStock;
import org.jeecg.modules.business.mapper.AttStockMapper;
import org.jeecg.modules.business.service.IAttStockService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 仓库库存表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
@Service
public class AttStockServiceImpl extends ServiceImpl<AttStockMapper, AttStock> implements IAttStockService {

    @Override
    public IPage<AttStock> findPages(Page<AttStock> page, LambdaQueryWrapper<AttStock> wrapper) {
        return page.setRecords(this.baseMapper.findPages(wrapper));
    }

}
