package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.PotentialCustomer;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

/**
 * Created by user on 2018/7/10.
 */
public interface IExaminationService {
    int deleteByPrimaryKey(Long examinationId);



    int updateByPrimaryKey(PotentialCustomer potentialCustomer);

    PageResult query(QueryObject qo);

    /**
     * 改变考试结果的状态
     * @param potentialCustomer
     */
    void changeStateById(PotentialCustomer potentialCustomer);
}
