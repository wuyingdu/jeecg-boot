package org.jeecg.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.business.entity.AttInStorehouse;
import org.jeecg.modules.business.entity.AttOutStorehouse;
import org.jeecg.modules.business.mapper.AttOutStorehouseMapper;
import org.jeecg.modules.business.service.IAttOutStorehouseService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 出库信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
@Service
public class AttOutStorehouseServiceImpl extends ServiceImpl<AttOutStorehouseMapper, AttOutStorehouse> implements IAttOutStorehouseService {

    @Override
    public IPage<AttOutStorehouse> findPages(Page<AttOutStorehouse> page, LambdaQueryWrapper<AttOutStorehouse> wrapper) {
        return page.setRecords(this.baseMapper.findPages(wrapper));
    }
}
