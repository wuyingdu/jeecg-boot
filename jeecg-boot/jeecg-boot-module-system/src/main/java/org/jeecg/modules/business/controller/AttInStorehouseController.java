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
import org.jeecg.modules.business.entity.AttAttachment;
import org.jeecg.modules.business.entity.AttInStorehouse;
import org.jeecg.modules.business.service.IAttInStorehouseService;

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
 * @Description: 入库信息表
 * @Author: jeecg-boot
 * @Date:   2020-03-02
 * @Version: V1.0
 */
@Api(tags="入库信息表")
@RestController
@RequestMapping("/business/attInStorehouse")
@Slf4j
public class AttInStorehouseController extends JeecgController<AttInStorehouse, IAttInStorehouseService> {
	@Autowired
	private IAttInStorehouseService attInStorehouseService;
	
	/**
	 * 分页列表查询
	 *
	 * @param attInStorehouse
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "入库信息表-分页列表查询")
	@ApiOperation(value="入库信息表-分页列表查询", notes="入库信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AttInStorehouse attInStorehouse,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
//		QueryWrapper<AttInStorehouse> queryWrapper = QueryGenerator.initQueryWrapper(attInStorehouse, req.getParameterMap());
//		Page<AttInStorehouse> page = new Page<AttInStorehouse>(pageNo, pageSize);
//		IPage<AttInStorehouse> pageList = attInStorehouseService.page(page, queryWrapper);

		LambdaQueryWrapper<AttInStorehouse> wrapper = Wrappers.lambdaQuery(attInStorehouse);
		Page<AttInStorehouse> page = new Page<>(pageNo, pageSize);
		IPage<AttInStorehouse> pageList = attInStorehouseService.findPages(page, wrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param attInStorehouse
	 * @return
	 */
	@AutoLog(value = "入库信息表-添加")
	@ApiOperation(value="入库信息表-添加", notes="入库信息表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AttInStorehouse attInStorehouse) {
		attInStorehouseService.save(attInStorehouse);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param attInStorehouse
	 * @return
	 */
	@AutoLog(value = "入库信息表-编辑")
	@ApiOperation(value="入库信息表-编辑", notes="入库信息表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AttInStorehouse attInStorehouse) {
		attInStorehouseService.updateById(attInStorehouse);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "入库信息表-通过id删除")
	@ApiOperation(value="入库信息表-通过id删除", notes="入库信息表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		attInStorehouseService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "入库信息表-批量删除")
	@ApiOperation(value="入库信息表-批量删除", notes="入库信息表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.attInStorehouseService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "入库信息表-通过id查询")
	@ApiOperation(value="入库信息表-通过id查询", notes="入库信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AttInStorehouse attInStorehouse = attInStorehouseService.getById(id);
		if(attInStorehouse==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(attInStorehouse);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param attInStorehouse
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AttInStorehouse attInStorehouse) {
        return super.exportXls(request, attInStorehouse, AttInStorehouse.class, "入库信息表");
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
        return super.importExcel(request, response, AttInStorehouse.class);
    }

}
