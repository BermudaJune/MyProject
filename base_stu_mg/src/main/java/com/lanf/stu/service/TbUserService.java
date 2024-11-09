package com.lanf.stu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lanf.stu.model.TbUser;
import com.lanf.stu.vo.TbUserQueryVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface TbUserService extends IService<TbUser> {
    IPage<TbUser> selectPage(Page<TbUser> pageParam, TbUserQueryVo queryVo);

    List<TbUser> queryList(TbUserQueryVo queryVo);

    public void exportData(HttpServletResponse response);

    public boolean save(TbUser tbUser);

    public boolean updateById(TbUser tbUser);

    public TbUser getById(String id);

    public List<TbUser> getByIds(List<String> ids);
}
