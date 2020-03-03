package org.jeecg.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.business.entity.AttInStorehouse;
import org.jeecg.modules.business.mapper.AttInStorehouseMapper;
import org.jeecg.modules.business.service.IAttInStorehouseService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 入库信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
@Service
public class AttInStorehouseServiceImpl extends ServiceImpl<AttInStorehouseMapper, AttInStorehouse> implements IAttInStorehouseService {

    @Override
    public IPage<AttInStorehouse> findPages(Page<AttInStorehouse> page, LambdaQueryWrapper<AttInStorehouse> wrapper) {
        return page.setRecords(this.baseMapper.findPages(wrapper));
    }
}
