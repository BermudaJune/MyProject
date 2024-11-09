package com.lanf.stu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanf.stu.model.TbClass;
import com.lanf.stu.vo.TbClassQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TbClassMapper extends BaseMapper<TbClass> {
    IPage<TbClass> selectPage(Page<TbClass> page, @Param("vo") TbClassQueryVo tbClassQueryVo);

    List<TbClass> queryList(@Param("vo") TbClassQueryVo tbClassQueryVo);
}
