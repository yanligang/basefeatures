package cn.vlooks.www.app.tools;

import cn.vlooks.www.app.bean.ResourceTotalDetail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ResourceTotalDetail 工具类
 * 提供按工厂、资源、区间开始时间分组获取 totalAbleQuantity 的功能
 *
 * @author 闫
 * @version 1.0
 */
public class ResourceTotalDetailUtils {

    /**
     * 分组Key类，用于按工厂、资源、区间开始时间分组
     */
    public static class GroupKey {
        private final String plantCode;
        private final String resourceCode;
        private final Date periodStartTime;

        public GroupKey(String plantCode, String resourceCode, Date periodStartTime) {
            this.plantCode = plantCode;
            this.resourceCode = resourceCode;
            this.periodStartTime = periodStartTime;
        }

        public String getPlantCode() {
            return plantCode;
        }

        public String getResourceCode() {
            return resourceCode;
        }

        public Date getPeriodStartTime() {
            return periodStartTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GroupKey groupKey = (GroupKey) o;

            if (plantCode != null ? !plantCode.equals(groupKey.plantCode) : groupKey.plantCode != null) return false;
            if (resourceCode != null ? !resourceCode.equals(groupKey.resourceCode) : groupKey.resourceCode != null)
                return false;
            return periodStartTime != null ? periodStartTime.equals(groupKey.periodStartTime) : groupKey.periodStartTime == null;
        }

        @Override
        public int hashCode() {
            int result = plantCode != null ? plantCode.hashCode() : 0;
            result = 31 * result + (resourceCode != null ? resourceCode.hashCode() : 0);
            result = 31 * result + (periodStartTime != null ? periodStartTime.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return plantCode + "_" + resourceCode + "_" + (periodStartTime != null ? periodStartTime.getTime() : "null");
        }
    }

    /**
     * 按工厂代码、资源代码、区间开始时间分组，获取每组的 totalAbleQuantity 总和
     *
     * @param resourceTotalDetailList 资源总量详情列表
     * @return 分组后的 totalAbleQuantity Map，key为分组标识，value为该分组的 totalAbleQuantity 总和
     */
    public static Map<GroupKey, BigDecimal> groupByPlantResourcePeriodAndSumTotalAbleQuantity(
            List<ResourceTotalDetail> resourceTotalDetailList) {

        Map<GroupKey, BigDecimal> resultMap = new HashMap<>();

        if (resourceTotalDetailList == null || resourceTotalDetailList.isEmpty()) {
            return resultMap;
        }

        for (ResourceTotalDetail detail : resourceTotalDetailList) {
            if (detail == null) {
                continue;
            }

            GroupKey key = new GroupKey(
                    detail.getPlantCode(),
                    detail.getResourceCode(),
                    detail.getPeriodStartTime()
            );

            BigDecimal currentTotal = resultMap.get(key);
            BigDecimal detailQuantity = detail.getTotalAbleQuantity();

            if (detailQuantity == null) {
                detailQuantity = BigDecimal.ZERO;
            }

            if (currentTotal == null) {
                resultMap.put(key, detailQuantity);
            } else {
                resultMap.put(key, currentTotal.add(detailQuantity));
            }
        }

        return resultMap;
    }

    /**
     * 按工厂代码、资源代码、区间开始时间分组，获取每组的 totalAbleQuantity 总和
     * 返回 String 类型的 Key
     *
     * @param resourceTotalDetailList 资源总量详情列表
     * @return 分组后的 totalAbleQuantity Map，key为"plantCode_resourceCode_periodStartTime"格式，value为该分组的 totalAbleQuantity 总和
     */
    public static Map<String, BigDecimal> groupByPlantResourcePeriodAndSumTotalAbleQuantityWithStringKey(
            List<ResourceTotalDetail> resourceTotalDetailList) {

        Map<String, BigDecimal> resultMap = new HashMap<>();

        if (resourceTotalDetailList == null || resourceTotalDetailList.isEmpty()) {
            return resultMap;
        }

        for (ResourceTotalDetail detail : resourceTotalDetailList) {
            if (detail == null) {
                continue;
            }

            String key = detail.getGroupKey();

            BigDecimal currentTotal = resultMap.get(key);
            BigDecimal detailQuantity = detail.getTotalAbleQuantity();

            if (detailQuantity == null) {
                detailQuantity = BigDecimal.ZERO;
            }

            if (currentTotal == null) {
                resultMap.put(key, detailQuantity);
            } else {
                resultMap.put(key, currentTotal.add(detailQuantity));
            }
        }

        return resultMap;
    }

    /**
     * 按工厂代码、资源代码、区间开始时间分组
     *
     * @param resourceTotalDetailList 资源总量详情列表
     * @return 分组后的 Map，key为分组标识，value为该分组的资源详情列表
     */
    public static Map<GroupKey, List<ResourceTotalDetail>> groupByPlantResourcePeriod(
            List<ResourceTotalDetail> resourceTotalDetailList) {

        Map<GroupKey, List<ResourceTotalDetail>> resultMap = new HashMap<>();

        if (resourceTotalDetailList == null || resourceTotalDetailList.isEmpty()) {
            return resultMap;
        }

        for (ResourceTotalDetail detail : resourceTotalDetailList) {
            if (detail == null) {
                continue;
            }

            GroupKey key = new GroupKey(
                    detail.getPlantCode(),
                    detail.getResourceCode(),
                    detail.getPeriodStartTime()
            );

            List<ResourceTotalDetail> detailList = resultMap.get(key);
            if (detailList == null) {
                detailList = new ArrayList<>();
                resultMap.put(key, detailList);
            }
            detailList.add(detail);
        }

        return resultMap;
    }

    /**
     * 根据工厂代码、资源代码、区间开始时间获取对应的 totalAbleQuantity
     *
     * @param resourceTotalDetailList 资源总量详情列表
     * @param plantCode               工厂代码
     * @param resourceCode            资源代码
     * @param periodStartTime         区间开始时间
     * @return 对应的 totalAbleQuantity 总和，如果未找到返回 null
     */
    public static BigDecimal getTotalAbleQuantity(
            List<ResourceTotalDetail> resourceTotalDetailList,
            String plantCode,
            String resourceCode,
            Date periodStartTime) {

        if (resourceTotalDetailList == null || resourceTotalDetailList.isEmpty()) {
            return null;
        }

        BigDecimal total = BigDecimal.ZERO;
        boolean found = false;

        for (ResourceTotalDetail detail : resourceTotalDetailList) {
            if (detail == null) {
                continue;
            }

            boolean plantMatch = (plantCode == null && detail.getPlantCode() == null) ||
                    (plantCode != null && plantCode.equals(detail.getPlantCode()));

            boolean resourceMatch = (resourceCode == null && detail.getResourceCode() == null) ||
                    (resourceCode != null && resourceCode.equals(detail.getResourceCode()));

            boolean periodMatch = (periodStartTime == null && detail.getPeriodStartTime() == null) ||
                    (periodStartTime != null && periodStartTime.equals(detail.getPeriodStartTime()));

            if (plantMatch && resourceMatch && periodMatch) {
                found = true;
                BigDecimal quantity = detail.getTotalAbleQuantity();
                if (quantity != null) {
                    total = total.add(quantity);
                }
            }
        }

        return found ? total : null;
    }

    /**
     * 根据分组 Map 获取对应的 totalAbleQuantity
     *
     * @param groupedMap      已分组的 Map
     * @param plantCode       工厂代码
     * @param resourceCode    资源代码
     * @param periodStartTime 区间开始时间
     * @return 对应的 totalAbleQuantity，如果未找到返回 null
     */
    public static BigDecimal getTotalAbleQuantityFromGroupedMap(
            Map<GroupKey, BigDecimal> groupedMap,
            String plantCode,
            String resourceCode,
            Date periodStartTime) {

        if (groupedMap == null || groupedMap.isEmpty()) {
            return null;
        }

        GroupKey key = new GroupKey(plantCode, resourceCode, periodStartTime);
        return groupedMap.get(key);
    }
}
