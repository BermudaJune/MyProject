package com.lanf.stu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lanf.common.result.ResultCodeEnum;
import com.lanf.stu.mapper.TbStudentMapper;
import com.lanf.stu.model.TbClass;
import com.lanf.stu.model.TbStudent;
import com.lanf.stu.service.TbClassService;
import com.lanf.stu.service.TbStudentService;
import com.lanf.stu.vo.TbStudentExportVo;
import com.lanf.stu.vo.TbStudentQueryVo;
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
 * @description 学生信息 Service实现类
 * @date 2023-12-02 23:59:17
 */
@Transactional
@Service
public class TbStudentServiceImpl extends ServiceImpl
        <TbStudentMapper, TbStudent> implements TbStudentService {
    @Autowired
    private TbStudentMapper tbStudentMapper;
    @Resource
    private ExcelHandler excelHandler;

    @Autowired
    private TbClassService tbClassService;

    @Override
    public IPage<TbStudent> selectPage(Page<TbStudent> pageParam, TbStudentQueryVo tbStudentQueryVo) {
        return tbStudentMapper.selectPage(pageParam, tbStudentQueryVo);
    }

    @Override
    public List<TbStudent> queryList(TbStudentQueryVo tbStudentQueryVo) {
        List<TbStudent> result = tbStudentMapper.queryList(tbStudentQueryVo);
        return result;
    }

    @Override
    public boolean save(TbStudent tbStudent) {
        int result = this.tbStudentMapper.insert(tbStudent);
        return result > 0;
    }

    @Override
    public boolean updateById(TbStudent tbStudent) {
        int row = this.tbStudentMapper.updateById(tbStudent);
        if (row <= 0) {
            throw new LanfException(ResultCodeEnum.UPDATE_ERROR);
        }
        return row > 0;
    }

    @Override
    public TbStudent getById(String id) {
        TbStudent tbStudent = tbStudentMapper.selectById(id);
        return tbStudent;
    }

    @Override
    public List<TbStudent> getByIds(List<String> ids) {
        List<TbStudent> list = this.tbStudentMapper.selectBatchIds(ids);
        return list;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        List<TbStudent> list = this.selectPage(new Page<TbStudent>(1, 10000), new TbStudentQueryVo()).getRecords();
        List<TbStudentExportVo> expList = new ArrayList<>();
        list.forEach(po -> {
            TbStudentExportVo vo = new TbStudentExportVo();
            BeanUtils.copyProperties(po, vo);
            expList.add(vo);
        });
        try {
            this.excelHandler.exportExcel(response, expList, TbStudentExportVo.class, "学生信息数据", "学生信息数据");
        } catch (Exception e) {
            e.printStackTrace();
            throw new LanfException(9005, "导出失败");
        }
    }

    @Override
    public void importData(MultipartFile multipartFile) {
        List<TbStudent> saveList = new ArrayList<>();
        excelHandler.checkFile(multipartFile);
        try {
            List<Object> plist = new ArrayList<>();
            plist.add(new TbStudentExportVo());
            plist.add(new TbStudentExportVo());
            plist.add(new TbStudentExportVo());
            List<List<TbStudentExportVo>> voList = excelHandler.importExcels(multipartFile, 3, plist);
            if (!CollectionUtils.isEmpty(voList)) {
                voList.forEach(vo -> {
                    vo.forEach(v -> {
                        TbStudent tbStudent = new TbStudent();
                        BeanUtils.copyProperties(v, tbStudent);
                        String className = v.getClassName();
                        if (!StringUtils.isEmpty(className)) {
                            Function<Object, String> f = (o -> o.toString());
                            QueryWrapper queryWrapper = new QueryWrapper();
                            queryWrapper.select("id");
                            queryWrapper.eq("name", className);
                            String classId = tbClassService.getObj(queryWrapper, f);
                            if (StringUtils.isEmpty(classId)) {
                                TbClass tbClass = new TbClass();
                                tbClass.setName(className);
                                tbClassService.save(tbClass);
                                classId = tbClass.getId();
                            }
                            tbStudent.setClassId(classId);
                        }
                        String genderName = v.getGenderName();
                        if (!StringUtils.isEmpty(genderName)) {
                            tbStudent.setGender("男".equals(genderName) ? "1001" : "1002");
                        }
                        saveList.add(tbStudent);
                    });
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new LanfException(9004, "导入失败");
        }
        if (!CollectionUtils.isEmpty(saveList)) {
            this.saveBatch(saveList);
        }
    }
}
