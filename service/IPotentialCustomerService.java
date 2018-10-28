package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.CustomerTransferRecord;
import trlygjj.tanruiliyigenjinjin.domain.PotentialCustomer;
import trlygjj.tanruiliyigenjinjin.domain.ProfessionalStuent;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IPotentialCustomerService {
    int deleteByPrimaryKey(Long id);

    int insert(PotentialCustomer record);

    PotentialCustomer selectByPrimaryKey(Long id);

    List<PotentialCustomer> selectAll();

    int updateByPrimaryKey(PotentialCustomer record);

    PageResult query(QueryObject qo);

    void changeInpoolState(Long id);

    void tsetRegistration(PotentialCustomer potentialCustomer);

    void assign(CustomerTransferRecord customerTransferRecord);

    void receive(CustomerTransferRecord customerTransferRecord);

    void positive(ProfessionalStuent professionalStuent);

    void exportJxl() throws Exception;

    PotentialCustomer validateTell(String tell);

    PotentialCustomer validateQq(String qq);
}
