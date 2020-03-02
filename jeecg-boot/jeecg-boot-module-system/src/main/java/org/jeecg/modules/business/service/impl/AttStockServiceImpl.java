package org.jeecg.modules.business.service.impl;

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

}
