package cn.vlooks.www.app.tools;

import cn.vlooks.www.app.bean.ResourceOccupy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceGroupUtils {

    /**
     * 根据工厂代码 资源代码 区间开始时间（yyyy-MM-dd）分组
     * @param resourceTotalDetail 资源占用列表
     * @return Map<String, Double> Key: plantCode + resourceCode + periodStartTime, Value: totalAbleQuantity
     */
    public static Map<String, Double> groupResourceTotalDetail(List<ResourceOccupy> resourceTotalDetail) {
        Map<String, Double> resultMap = new HashMap<>();
        
        if (resourceTotalDetail == null || resourceTotalDetail.isEmpty()) {
            return resultMap;
        }

        for (ResourceOccupy detail : resourceTotalDetail) {
            String plantCode = detail.getPlantCode();
            String resourceCode = detail.getResourceCode();
            String periodStartTime = detail.getPeriodStartTime();
            
            // Format periodStartTime to yyyy-MM-dd if needed
            if (periodStartTime != null && periodStartTime.length() > 10) {
                periodStartTime = periodStartTime.substring(0, 10);
            }
            
            String key = generateKey(plantCode, resourceCode, periodStartTime);
            
            Double quantity = detail.getTotalAbleQuantity();
            if (quantity == null) {
                quantity = 0.0;
            }
            
            if (resultMap.containsKey(key)) {
                resultMap.put(key, resultMap.get(key) + quantity);
            } else {
                resultMap.put(key, quantity);
            }
        }
        
        return resultMap;
    }

    /**
     * Generate key for the map
     */
    public static String generateKey(String plantCode, String resourceCode, String periodStartTime) {
        // Using "_" as separator. 
        // Note: The user didn't specify the separator, but using one makes it safer.
        // If the user wants to concatenate directly, they can modify this.
        return (plantCode == null ? "null" : plantCode) + "_" + 
               (resourceCode == null ? "null" : resourceCode) + "_" + 
               (periodStartTime == null ? "null" : periodStartTime);
    }
}
