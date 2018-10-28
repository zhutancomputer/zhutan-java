package trlygjj.tanruiliyigenjinjin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trlygjj.tanruiliyigenjinjin.mapper.ChartMapper;
import trlygjj.tanruiliyigenjinjin.query.ChartQueryObject;
import trlygjj.tanruiliyigenjinjin.service.IChartService;

import java.util.List;
import java.util.Map;

@Service
public class ChartServiceImpl implements IChartService {
    @Autowired
    private ChartMapper chartMapper;

    public List<Map<String, Object>> selectProfessionalStuentChart(ChartQueryObject qo) {
        return chartMapper.selectProfessionalStuentChart(qo);
    }
}
