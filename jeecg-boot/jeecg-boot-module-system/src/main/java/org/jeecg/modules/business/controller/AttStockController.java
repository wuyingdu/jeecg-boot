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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.business.entity.AttOutStorehouse;
import org.jeecg.modules.business.entity.AttStock;
import org.jeecg.modules.business.service.IAttStockService;

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
 * @Description: 仓库库存表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
@Api(tags="仓库库存表")
@RestController
@RequestMapping("/business/attStock")
@Slf4j
public class AttStockController extends JeecgController<AttStock, IAttStockService> {
	@Autowired
	private IAttStockService attStockService;
	
	/**
	 * 分页列表查询
	 *
	 * @param attStock
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "仓库库存表-分页列表查询")
	@ApiOperation(value="仓库库存表-分页列表查询", notes="仓库库存表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AttStock attStock,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
//		QueryWrapper<AttStock> queryWrapper = QueryGenerator.initQueryWrapper(attStock, req.getParameterMap());
//		Page<AttStock> page = new Page<AttStock>(pageNo, pageSize);
//		IPage<AttStock> pageList = attStockService.page(page, queryWrapper);
		LambdaQueryWrapper<AttStock> wrapper = Wrappers.lambdaQuery(attStock);
		Page<AttStock> page = new Page<>(pageNo, pageSize);
		IPage<AttStock> pageList = attStockService.findPages(page, wrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param attStock
	 * @return
	 */
	@AutoLog(value = "仓库库存表-添加")
	@ApiOperation(value="仓库库存表-添加", notes="仓库库存表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AttStock attStock) {
		attStockService.save(attStock);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param attStock
	 * @return
	 */
	@AutoLog(value = "仓库库存表-编辑")
	@ApiOperation(value="仓库库存表-编辑", notes="仓库库存表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AttStock attStock) {
		attStockService.updateById(attStock);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "仓库库存表-通过id删除")
	@ApiOperation(value="仓库库存表-通过id删除", notes="仓库库存表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		attStockService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "仓库库存表-批量删除")
	@ApiOperation(value="仓库库存表-批量删除", notes="仓库库存表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.attStockService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "仓库库存表-通过id查询")
	@ApiOperation(value="仓库库存表-通过id查询", notes="仓库库存表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AttStock attStock = attStockService.getById(id);
		if(attStock==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(attStock);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param attStock
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AttStock attStock) {
        return super.exportXls(request, attStock, AttStock.class, "仓库库存表");
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
        return super.importExcel(request, response, AttStock.class);
    }

}
