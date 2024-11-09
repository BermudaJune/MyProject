package com.lanf.stu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanf.common.result.Result;
import com.lanf.stu.model.TbScore;
import com.lanf.stu.service.TbScoreService;
import com.lanf.stu.vo.TbScoreQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@Api(tags = "成绩")
@RestController
@RequestMapping("/stu/tbScore")
public class TbScoreController {
    @Autowired
    private TbScoreService tbScoreService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(@ApiParam(name = "page", value = "当前页码", required = true)
                        @PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable Long limit,
                        @ApiParam(name = "tbScoreQueryVo", value = "查询对象", required = false)
                        TbScoreQueryVo tbScoreQueryVo) {
        Page<TbScore> pageParam = new Page<>(page, limit);
        IPage<TbScore> pageModel = tbScoreService.selectPage(pageParam, tbScoreQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "查询列表")
    @GetMapping("/list")
    public Result list(@ApiParam(name = "tbScoreQueryVo", value = "查询对象", required = false)
                       TbScoreQueryVo tbScoreQueryVo) {
        List<TbScore> list = tbScoreService.queryList(tbScoreQueryVo);
        return Result.ok(list);
    }

    @ApiOperation(value = "所有成绩列表")
    @GetMapping("findAll")
    public Result findAllTbScore() {
        //调用service的方法实现查询所有的操作
        List<TbScore> list = tbScoreService.list(null);
        return Result.ok(list);
    }

    @ApiOperation(value = "获取成绩")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        TbScore tbScore = tbScoreService.getById(id);
        return Result.ok(tbScore);
    }

    @ApiOperation(value = "获取成绩集合")
    @PostMapping("/getByIds")
    public Result getByIds(@RequestBody List<String> idList) {
        List<TbScore> list = tbScoreService.getByIds(idList);
        return Result.ok(list);
    }

    @ApiOperation(value = "保存成绩")
    @PostMapping("/save")
    public Result save(@RequestBody TbScore tbScore) {
        tbScoreService.save(tbScore);
        return Result.ok();
    }

    @ApiOperation(value = "更新成绩")
    @PutMapping("/update")
    public Result updateById(@RequestBody TbScore tbScore) {
        tbScoreService.updateById(tbScore);
        return Result.ok();
    }

    @ApiOperation(value = "删除成绩")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        tbScoreService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        boolean b = tbScoreService.removeByIds(idList);
        if (b) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "导出成绩")
    @GetMapping("/export")
    public void exportData(HttpServletResponse response) {
        this.tbScoreService.exportData(response);
    }

    @ApiOperation(value = "导入成绩信息")
    @PostMapping("/import")
    public Result importUser(@RequestParam("files") MultipartFile files) {
        this.tbScoreService.importData(files);
        return Result.ok();
    }
}
