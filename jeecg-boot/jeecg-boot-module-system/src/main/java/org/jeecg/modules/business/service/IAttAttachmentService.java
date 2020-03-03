package org.jeecg.modules.business.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.business.entity.AttAttachment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 备件信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
public interface IAttAttachmentService extends IService<AttAttachment> {


    /**
     * 分页
     * @param page
     * @param wrapper
     * @return
     */
    IPage<AttAttachment> findPages(Page<AttAttachment> page, LambdaQueryWrapper wrapper);


}
