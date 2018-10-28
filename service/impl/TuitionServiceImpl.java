package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.ProfessionalStuent;
import trlygjj.tanruiliyigenjinjin.domain.Tuition;
import trlygjj.tanruiliyigenjinjin.mapper.TuitionMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.ITuitionService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 学员缴费表
 */
@Service
public class TuitionServiceImpl implements ITuitionService {
    @Autowired
    private TuitionMapper tuitionMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return tuitionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Tuition record) {
        return tuitionMapper.insert(record);
    }

    @Override
    public Tuition selectByPrimaryKey(Long id) {
        return tuitionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Tuition> selectAll() {
        return tuitionMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Tuition record) {
        return tuitionMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = tuitionMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Tuition> rows = tuitionMapper.queryForList(qo);
        return new PageResult(total,rows);
    }

    /**
     * 查询出未缴费的学员
     *
     * @return
     */
    public List<ProfessionalStuent> selectUnpaidstudent() {


        List<ProfessionalStuent> pss = new ArrayList<>();
        List<Tuition>  tuitions = tuitionMapper.selectUnpaidstudent();
        for (Tuition tuition : tuitions) {
            pss.add(tuition.getProfessionalstudent());
        }

        return pss;
    }

    /**
     * 根据正式学员id查询需要缴费的信息
     *
     * @param professionStudentId
     * @return
     */
    public Tuition selectTuitionStudentByprofessionStudentById(Long professionStudentId) {
        Tuition tuition =  tuitionMapper.selectTuitionStudentByprofessionStudentById(professionStudentId);
        return tuition;
    }
}
