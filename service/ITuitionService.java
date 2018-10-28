package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.ProfessionalStuent;
import trlygjj.tanruiliyigenjinjin.domain.Tuition;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * 学员缴费表
 */
public interface ITuitionService {

    int deleteByPrimaryKey(Long id);

    int insert(Tuition record);

    Tuition selectByPrimaryKey(Long id);

    List<Tuition> selectAll();

    int updateByPrimaryKey(Tuition record);

    PageResult query(QueryObject qo);

    /**
     * 查询出未缴费的学员的集合
     * @return
     */
    List<ProfessionalStuent> selectUnpaidstudent();

    /**
     * 根据正式学员id查询需要缴费的信息
     * @param professionStudentId
     * @return
     */
    Tuition selectTuitionStudentByprofessionStudentById(Long professionStudentId);
}
