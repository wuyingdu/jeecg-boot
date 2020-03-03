package org.jeecg.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.business.entity.AttAttachment;
import org.jeecg.modules.business.mapper.AttAttachmentMapper;
import org.jeecg.modules.business.service.IAttAttachmentService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 备件信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
@Service
public class AttAttachmentServiceImpl extends ServiceImpl<AttAttachmentMapper, AttAttachment> implements IAttAttachmentService {

    @Override
    public IPage<AttAttachment> findPages(Page<AttAttachment> page, LambdaQueryWrapper wrapper) {
        return page.setRecords(this.baseMapper.findPages(wrapper));
    }
}
