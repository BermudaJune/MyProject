package com.lanf.stu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanf.stu.model.TbUser;
import com.lanf.stu.vo.TbUserQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TbUserMapper extends BaseMapper<TbUser> {
    IPage<TbUser> selectPage(Page<TbUser> page, @Param("vo") TbUserQueryVo tbUserQueryVo);

    List<TbUser> queryList(@Param("vo") TbUserQueryVo tbUserQueryVo);
}
