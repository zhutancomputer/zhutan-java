package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.ProfessionalStuent;
import trlygjj.tanruiliyigenjinjin.domain.TransferRecord;
import trlygjj.tanruiliyigenjinjin.mapper.ProfessionalStuentMapper;
import trlygjj.tanruiliyigenjinjin.mapper.TransferRecordMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.ITransferRecordService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 学员转班业务层
 */
@Service
public class TransferRecordServiceImpl implements ITransferRecordService {
    @Autowired
    private TransferRecordMapper transferRecordMapper;
    // 正式学员
    @Autowired
    private ProfessionalStuentMapper professionalStuentMapper;

    @Override
    public int insert(TransferRecord record) {
        Long id = record.getProfessionalStudent().getId();
        ProfessionalStuent ps = professionalStuentMapper.selectByPrimaryKey(id);
        int state = ps.getState();

        if(state != ProfessionalStuent.STATE_TRANSFER &&
                state != ProfessionalStuent.STATE_LEAVE){

            // 将学员的状态改变为中班中状态
            ps.setState(ProfessionalStuent.STATE_TRANSFER);
            ps.setInterestClass(record.getOldgrade());
            professionalStuentMapper.updateProfessionalStuentState(ps);
            record.setMarketer(ps.getMarketer());


        }
        record.setTuition(ps.getTuition());
        return transferRecordMapper.insert(record);
    }

    public TransferRecord selectByPrimaryKey(Long id) {
        return transferRecordMapper.selectByPrimaryKey(id);
    }

    public List<TransferRecord> selectAll() {
        return transferRecordMapper.selectAll();
    }


    @Override
    public PageResult query(QueryObject qo) {
        int total = transferRecordMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<TransferRecord> rows = transferRecordMapper.queryForList(qo);
        return new PageResult(total,rows);
    }

    /**
     * 审核转班学员的转态
     */
    public void updateLeaveStudentState(Long id) {
        // 查出需要审核转班的学员
        TransferRecord transferRecord = transferRecordMapper.selectByPrimaryKey(id);
        if(transferRecord != null && transferRecord.getSatate() == TransferRecord.STATE_UNDER){
            // 为正式学员改变状态和设置新的班级
            ProfessionalStuent ps = transferRecord.getProfessionalStudent();
            ps.setState(ProfessionalStuent.STATE_READING);
            ps.setInterestClass(transferRecord.getNewgrade());
            professionalStuentMapper.updateProfessionalStuentState(ps);

            //设置转学的状态
            transferRecord.setSatate(TransferRecord.STATE_PUSS);
            //设置转班学员的转班时间
            transferRecord.setTransferrecordDate(new Date());
            transferRecordMapper.transferRecordState(transferRecord);
        }
    }
}
