package org.jeecg.modules.business.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.business.entity.AttAttachment;
import org.jeecg.modules.business.entity.AttInStorehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 入库信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
public interface AttInStorehouseMapper extends BaseMapper<AttInStorehouse> {

    /**
     * 分页
     * @param wrapper
     * @return
     */
    List<AttInStorehouse> findPages(@Param(Constants.WRAPPER) Wrapper wrapper);
}
