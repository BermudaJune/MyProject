package com.lanf.stu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lanf.common.result.ResultCodeEnum;
import com.lanf.stu.mapper.TbUserMapper;
import com.lanf.stu.model.TbUser;
import com.lanf.stu.service.TbUserService;
import com.lanf.stu.vo.TbUserExportVo;
import com.lanf.stu.vo.TbUserQueryVo;
import com.lanf.system.easyexcel.ExcelHandler;
import com.lanf.system.exception.LanfException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
/**
* @author tanlingfei
* @version 1.0
* @description 用户表 Service实现类
* @date 2023-12-18 21:28:38
*/
@Transactional
@Service
public class TbUserServiceImpl extends ServiceImpl
<TbUserMapper, TbUser> implements TbUserService {
    @Autowired
    private TbUserMapper tbUserMapper;
    @Resource
    private ExcelHandler excelHandler;

    @Override
    public IPage<TbUser> selectPage(Page<TbUser> pageParam, TbUserQueryVo tbUserQueryVo) {
        //QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        //return tbUserMapper.selectPage(pageParam,queryWrapper);
        return tbUserMapper.selectPage(pageParam,tbUserQueryVo);
    }

    @Override
    public List<TbUser> queryList(TbUserQueryVo tbUserQueryVo){
     List<TbUser> result = tbUserMapper.queryList(tbUserQueryVo);
     return result;
   }

    @Override
    public boolean save(TbUser tbUser){
        int result = this.tbUserMapper.insert(tbUser);
        return result>0;
    }
    @Override
    public boolean updateById(TbUser tbUser){
        int row = this.tbUserMapper.updateById(tbUser);
        if(row <= 0){
            throw new LanfException(ResultCodeEnum.UPDATE_ERROR);
         }
         return row>0;
    }
    @Override
    public TbUser getById(String id){
         TbUser tbUser = tbUserMapper.selectById(id);
         return tbUser;
    }
   @Override
   public List<TbUser> getByIds(List<String> ids) {
      List<TbUser> list = this.tbUserMapper.selectBatchIds(ids);
      return list;
   }
   @Override
   public void exportData(HttpServletResponse response) {
   List<TbUser> list = this.selectPage(new Page<TbUser>(1, 10000), new TbUserQueryVo()).getRecords();
   List<TbUserExportVo> expList = new ArrayList<>();
   list.forEach(po -> {
       TbUserExportVo vo = new TbUserExportVo();
       BeanUtils.copyProperties(po, vo);
          expList.add(vo);
      });
      try {
          this.excelHandler.exportExcel(response, expList, TbUserExportVo.class, "用户表数据", "用户表数据");
      } catch (Exception e) {
          e.printStackTrace();
          throw new LanfException(9005, "导出失败");
      }
   }
}
