package org.jeecg.modules.business.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.business.entity.AttAttachment;
import org.jeecg.modules.business.service.IAttAttachmentService;
import org.jeecg.modules.business.entity.AttAttachment;
import org.jeecg.modules.business.service.IAttAttachmentService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 备件信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
@Api(tags="备件信息表")
@RestController
@RequestMapping("/business/attAttachment")
@Slf4j
public class AttAttachmentController extends JeecgController<AttAttachment, IAttAttachmentService> {
	@Autowired
	private IAttAttachmentService attAttachmentService;
	
	/**
	 * 分页列表查询
	 *
	 * @param attAttachment
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "备件信息表-分页列表查询")
	@ApiOperation(value="备件信息表-分页列表查询", notes="备件信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AttAttachment attAttachment,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AttAttachment> queryWrapper = QueryGenerator.initQueryWrapper(attAttachment, req.getParameterMap());
		Page<AttAttachment> page = new Page<AttAttachment>(pageNo, pageSize);
		IPage<AttAttachment> pageList = attAttachmentService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param attAttachment
	 * @return
	 */
	@AutoLog(value = "备件信息表-添加")
	@ApiOperation(value="备件信息表-添加", notes="备件信息表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AttAttachment attAttachment) {
		attAttachmentService.save(attAttachment);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param attAttachment
	 * @return
	 */
	@AutoLog(value = "备件信息表-编辑")
	@ApiOperation(value="备件信息表-编辑", notes="备件信息表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AttAttachment attAttachment) {
		attAttachmentService.updateById(attAttachment);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "备件信息表-通过id删除")
	@ApiOperation(value="备件信息表-通过id删除", notes="备件信息表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		attAttachmentService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "备件信息表-批量删除")
	@ApiOperation(value="备件信息表-批量删除", notes="备件信息表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.attAttachmentService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "备件信息表-通过id查询")
	@ApiOperation(value="备件信息表-通过id查询", notes="备件信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AttAttachment attAttachment = attAttachmentService.getById(id);
		if(attAttachment==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(attAttachment);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param attAttachment
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AttAttachment attAttachment) {
        return super.exportXls(request, attAttachment, AttAttachment.class, "备件信息表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AttAttachment.class);
    }

}
