package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.domain.ProfessionalStuent;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;

import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
public interface IProfessionalStuentService {
    /**
     * 删除正式学员
     * @param id
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 保存正式学员
     * @param record
     */
    void insert(ProfessionalStuent record);

    /**
     * 查询正式学员
     * @param id
     * @return
     */
    ProfessionalStuent selectByPrimaryKey(Long id);

    /**
     * 查询全部正式学员
     * @return
     */
    List<ProfessionalStuent> selectAll();

    /**
     * 更新正式学员
     * @param record
     */
    void updateByPrimaryKey(ProfessionalStuent record);
    /**
     * 更新正式学员状态值
     * @param record
     */
   // void updateProfessionalStuentState(ProfessionalStuent record);

    /**
     * 正式学员分页查询
     * @param qo
     * @return
     */
    PageResult query(QueryObject qo);

    /**
     * 更新学员状态值
     * @param professionalStuent
     */
    void updateProfessionalStuentState(ProfessionalStuent professionalStuent);
}
