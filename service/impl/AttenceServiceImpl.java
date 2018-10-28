package trlygjj.tanruiliyigenjinjin.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.Attence;
import trlygjj.tanruiliyigenjinjin.domain.Employee;
import trlygjj.tanruiliyigenjinjin.mapper.AttenceMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.IAttenceService;
import trlygjj.tanruiliyigenjinjin.util.SignInException;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class AttenceServiceImpl implements IAttenceService {
    @Autowired
    private AttenceMapper attenceMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return attenceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Attence record) {
        return attenceMapper.insert(record);
    }

    @Override
    public Attence selectByPrimaryKey(Long id) {
        return attenceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Attence> selectAll() {
        return attenceMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Attence record) {
        return attenceMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = attenceMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Attence> rows = attenceMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    public void signIn() {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Attence singIn= attenceMapper.isSignIn(employee.getId(),Attence.IN_STATE_IN,dateFormat.format(date));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();

        /*Attence attence = attenceMapper.selectByEmployeeIdAndToday(employee.getId(),today);
        if(attence.getIntState() == Attence.IN_STATE_OUT){
            attence.setIntState(Attence.IN_STATE_IN);
            attence.setIntime(today);
            attenceMapper.updateByPrimaryKey(attence);
        }*/

        Attence attence = attenceMapper.selectEmployeeNameAndToday(employee.getId(), dateFormat.format(today));
        if (attence.getIntState() == Attence.IN_STATE_OUT) {

            attence.setSn(employee.getJobNumber());
            attence.setIntState(Attence.IN_STATE_IN);
            attence.setIntime(today);
            attence.setEmployee(employee);
            attenceMapper.updateByPrimaryKey(attence);
        } else {
            throw new SignInException("今天已经签到了,请勿重复签到");
        }


    }

    public void signOut() {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        Attence attence = attenceMapper.selectEmployeeNameAndToday(employee.getId(), dateFormat.format(today));
        if(attence != null) {
            if (attence.getOutState() == Attence.OUT_STATE_OUT) {
                attence.setOutState(Attence.OUT_STATE_IN);
                attence.setOuttime(today);
                attence.setEmployee(employee);
                attenceMapper.updateByPrimaryKey(attence);
            } else {
                throw new SignInException("你已签到");
            }
        }
    }
}
