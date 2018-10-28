package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Employee;
import trlygjj.tanruiliyigenjinjin.domain.ProfessionalStuent;
import trlygjj.tanruiliyigenjinjin.domain.Tuition;
import trlygjj.tanruiliyigenjinjin.domain.TuitionDetail;
import trlygjj.tanruiliyigenjinjin.mapper.ProfessionalStuentMapper;
import trlygjj.tanruiliyigenjinjin.mapper.TuitionDetailMapper;
import trlygjj.tanruiliyigenjinjin.mapper.TuitionMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.ITuitionDetailService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * 学员缴费表明细
 */
@Service
public class TuitionDetailServiceImpl implements ITuitionDetailService {
    @Autowired
    private TuitionDetailMapper tuitionDetailMapper;
    @Autowired
    private ProfessionalStuentMapper professionalStuentMapper;
    @Autowired
    private TuitionMapper tuitionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tuitionDetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TuitionDetail record) {
        //查出销售人员
        ProfessionalStuent ps = professionalStuentMapper.selectByPrimaryKey(record.getProfessionalstuent().getId());
        Employee marketer = ps.getMarketer();
        record.setMarket(marketer);


        /* 修改缴费的表格信息*/
        //获取缴费的金额
        BigDecimal amountcollected = record.getAmountcollected();

        Tuition currentTuition = ps.getTuition();



        // 改变当前学员的未缴学费
        currentTuition.setUnpaidaltuition(currentTuition.getUnpaidaltuition().subtract(record.getAmountcollected()));
        //改变该学员你的已缴学费
        currentTuition.setPaidupcapital(currentTuition.getPaidupcapital().add(record.getAmountcollected()));

        //更新该学员的缴费信息
        tuitionMapper.updateCurrentStudentTuition(currentTuition);



        //将缴费对象设置进缴费明细中
        record.setTuition(currentTuition);

        return tuitionDetailMapper.insert(record);
    }

    @Override
    public TuitionDetail selectByPrimaryKey(Long id) {
        return tuitionDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TuitionDetail> selectAll() {
        return tuitionDetailMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(TuitionDetail record) {
        return tuitionDetailMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = tuitionDetailMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<TuitionDetail> rows = tuitionDetailMapper.queryForList(qo);
        return new PageResult(total,rows);
    }

    /**
     * 审核学员的缴费情况
     *
     * @param id
     */
    public void updateTuitionDetailState(Long id) {
        // 审核缴费信息
        TuitionDetail tuitionDetail = tuitionDetailMapper.selectByPrimaryKey(id);
        if(tuitionDetail.getState() == TuitionDetail.STATE_UNDER){
            tuitionDetail.setState(TuitionDetail.STATE_PUSS);
            tuitionDetailMapper.updateTuitionDetailState(tuitionDetail);
        }


    }
}
