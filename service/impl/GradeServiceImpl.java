package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Grade;
import trlygjj.tanruiliyigenjinjin.mapper.GradeMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.IGradeService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class GradeServiceImpl implements IGradeService {
    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return gradeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Grade record) {
        return gradeMapper.insert(record);
    }

    @Override
    public Grade selectByPrimaryKey(Long id) {
        return gradeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Grade> selectAll() {
        return gradeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Grade record) {
        return gradeMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = gradeMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Grade> rows = gradeMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    public List<Grade> listAllInterentGrade() {
       return gradeMapper.listAllInterentGrade();
    }
}
