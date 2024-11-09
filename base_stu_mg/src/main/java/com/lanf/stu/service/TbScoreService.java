package com.lanf.stu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lanf.stu.model.TbScore;
import com.lanf.stu.vo.TbScoreQueryVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface TbScoreService extends IService<TbScore> {
    IPage<TbScore> selectPage(Page<TbScore> pageParam, TbScoreQueryVo queryVo);

    List<TbScore> queryList(TbScoreQueryVo queryVo);

    public void exportData(HttpServletResponse response);

    public void importData(MultipartFile multipartFile);

    public boolean save(TbScore tbScore);

    public boolean updateById(TbScore tbScore);

    public TbScore getById(String id);

    public List<TbScore> getByIds(List<String> ids);
}
