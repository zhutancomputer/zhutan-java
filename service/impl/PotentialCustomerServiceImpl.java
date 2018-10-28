package trlygjj.tanruiliyigenjinjin.service.impl;


import jxl.CellView;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.*;
import trlygjj.tanruiliyigenjinjin.mapper.*;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.IPotentialCustomerService;

import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class PotentialCustomerServiceImpl implements IPotentialCustomerService {
    @Autowired
    private PotentialCustomerMapper potentialCustomerMapper;
    @Autowired
    private CustomerTransferRecordMapper customerTransferRecordMapper;

    @Autowired
    private ProfessionalStuentMapper professionalStuentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private TuitionMapper tuitionMapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return potentialCustomerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PotentialCustomer record) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        //设置录入人的和录入的时间
        record.setInputUser(employee);
        record.setInputTime(new Date());
        //设置建档时间和营销人员
        record.setDocumentDate(new Date());
        record.setMarketer(employee);
        System.out.println(record);
        return potentialCustomerMapper.insert(record);
    }

    @Override
    public PotentialCustomer selectByPrimaryKey(Long id) {
        return potentialCustomerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PotentialCustomer> selectAll() {
        return potentialCustomerMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(PotentialCustomer record) {
        return potentialCustomerMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = potentialCustomerMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<PotentialCustomer> rows = potentialCustomerMapper.queryForList(qo);
        return new PageResult(total,rows);
    }
    //放入资源池
    public void changeInpoolState(Long id) {
        potentialCustomerMapper.changeInpoolState(PotentialCustomer.INPOOL_STATE,id);
    }
    //考试登记
    public void tsetRegistration(PotentialCustomer potentialCustomer) {
        potentialCustomer.setNeedTest(PotentialCustomer.NEED_TEST);
        potentialCustomer.setTestResult(PotentialCustomer.FAIL_THE_TEST);
      potentialCustomerMapper.tsetRegistration(potentialCustomer);

    }
    //指派客户
    public void assign(CustomerTransferRecord customerTransferRecord) {
        String targetFollowerName = customerTransferRecord.getTargetFollowerName();
        Employee targer = employeeMapper.queryUsername(targetFollowerName);

        //查询当前客户的信息
        PotentialCustomer potentialCustomer = potentialCustomerMapper.selectByPrimaryKey(customerTransferRecord.getId());
        //设置移交信息
        Employee employee = potentialCustomer.getFollower();
        customerTransferRecord.setTransferDate(new Date());
        if (employee!=null){

            customerTransferRecord.setOriginalFollowerName(employee.getUsername());
        }

        //保存到客户移交记录

        customerTransferRecordMapper.insert(customerTransferRecord);
        //修改潜在客户表中的跟踪人
        //修改是否在资源池的状态
        potentialCustomer.setInpoolState(PotentialCustomer.OUTPOOL_STATE);
        potentialCustomer.setFollower(targer);
        potentialCustomerMapper.changeToOutPoll(potentialCustomer);
    }

    //接收客户
    public void receive(CustomerTransferRecord customerTransferRecord) {
        Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();

        //查询当前客户的信息
        PotentialCustomer potentialCustomer = potentialCustomerMapper.selectByPrimaryKey(customerTransferRecord.getId());
        //设置移交信息
        Employee follower = potentialCustomer.getFollower();
        customerTransferRecord.setTransferDate(new Date());
        if (follower!=null){
            customerTransferRecord.setOriginalFollowerName(follower.getUsername());
        }
        customerTransferRecord.setTransferDate(new Date());
        Employee employee1 = (Employee) SecurityUtils.getSubject().getPrincipal();
        customerTransferRecord.setTargetFollowerName(employee1.getUsername());
        //保存到客户移交记录
        customerTransferRecordMapper.insert(customerTransferRecord);
        ////当前登录的人设置为潜在客户的跟踪人
        potentialCustomer.setInpoolState(PotentialCustomer.OUTPOOL_STATE);
        potentialCustomer.setFollower(currentUser);
        potentialCustomerMapper.changeToOutPoll(potentialCustomer);
    }
    //转正
    public void positive(ProfessionalStuent professionalStuent) {
        potentialCustomerMapper.positive(PotentialCustomer.POSITIVED_COSTOMER,professionalStuent.getId());
        //设置转正时间
        professionalStuent.setPositiveDate(new Date());
        professionalStuentMapper.insert(professionalStuent);
        Tuition tuition = new Tuition();
        //学员对象
        tuition.setProfessionalstudent(professionalStuent);
        //基本优惠
        tuition.setBasicdiscounts(professionalStuent.getTuition().getBasicdiscounts());
        //其他优惠
        tuition.setOtherdiscounts(professionalStuent.getTuition().getOtherdiscounts());
        //已缴学费paidupcapital
        tuition.setPaidupcapital(professionalStuent.getTuition().getDeposit());
        //未缴学费 unpaidaltuition = 总金额-基础优惠-其他优惠-定金
        tuition.setUnpaidaltuition(professionalStuent.getTuition().getOriginaltuition().
                subtract(professionalStuent.getTuition().getBasicdiscounts()).
                subtract(professionalStuent.getTuition().getOtherdiscounts()).
                subtract(professionalStuent.getTuition().getDeposit()));
        tuition.setDeadline(professionalStuent.getTuition().getDeadline());
        tuition.setPayment(professionalStuent.getPayment());
        tuition.setExplains(professionalStuent.getTuition().getExplains());
        tuition.setDeadline(professionalStuent.getTuition().getDeadline());
        tuition.setOriginaltuition(professionalStuent.getTuition().getOriginaltuition());

        //保存缴费表的定金
        tuition.setDeposit(professionalStuent.getTuition().getDeposit());
        //设置缴费表的班级关系
        tuition.setGrade(professionalStuent.getInterestClass());

        tuitionMapper.insert(tuition);


        // 更新保存正式学员与缴费表之间的关系
        professionalStuentMapper.updateProfessionalStudentRoalitTuition(professionalStuent.getId() , tuition.getId());
    }


    public void exportJxl() throws Exception{
        List<PotentialCustomer> potentialCustomers = potentialCustomerMapper.selectAll();
        //创建工作簿
        WritableWorkbook workbook = Workbook.createWorkbook(new File("d:/exportJxl.xls"));
        WritableSheet sheet = workbook.createSheet("潜在客户表", 0);
        //设置样式
        WritableFont wf2   =   new   WritableFont(WritableFont.ARIAL,14,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
        WritableCellFormat wcfTitle = new WritableCellFormat(wf2);
        wcfTitle.setBackground(jxl.format.Colour.IVORY);  //象牙白
        wcfTitle.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //BorderLineStyle边框
        wcfTitle.setAlignment(Alignment.CENTRE); //设置垂直对齐

        CellView navCellView = new CellView();
        navCellView.setAutosize(true); //设置自动大小


        String[] heads = {"姓名", "客户电话", "意向程度", "意向校区"};
        Label label = null;
        for(int i=0;i<heads.length;i++){
            sheet.setColumnView(i, navCellView);//根据内容自动设置列宽
            label = new Label(i, 0, heads[i],wcfTitle);
            sheet.addCell(label);
        }

        for (int i =1;i<=potentialCustomers.size();i++){


             label = new Label(0, i, potentialCustomers.get(i-1).getName(),wcfTitle);
            sheet.addCell(label);
            label = new Label(1, i, potentialCustomers.get(i-1).getTell(),wcfTitle);
            sheet.addCell(label);
            if(potentialCustomers.get(i-1).getInterestDegree()==null){
                label = new Label(2, i, "未知",wcfTitle);
                sheet.addCell(label);

            }else {
                label = new Label(2, i, potentialCustomers.get(i-1).getInterestDegree().getName(),wcfTitle);
                sheet.addCell(label);
            }

            if (potentialCustomers.get(i-1).getInterestCampu()==null){
                label = new Label(3, i, "未指明",wcfTitle);
                sheet.addCell(label);
            }else{
                label = new Label(3, i, potentialCustomers.get(i-1).getInterestCampu().getName(),wcfTitle);
                sheet.addCell(label);
            }


        }


        workbook.write();
        workbook.close();

    }

    public PotentialCustomer validateTell(String tell) {

        return potentialCustomerMapper.validateTell(tell);
    }

    public PotentialCustomer validateQq(String qq) {
        return potentialCustomerMapper.validateQq(qq);
    }


}




