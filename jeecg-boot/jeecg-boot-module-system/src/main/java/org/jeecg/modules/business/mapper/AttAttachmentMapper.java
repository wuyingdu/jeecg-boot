package org.jeecg.modules.business.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.business.entity.AttAttachment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 备件信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
public interface AttAttachmentMapper extends BaseMapper<AttAttachment> {

    /**
     * 分页
     * @param wrapper
     * @return
     */
    List<AttAttachment> findPages(@Param(Constants.WRAPPER) Wrapper wrapper);

}
