package com.lanf.stu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lanf.stu.model.TbStudent;
import com.lanf.stu.vo.TbStudentQueryVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

public interface TbStudentService extends IService<TbStudent> {
    IPage<TbStudent> selectPage(Page<TbStudent> pageParam, TbStudentQueryVo queryVo);
    List<TbStudent> queryList(TbStudentQueryVo queryVo);
    public void exportData(HttpServletResponse response);
    public boolean save(TbStudent tbStudent);
    public boolean updateById(TbStudent tbStudent);
    public TbStudent getById(String id);
    public List<TbStudent> getByIds(List<String> ids);
    public void importData(MultipartFile multipartFile);
}
