package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.domain.ProfessionalStuent;
import trlygjj.tanruiliyigenjinjin.mapper.ProfessionalStuentMapper;
import trlygjj.tanruiliyigenjinjin.page.PageResult;
import trlygjj.tanruiliyigenjinjin.query.QueryObject;
import trlygjj.tanruiliyigenjinjin.service.IProfessionalStuentService;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2018/7/10.
 */
@Service
public class ProfessionalStuentServiceImpl implements IProfessionalStuentService {
    @Autowired
    private ProfessionalStuentMapper professionalStuentMapper;

    /**
     * 删除正式学员
     * @param id
     */
    @Override
    public void deleteByPrimaryKey(Long id) {
         professionalStuentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 保存正式学员
     * @param record
     */
    @Override
    public void insert(ProfessionalStuent record) {
        professionalStuentMapper.insert(record);
    }

    /**
     * 查询单个正式学员
     * @param id
     * @return
     */
    @Override
    public ProfessionalStuent selectByPrimaryKey(Long id) {
        return professionalStuentMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部正式学员
     * @return
     */
    @Override
    public List<ProfessionalStuent> selectAll() {
        return professionalStuentMapper.selectAll();
    }

    /**
     * 更新正式学员
     * @param record
     */
    @Override
    public void updateByPrimaryKey(ProfessionalStuent record) {
       professionalStuentMapper.updateByPrimaryKey(record);
    }

    /**
     * 分页查询
     * @param qo
     * @return
     */
    public PageResult query(QueryObject qo) {
        int total = professionalStuentMapper.queryForCount(qo);
        if(total == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<ProfessionalStuent> rows = professionalStuentMapper.queryForList(qo);
        for (ProfessionalStuent row : rows) {
        }
        return new PageResult(total,rows);
    }

    /**
     * 更新学员状态值
     *
     * @param professionalStuent
     */
    public void updateProfessionalStuentState(ProfessionalStuent professionalStuent) {

        professionalStuentMapper.updateProfessionalStuentState(professionalStuent);
    }
}