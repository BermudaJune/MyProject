package com.lanf.stu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanf.common.result.Result;
import com.lanf.stu.model.TbStudent;
import com.lanf.stu.service.TbStudentService;
import com.lanf.stu.vo.TbStudentQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@Api(tags = "学生信息")
@RestController
@RequestMapping("/stu/tbStudent")
public class TbStudentController {
    @Autowired
    private TbStudentService tbStudentService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(@ApiParam(name = "page", value = "当前页码", required = true)
                        @PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable Long limit,
                        @ApiParam(name = "tbStudentQueryVo", value = "查询对象", required = false)
                        TbStudentQueryVo tbStudentQueryVo) {
        Page<TbStudent> pageParam = new Page<>(page, limit);
        IPage<TbStudent> pageModel = tbStudentService.selectPage(pageParam, tbStudentQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "查询列表")
    @GetMapping("/list")
    public Result list(@ApiParam(name = "tbStudentQueryVo", value = "查询对象", required = false)
                       TbStudentQueryVo tbStudentQueryVo) {
        List<TbStudent> list = tbStudentService.queryList(tbStudentQueryVo);
        return Result.ok(list);
    }

    @ApiOperation(value = "所有学生信息列表")
    @GetMapping("findAll")
    public Result findAllTbStudent() {
        //调用service的方法实现查询所有的操作
        List<TbStudent> list = tbStudentService.list(null);
        return Result.ok(list);
    }

    @ApiOperation(value = "获取学生信息")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        TbStudent tbStudent = tbStudentService.getById(id);
        return Result.ok(tbStudent);
    }

    @ApiOperation(value = "获取学生信息集合")
    @PostMapping("/getByIds")
    public Result getByIds(@RequestBody List<String> idList) {
        List<TbStudent> list = tbStudentService.getByIds(idList);
        return Result.ok(list);
    }

    @ApiOperation(value = "保存学生信息")
    @PostMapping("/save")
    public Result save(@RequestBody TbStudent tbStudent) {
        tbStudentService.save(tbStudent);
        return Result.ok();
    }

    @ApiOperation(value = "更新学生信息")
    @PutMapping("/update")
    public Result updateById(@RequestBody TbStudent tbStudent) {
        tbStudentService.updateById(tbStudent);
        return Result.ok();
    }

    @ApiOperation(value = "删除学生信息")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        tbStudentService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        boolean b = tbStudentService.removeByIds(idList);
        if (b) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "导出学生信息")
    @GetMapping("/export")
    public void exportData(HttpServletResponse response) {
        this.tbStudentService.exportData(response);
    }

    @ApiOperation(value = "导入学生信息")
    @PostMapping("/import")
    public Result importUser(@RequestParam("files") MultipartFile files) {
        this.tbStudentService.importData(files);
        return Result.ok();
    }
}
