package com.lanf.stu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanf.stu.model.TbStudent;
import com.lanf.stu.vo.TbStudentQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TbStudentMapper extends BaseMapper<TbStudent> {
    IPage<TbStudent> selectPage(Page<TbStudent> page, @Param("vo") TbStudentQueryVo tbStudentQueryVo);

    List<TbStudent> queryList(@Param("vo") TbStudentQueryVo tbStudentQueryVo);
}
