package trlygjj.tanruiliyigenjinjin.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Employee;
import trlygjj.tanruiliyigenjinjin.domain.LeaveStudent;
import trlygjj.tanruiliyigenjinjin.domain.ProfessionalStuent;
import trlygjj.tanruiliyigenjinjin.domain.Refund;
import trlygjj.tanruiliyigenjinjin.mapper.LeaveStudentMapper;
import trlygjj.tanruiliyigenjinjin.mapper.RefundMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.ILeaveStudentService;
import trlygjj.tanruiliyigenjinjin.service.IProfessionalStuentService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
//学员流失
@Service
public class LeaveStudentServiceImpl implements ILeaveStudentService {
    @Autowired
    private LeaveStudentMapper leaveStudentMapper;
    // 正式学员
    @Autowired
    private IProfessionalStuentService professionalStuentService;
    @Autowired
    private RefundMapper refundMapper;


    /**
     * 保存学员流失
     * @param record
     */
    @Override
    public void insert(LeaveStudent record) {
        Employee inputuser = (Employee) SecurityUtils.getSubject().getPrincipal();
        // 根据id查询出对应的学生对象
        ProfessionalStuent ps = professionalStuentService.selectByPrimaryKey(record.getProfessionalStudent().getId());
       if(ps != null ){
           // 不是流失状态才能流失
           if(ps.getState() != ProfessionalStuent.STATE_LEAVE){

               //修改该学员的状态
               ps.setState(ProfessionalStuent.STATE_LEAVE);
               professionalStuentService.updateProfessionalStuentState(ps);

               // 保存流失学员信息

               //设置录入人信息
               record.setInputuser(inputuser);
               // 保存休学学员的班级
               record.setGrade(ps.getInterestClass());
               // 保存休学学员的销售对象
               record.setMarketer(ps.getMarketer());
               leaveStudentMapper.insert(record);


               // 判断流失学员是否要退款
               if(record.getIsRefund() == 1){
                   Refund refund = new Refund();
                   // 先根据学生对象查询流失学员
                   LeaveStudent leaveStudent =  leaveStudentMapper.selectLeaveStudentByProfessionalStuentId(ps.getId());
                   refund.setLeavestudent(leaveStudent);
                   //保存录入人
                   refund.setInputuser(inputuser);
                   refund.setRefundName(ps.getName());
                   refund.setGrade(leaveStudent.getGrade());
                   refund.setRefunduser(ps.getMarketer());
                   refund.setRefunddate(new Date());
                   refundMapper.insert(refund);


               }
           }

       }

    }

    /**
     * 查询单个学员流失
     * @param id
     * @return
     */
    @Override
    public LeaveStudent selectByPrimaryKey(Long id) {
        return leaveStudentMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部学员流失
     * @return
     */
    @Override
    public List<LeaveStudent> selectAll() {
        return leaveStudentMapper.selectAll();
    }


    /**
     * 分页查询
     * @param qo
     * @return
     */
    public PageResult query(QueryObject qo) {
        int total = leaveStudentMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<LeaveStudent> rows = leaveStudentMapper.queryForList(qo);
        return new PageResult(total,rows);
    }

    /**
     * 审核流失学员的转态
     */
    public void updateLeaveStudentState(Long leaveStudentId) {
        // 查出需要审核休学的学员
        LeaveStudent leaveStudent = leaveStudentMapper.selectByPrimaryKey(leaveStudentId);
        if(leaveStudent != null && leaveStudent.getLeaceState() == LeaveStudent.STATE_UNDER){
            // 获前审核人的信息
            Employee auditoruser = (Employee) SecurityUtils.getSubject().getPrincipal();
            leaveStudent.setAuditoruser(auditoruser);
            leaveStudent.setLeaceState(LeaveStudent.STATE_PUSS);
            leaveStudentMapper.updateLeaveStudentState(leaveStudent);
        }
    }


}
