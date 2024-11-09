package com.lanf.stu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanf.stu.model.TbScore;
import com.lanf.stu.vo.TbScoreQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface TbScoreMapper extends BaseMapper<TbScore> {
    IPage<TbScore> selectPage(Page<TbScore> page, @Param("vo") TbScoreQueryVo tbScoreQueryVo);

    List<TbScore> queryList(@Param("vo") TbScoreQueryVo tbScoreQueryVo);
}
