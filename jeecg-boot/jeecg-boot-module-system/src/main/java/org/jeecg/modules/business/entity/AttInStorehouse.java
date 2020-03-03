package org.jeecg.modules.business.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 入库信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
@Data
@TableName("att_in_storehouse")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="att_in_storehouse对象", description="入库信息表")
public class AttInStorehouse implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**备件id*/
	@Excel(name = "备件id", width = 15)
    @ApiModelProperty(value = "备件id")
    private String attId;
    /**备件编码*/
    @Excel(name = "备件编码", width = 15)
    @ApiModelProperty(value = "备件编码")
    private String attCode;
    /**备件名称*/
    @Excel(name = "备件名称", width = 15)
    @ApiModelProperty(value = "备件名称")
    private String attName;
	/**供应商id*/
	@Excel(name = "供应商id", width = 15)
    @ApiModelProperty(value = "供应商id")
    private String supId;
    /**供应商编码*/
    @Excel(name = "供应商编码", width = 15)
    @ApiModelProperty(value = "供应商编码")
    private String supCode;
    /**供应商名称*/
    @Excel(name = "供应商名称", width = 15)
    @ApiModelProperty(value = "供应商名称")
    private String supName;
	/**数量*/
	@Excel(name = "数量", width = 15)
    @ApiModelProperty(value = "数量")
    private BigDecimal num;
	/**入库时间*/
	@Excel(name = "入库时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入库时间")
    private Date inDateTime;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remark;
}
