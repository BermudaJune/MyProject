package com.lanf.stu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lanf.common.result.ResultCodeEnum;
import com.lanf.stu.mapper.TbScoreMapper;
import com.lanf.stu.model.TbScore;
import com.lanf.stu.service.TbScoreService;
import com.lanf.stu.service.TbStudentService;
import com.lanf.stu.vo.TbScoreExportVo;
import com.lanf.stu.vo.TbScoreQueryVo;
import com.lanf.system.easyexcel.ExcelHandler;
import com.lanf.system.exception.LanfException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author tanlingfei
 * @version 1.0
 * @description 成绩 Service实现类
 * @date 2023-12-03 00:34:44
 */
@Transactional
@Service
public class TbScoreServiceImpl extends ServiceImpl
        <TbScoreMapper, TbScore> implements TbScoreService {
    @Autowired
    private TbScoreMapper tbScoreMapper;
    @Resource
    private ExcelHandler excelHandler;

    @Autowired
    private TbStudentService tbStudentService;

    @Override
    public IPage<TbScore> selectPage(Page<TbScore> pageParam, TbScoreQueryVo tbScoreQueryVo) {
        return tbScoreMapper.selectPage(pageParam, tbScoreQueryVo);
    }

    @Override
    public List<TbScore> queryList(TbScoreQueryVo tbScoreQueryVo) {
        List<TbScore> result = tbScoreMapper.queryList(tbScoreQueryVo);
        return result;
    }

    @Override
    public boolean save(TbScore tbScore) {
        int result = this.tbScoreMapper.insert(tbScore);
        return result > 0;
    }

    @Override
    public boolean updateById(TbScore tbScore) {
        int row = this.tbScoreMapper.updateById(tbScore);
        if (row <= 0) {
            throw new LanfException(ResultCodeEnum.UPDATE_ERROR);
        }
        return row > 0;
    }

    @Override
    public TbScore getById(String id) {
        TbScore tbScore = tbScoreMapper.selectById(id);
        return tbScore;
    }

    @Override
    public List<TbScore> getByIds(List<String> ids) {
        List<TbScore> list = this.tbScoreMapper.selectBatchIds(ids);
        return list;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        List<TbScore> list = this.selectPage(new Page<TbScore>(1, 10000), new TbScoreQueryVo()).getRecords();
        List<TbScoreExportVo> expList = new ArrayList<>();
        list.forEach(po -> {
            TbScoreExportVo vo = new TbScoreExportVo();
            BeanUtils.copyProperties(po, vo);
            expList.add(vo);
        });
        try {
            this.excelHandler.exportExcel(response, expList, TbScoreExportVo.class, "成绩数据", "成绩数据");
        } catch (Exception e) {
            e.printStackTrace();
            throw new LanfException(9005, "导出失败");
        }
    }

    @Override
    public void importData(MultipartFile multipartFile) {
        List<TbScore> saveList = new ArrayList<>();
        excelHandler.checkFile(multipartFile);
        try {
            List<Object> plist = new ArrayList<>();
            plist.add(new TbScoreExportVo());
            plist.add(new TbScoreExportVo());
            plist.add(new TbScoreExportVo());
            List<List<TbScoreExportVo>> voList = excelHandler.importExcels(multipartFile, 3, plist);
            if (!CollectionUtils.isEmpty(voList)) {
                voList.forEach(vo -> {
                    vo.forEach(v -> {
                        TbScore tbScore = new TbScore();
                        BeanUtils.copyProperties(v, tbScore);
                        String studentName = v.getStudentName();
                        if (!StringUtils.isEmpty(studentName)) {
                            Function<Object, String> f = (o -> o.toString());
                            QueryWrapper queryWrapper = new QueryWrapper();
                            queryWrapper.select("id");
                            queryWrapper.eq("name", studentName);
                            String stuId = tbStudentService.getObj(queryWrapper, f);
                            if (!StringUtils.isEmpty(stuId)) {
                                tbScore.setStudentId(stuId);
                            } else {
                                throw new LanfException(5240, "学生:" + studentName + ",在数据库中不存在");
                            }
                        }
                        saveList.add(tbScore);
                    });
                });
            }
        } catch (LanfException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new LanfException(9004, "导入失败");
        }
        if (!CollectionUtils.isEmpty(saveList)) {
            this.saveBatch(saveList);
        }
    }
}
