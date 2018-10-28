package trlygjj.tanruiliyigenjinjin.service;

import trlygjj.tanruiliyigenjinjin.query.ChartQueryObject;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/7/10.
 */
public interface IChartService {
    List<Map<String,Object>> selectProfessionalStuentChart(ChartQueryObject qo);
}
