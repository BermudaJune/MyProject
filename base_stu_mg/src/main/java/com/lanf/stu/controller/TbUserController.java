package com.lanf.stu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanf.common.result.Result;
import com.lanf.common.util.JwtHelper;
import com.lanf.stu.model.TbUser;
import com.lanf.stu.service.TbUserService;
import com.lanf.stu.vo.LoginVo;
import com.lanf.stu.vo.TbUserQueryVo;
import com.lanf.system.easyexcel.ExcelHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lanf.system.exception.LanfException;
import com.lanf.common.result.ResultCodeEnum;

@CrossOrigin
@Api(tags = "用户表")
@RestController
@RequestMapping("/stu/tbUser")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;

    @Resource
    private ExcelHandler excelHandler;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(@ApiParam(name = "page", value = "当前页码", required = true)
                        @PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable Long limit,
                        @ApiParam(name = "tbUserQueryVo", value = "查询对象", required = false)
                        TbUserQueryVo tbUserQueryVo) {
        Page<TbUser> pageParam = new Page<>(page, limit);
        IPage<TbUser> pageModel = tbUserService.selectPage(pageParam, tbUserQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",loginVo.getUsername());
        TbUser tbUser = tbUserService.getOne(queryWrapper);
        if (null == tbUser) {
            throw new LanfException(ResultCodeEnum.ACCOUNT_ERROR);
        }
        if (!loginVo.getPassword().equals(tbUser.getPassword())) {
            throw new LanfException(ResultCodeEnum.PASSWORD_ERROR);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("token", JwtHelper.createToken(tbUser.getId(), tbUser.getUserName()));
        return Result.ok(map);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/info")
    public Result info(HttpServletRequest request) {
        String token = request.getParameter("token");
        String username = JwtHelper.getUsername(token);
        Map map = new HashMap();
        map.put("name", username);
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

    @PostMapping("/logout")
    public Result logout(){
        return Result.ok();
    }

    @ApiOperation(value = "查询列表")
    @GetMapping("/list")
    public Result list(@ApiParam(name = "tbUserQueryVo", value = "查询对象", required = false)
                       TbUserQueryVo tbUserQueryVo) {
        List<TbUser> list = tbUserService.queryList(tbUserQueryVo);
        return Result.ok(list);
    }

    @ApiOperation(value = "所有用户表列表")
    @GetMapping("findAll")
    public Result findAllTbUser() {
        //调用service的方法实现查询所有的操作
        List<TbUser> list = tbUserService.list(null);
        return Result.ok(list);
    }

    @ApiOperation(value = "获取用户表")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        TbUser tbUser = tbUserService.getById(id);
        return Result.ok(tbUser);
    }

    @ApiOperation(value = "获取用户表集合")
    @PostMapping("/getByIds")
    public Result getByIds(@RequestBody List<String> idList) {
        List<TbUser> list = tbUserService.getByIds(idList);
        return Result.ok(list);
    }

    @ApiOperation(value = "保存用户表")
    @PostMapping("/save")
    public Result save(@RequestBody TbUser tbUser) {
        tbUserService.save(tbUser);
        return Result.ok();
    }

    @ApiOperation(value = "更新用户表")
    @PutMapping("/update")
    public Result updateById(@RequestBody TbUser tbUser) {
        tbUserService.updateById(tbUser);
        return Result.ok();
    }

    @ApiOperation(value = "删除用户表")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        tbUserService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        boolean b = tbUserService.removeByIds(idList);
        if (b) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "导出用户表")
    @GetMapping("/export")
    public void exportData(HttpServletResponse response) {
        this.tbUserService.exportData(response);
    }
}
