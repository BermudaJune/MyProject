package com.lanf.stu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lanf.stu.model.TbClass;
import com.lanf.stu.vo.TbClassQueryVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface TbClassService extends IService<TbClass> {
    IPage<TbClass> selectPage(Page<TbClass> pageParam, TbClassQueryVo queryVo);

    List<TbClass> queryList(TbClassQueryVo queryVo);

    public void exportData(HttpServletResponse response);

    public boolean save(TbClass tbClass);

    public boolean updateById(TbClass tbClass);

    public TbClass getById(String id);

    public List<TbClass> getByIds(List<String> ids);
}
