package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.TuitionDetail;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * 学员缴费表明细
 */
public interface ITuitionDetailService {

    int deleteByPrimaryKey(Long id);

    int insert(TuitionDetail record);

    TuitionDetail selectByPrimaryKey(Long id);

    List<TuitionDetail> selectAll();

    int updateByPrimaryKey(TuitionDetail record);

    PageResult query(QueryObject qo);

    /**
     * 审核学员的缴费情况
     * @param id
     */
    void updateTuitionDetailState(Long id);
}
