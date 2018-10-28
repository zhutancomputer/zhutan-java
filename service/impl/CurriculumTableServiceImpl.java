package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.CurriculumTable;
import trlygjj.tanruiliyigenjinjin.mapper.CurriculumTableMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.ICurriculumTableService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class CurriculumTableServiceImpl implements ICurriculumTableService {
    @Autowired
    private CurriculumTableMapper curriculumTableMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return curriculumTableMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CurriculumTable record) {
        return curriculumTableMapper.insert(record);
    }

    @Override
    public CurriculumTable selectByPrimaryKey(Long id) {
        return curriculumTableMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CurriculumTable> selectAll() {
        return curriculumTableMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(CurriculumTable record) {
        return curriculumTableMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = curriculumTableMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<CurriculumTable> rows = curriculumTableMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    public List<CurriculumTable> selectCurriculumTableByDate(String  date) {
        return curriculumTableMapper.selectCurriculumTableByDate(date);
    }
}
