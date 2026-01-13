package cn.vlooks.www.app.tools;

import cn.vlooks.www.app.bean.ResourceTotalDetail;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ResourceTotalDetail 工具类
 * 提供按工厂、资源、区间开始时间分组获取 totalAbleQuantity 的功能
 * 使用 Stream 流处理
 *
 * @author 闫
 * @version 1.0
 */
public class ResourceTotalDetailUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 格式化日期为 yyyy-MM-dd 格式
     *
     * @param date 日期
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        synchronized (DATE_FORMAT) {
            return DATE_FORMAT.format(date);
        }
    }

    /**
     * 分组Key类，用于按工厂、资源、区间开始时间分组
     */
    public static class GroupKey {
        private final String plantCode;
        private final String resourceCode;
        private final String periodStartTimeStr; // yyyy-MM-dd 格式

        public GroupKey(String plantCode, String resourceCode, Date periodStartTime) {
            this.plantCode = plantCode;
            this.resourceCode = resourceCode;
            this.periodStartTimeStr = formatDate(periodStartTime);
        }

        public GroupKey(String plantCode, String resourceCode, String periodStartTimeStr) {
            this.plantCode = plantCode;
            this.resourceCode = resourceCode;
            this.periodStartTimeStr = periodStartTimeStr != null ? periodStartTimeStr : "";
        }

        public String getPlantCode() {
            return plantCode;
        }

        public String getResourceCode() {
            return resourceCode;
        }

        public String getPeriodStartTimeStr() {
            return periodStartTimeStr;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GroupKey groupKey = (GroupKey) o;

            if (plantCode != null ? !plantCode.equals(groupKey.plantCode) : groupKey.plantCode != null) return false;
            if (resourceCode != null ? !resourceCode.equals(groupKey.resourceCode) : groupKey.resourceCode != null)
                return false;
            return periodStartTimeStr != null ? periodStartTimeStr.equals(groupKey.periodStartTimeStr) : groupKey.periodStartTimeStr == null;
        }

        @Override
        public int hashCode() {
            int result = plantCode != null ? plantCode.hashCode() : 0;
            result = 31 * result + (resourceCode != null ? resourceCode.hashCode() : 0);
            result = 31 * result + (periodStartTimeStr != null ? periodStartTimeStr.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return plantCode + "_" + resourceCode + "_" + periodStartTimeStr;
        }
    }

    /**
     * 按工厂代码、资源代码、区间开始时间分组，获取每组的 totalAbleQuantity 总和
     * 使用 Stream 流处理
     *
     * @param resourceTotalDetailList 资源总量详情列表
     * @return 分组后的 totalAbleQuantity Map，key为分组标识，value为该分组的 totalAbleQuantity 总和
     */
    public static Map<GroupKey, BigDecimal> groupByPlantResourcePeriodAndSumTotalAbleQuantity(
            List<ResourceTotalDetail> resourceTotalDetailList) {

        if (resourceTotalDetailList == null || resourceTotalDetailList.isEmpty()) {
            return Collections.emptyMap();
        }

        return resourceTotalDetailList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        detail -> new GroupKey(
                                detail.getPlantCode(),
                                detail.getResourceCode(),
                                detail.getPeriodStartTime()
                        ),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                detail -> detail.getTotalAbleQuantity() != null ? detail.getTotalAbleQuantity() : BigDecimal.ZERO,
                                BigDecimal::add
                        )
                ));
    }

    /**
     * 按工厂代码、资源代码、区间开始时间分组，获取每组的 totalAbleQuantity 总和
     * 返回 String 类型的 Key (格式: plantCode_resourceCode_periodStartTime(yyyy-MM-dd))
     * 使用 Stream 流处理
     *
     * @param resourceTotalDetailList 资源总量详情列表
     * @return 分组后的 totalAbleQuantity Map，key为"plantCode_resourceCode_periodStartTime"格式，value为该分组的 totalAbleQuantity 总和
     */
    public static Map<String, BigDecimal> groupByPlantResourcePeriodAndSumTotalAbleQuantityWithStringKey(
            List<ResourceTotalDetail> resourceTotalDetailList) {

        if (resourceTotalDetailList == null || resourceTotalDetailList.isEmpty()) {
            return Collections.emptyMap();
        }

        return resourceTotalDetailList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        ResourceTotalDetail::getGroupKey,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                detail -> detail.getTotalAbleQuantity() != null ? detail.getTotalAbleQuantity() : BigDecimal.ZERO,
                                BigDecimal::add
                        )
                ));
    }

    /**
     * 按工厂代码、资源代码、区间开始时间分组
     * 使用 Stream 流处理
     *
     * @param resourceTotalDetailList 资源总量详情列表
     * @return 分组后的 Map，key为分组标识，value为该分组的资源详情列表
     */
    public static Map<GroupKey, List<ResourceTotalDetail>> groupByPlantResourcePeriod(
            List<ResourceTotalDetail> resourceTotalDetailList) {

        if (resourceTotalDetailList == null || resourceTotalDetailList.isEmpty()) {
            return Collections.emptyMap();
        }

        return resourceTotalDetailList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        detail -> new GroupKey(
                                detail.getPlantCode(),
                                detail.getResourceCode(),
                                detail.getPeriodStartTime()
                        )
                ));
    }

    /**
     * 按工厂代码、资源代码、区间开始时间分组
     * 返回 String 类型的 Key
     * 使用 Stream 流处理
     *
     * @param resourceTotalDetailList 资源总量详情列表
     * @return 分组后的 Map，key为"plantCode_resourceCode_periodStartTime"格式，value为该分组的资源详情列表
     */
    public static Map<String, List<ResourceTotalDetail>> groupByPlantResourcePeriodWithStringKey(
            List<ResourceTotalDetail> resourceTotalDetailList) {

        if (resourceTotalDetailList == null || resourceTotalDetailList.isEmpty()) {
            return Collections.emptyMap();
        }

        return resourceTotalDetailList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(ResourceTotalDetail::getGroupKey));
    }

    /**
     * 根据工厂代码、资源代码、区间开始时间获取对应的 totalAbleQuantity
     * 使用 Stream 流处理
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

        String periodStartTimeStr = formatDate(periodStartTime);

        Optional<BigDecimal> result = resourceTotalDetailList.stream()
                .filter(Objects::nonNull)
                .filter(detail -> Objects.equals(plantCode, detail.getPlantCode()))
                .filter(detail -> Objects.equals(resourceCode, detail.getResourceCode()))
                .filter(detail -> periodStartTimeStr.equals(detail.getPeriodStartTimeFormatted()))
                .map(detail -> detail.getTotalAbleQuantity() != null ? detail.getTotalAbleQuantity() : BigDecimal.ZERO)
                .reduce(BigDecimal::add);

        return result.orElse(null);
    }

    /**
     * 根据工厂代码、资源代码、区间开始时间(yyyy-MM-dd格式字符串)获取对应的 totalAbleQuantity
     * 使用 Stream 流处理
     *
     * @param resourceTotalDetailList 资源总量详情列表
     * @param plantCode               工厂代码
     * @param resourceCode            资源代码
     * @param periodStartTimeStr      区间开始时间 (yyyy-MM-dd格式)
     * @return 对应的 totalAbleQuantity 总和，如果未找到返回 null
     */
    public static BigDecimal getTotalAbleQuantity(
            List<ResourceTotalDetail> resourceTotalDetailList,
            String plantCode,
            String resourceCode,
            String periodStartTimeStr) {

        if (resourceTotalDetailList == null || resourceTotalDetailList.isEmpty()) {
            return null;
        }

        String finalPeriodStr = periodStartTimeStr != null ? periodStartTimeStr : "";

        Optional<BigDecimal> result = resourceTotalDetailList.stream()
                .filter(Objects::nonNull)
                .filter(detail -> Objects.equals(plantCode, detail.getPlantCode()))
                .filter(detail -> Objects.equals(resourceCode, detail.getResourceCode()))
                .filter(detail -> finalPeriodStr.equals(detail.getPeriodStartTimeFormatted()))
                .map(detail -> detail.getTotalAbleQuantity() != null ? detail.getTotalAbleQuantity() : BigDecimal.ZERO)
                .reduce(BigDecimal::add);

        return result.orElse(null);
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

    /**
     * 根据分组 Map 获取对应的 totalAbleQuantity
     *
     * @param groupedMap         已分组的 Map
     * @param plantCode          工厂代码
     * @param resourceCode       资源代码
     * @param periodStartTimeStr 区间开始时间 (yyyy-MM-dd格式)
     * @return 对应的 totalAbleQuantity，如果未找到返回 null
     */
    public static BigDecimal getTotalAbleQuantityFromGroupedMap(
            Map<GroupKey, BigDecimal> groupedMap,
            String plantCode,
            String resourceCode,
            String periodStartTimeStr) {

        if (groupedMap == null || groupedMap.isEmpty()) {
            return null;
        }

        GroupKey key = new GroupKey(plantCode, resourceCode, periodStartTimeStr);
        return groupedMap.get(key);
    }

    /**
     * 根据 String Key 格式的分组 Map 获取对应的 totalAbleQuantity
     *
     * @param groupedMap         已分组的 Map (String Key 格式)
     * @param plantCode          工厂代码
     * @param resourceCode       资源代码
     * @param periodStartTime    区间开始时间
     * @return 对应的 totalAbleQuantity，如果未找到返回 null
     */
    public static BigDecimal getTotalAbleQuantityFromStringKeyMap(
            Map<String, BigDecimal> groupedMap,
            String plantCode,
            String resourceCode,
            Date periodStartTime) {

        if (groupedMap == null || groupedMap.isEmpty()) {
            return null;
        }

        String key = plantCode + "_" + resourceCode + "_" + formatDate(periodStartTime);
        return groupedMap.get(key);
    }

    /**
     * 根据 String Key 格式的分组 Map 获取对应的 totalAbleQuantity
     *
     * @param groupedMap         已分组的 Map (String Key 格式)
     * @param plantCode          工厂代码
     * @param resourceCode       资源代码
     * @param periodStartTimeStr 区间开始时间 (yyyy-MM-dd格式)
     * @return 对应的 totalAbleQuantity，如果未找到返回 null
     */
    public static BigDecimal getTotalAbleQuantityFromStringKeyMap(
            Map<String, BigDecimal> groupedMap,
            String plantCode,
            String resourceCode,
            String periodStartTimeStr) {

        if (groupedMap == null || groupedMap.isEmpty()) {
            return null;
        }

        String key = plantCode + "_" + resourceCode + "_" + (periodStartTimeStr != null ? periodStartTimeStr : "");
        return groupedMap.get(key);
    }

    /**
     * 按工厂代码、资源代码、区间开始时间分组，获取每组的 totalAbleQuantity 总和
     * 返回三层嵌套 Map: plantCode -> resourceCode -> periodStartTime(yyyy-MM-dd) -> totalAbleQuantity
     * 使用 Stream 流处理
     *
     * @param resourceTotalDetailList 资源总量详情列表
     * @return 分组后的嵌套 Map
     */
    public static Map<String, Map<String, Map<String, BigDecimal>>> groupByPlantResourcePeriodNested(
            List<ResourceTotalDetail> resourceTotalDetailList) {

        if (resourceTotalDetailList == null || resourceTotalDetailList.isEmpty()) {
            return Collections.emptyMap();
        }

        return resourceTotalDetailList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        detail -> detail.getPlantCode() != null ? detail.getPlantCode() : "",
                        Collectors.groupingBy(
                                detail -> detail.getResourceCode() != null ? detail.getResourceCode() : "",
                                Collectors.groupingBy(
                                        ResourceTotalDetail::getPeriodStartTimeFormatted,
                                        Collectors.reducing(
                                                BigDecimal.ZERO,
                                                detail -> detail.getTotalAbleQuantity() != null ? detail.getTotalAbleQuantity() : BigDecimal.ZERO,
                                                BigDecimal::add
                                        )
                                )
                        )
                ));
    }

    /**
     * 从嵌套 Map 中获取 totalAbleQuantity
     *
     * @param nestedMap          嵌套 Map
     * @param plantCode          工厂代码
     * @param resourceCode       资源代码
     * @param periodStartTimeStr 区间开始时间 (yyyy-MM-dd格式)
     * @return 对应的 totalAbleQuantity，如果未找到返回 null
     */
    public static BigDecimal getTotalAbleQuantityFromNestedMap(
            Map<String, Map<String, Map<String, BigDecimal>>> nestedMap,
            String plantCode,
            String resourceCode,
            String periodStartTimeStr) {

        if (nestedMap == null || nestedMap.isEmpty()) {
            return null;
        }

        String plant = plantCode != null ? plantCode : "";
        String resource = resourceCode != null ? resourceCode : "";
        String period = periodStartTimeStr != null ? periodStartTimeStr : "";

        return Optional.ofNullable(nestedMap.get(plant))
                .map(m -> m.get(resource))
                .map(m -> m.get(period))
                .orElse(null);
    }
}
