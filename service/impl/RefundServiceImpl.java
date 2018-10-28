package trlygjj.tanruiliyigenjinjin.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Employee;
import trlygjj.tanruiliyigenjinjin.domain.Refund;
import trlygjj.tanruiliyigenjinjin.mapper.RefundMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.IRefundService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 * 学员退款
 */
@Service
public class RefundServiceImpl implements IRefundService {
    @Autowired
    private RefundMapper refundMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return refundMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Refund record) {
        return refundMapper.insert(record);
    }

    @Override
    public Refund selectByPrimaryKey(Long id) {
        return refundMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Refund> selectAll() {
        return refundMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Refund record) {
        return refundMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = refundMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Refund> rows = refundMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    /**
     * 审核退款情况
     *
     * @param id
     */
    public void updateLeaveStudentState(Long id) {
        // 查询需要审核的信息
        Refund refund = refundMapper.selectByPrimaryKey(id);

        //审核对象
        if(refund != null && refund.getRefundamountstate() == Refund.STATE_UNDER){
            // 获取审核人的信息
            Employee auditor = (Employee) SecurityUtils.getSubject().getPrincipal();
            refund.setAuditor(auditor);
            refund.setRefundamountstate(Refund.STATE_PUSS);
            refundMapper.refundState(refund);
        }
    }
}
