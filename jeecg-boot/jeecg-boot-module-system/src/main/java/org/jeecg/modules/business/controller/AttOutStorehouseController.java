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
import org.jeecg.modules.business.entity.AttInStorehouse;
import org.jeecg.modules.business.entity.AttOutStorehouse;
import org.jeecg.modules.business.service.IAttOutStorehouseService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.system.controller.SysUserController;
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
 * @Description: 出库信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
@Api(tags="出库信息表")
@RestController
@RequestMapping("/business/attOutStorehouse")
@Slf4j
public class AttOutStorehouseController extends JeecgController<AttOutStorehouse, IAttOutStorehouseService> {
	@Autowired
	private IAttOutStorehouseService attOutStorehouseService;
	
	/**
	 * 分页列表查询
	 *
	 * @param attOutStorehouse
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "出库信息表-分页列表查询")
	@ApiOperation(value="出库信息表-分页列表查询", notes="出库信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AttOutStorehouse attOutStorehouse,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
//		QueryWrapper<AttOutStorehouse> queryWrapper = QueryGenerator.initQueryWrapper(attOutStorehouse, req.getParameterMap());
//		Page<AttOutStorehouse> page = new Page<AttOutStorehouse>(pageNo, pageSize);
//		IPage<AttOutStorehouse> pageList = attOutStorehouseService.page(page, queryWrapper);
		LambdaQueryWrapper<AttOutStorehouse> wrapper = Wrappers.lambdaQuery(attOutStorehouse);
		Page<AttOutStorehouse> page = new Page<>(pageNo, pageSize);
		IPage<AttOutStorehouse> pageList = attOutStorehouseService.findPages(page, wrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param attOutStorehouse
	 * @return
	 */
	@AutoLog(value = "出库信息表-添加")
	@ApiOperation(value="出库信息表-添加", notes="出库信息表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AttOutStorehouse attOutStorehouse) {
		attOutStorehouseService.save(attOutStorehouse);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param attOutStorehouse
	 * @return
	 */
	@AutoLog(value = "出库信息表-编辑")
	@ApiOperation(value="出库信息表-编辑", notes="出库信息表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AttOutStorehouse attOutStorehouse) {
		attOutStorehouseService.updateById(attOutStorehouse);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "出库信息表-通过id删除")
	@ApiOperation(value="出库信息表-通过id删除", notes="出库信息表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		attOutStorehouseService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "出库信息表-批量删除")
	@ApiOperation(value="出库信息表-批量删除", notes="出库信息表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.attOutStorehouseService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "出库信息表-通过id查询")
	@ApiOperation(value="出库信息表-通过id查询", notes="出库信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AttOutStorehouse attOutStorehouse = attOutStorehouseService.getById(id);
		if(attOutStorehouse==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(attOutStorehouse);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param attOutStorehouse
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AttOutStorehouse attOutStorehouse) {
        return super.exportXls(request, attOutStorehouse, AttOutStorehouse.class, "出库信息表");
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
        return super.importExcel(request, response, AttOutStorehouse.class);
    }

}
