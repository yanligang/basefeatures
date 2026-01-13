package cn.vlooks.www.app.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 资源总量详情
 *
 * @author 闫
 * @version 1.0
 */
public class ResourceTotalDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 场景ID
     */
    private Long scenarioId;

    /**
     * 操作代码
     */
    private String operationCode;

    /**
     * 工厂代码
     */
    private String plantCode;

    /**
     * 资源代码
     */
    private String resourceCode;

    /**
     * 资源日产量
     */
    private Integer dailyCapacity;

    /**
     * 区间代码
     */
    private String periodCode;

    /**
     * 区间开始时间
     */
    private Date periodStartTime;

    /**
     * 区间结束时间
     */
    private Date periodEndTime;

    /**
     * 总可用数量
     */
    private BigDecimal totalAbleQuantity;

    /**
     * 已占用数量
     */
    private BigDecimal occupiedQuantity;

    public ResourceTotalDetail() {
    }

    public Long getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(Long scenarioId) {
        this.scenarioId = scenarioId;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public Integer getDailyCapacity() {
        return dailyCapacity;
    }

    public void setDailyCapacity(Integer dailyCapacity) {
        this.dailyCapacity = dailyCapacity;
    }

    public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(String periodCode) {
        this.periodCode = periodCode;
    }

    public Date getPeriodStartTime() {
        return periodStartTime;
    }

    public void setPeriodStartTime(Date periodStartTime) {
        this.periodStartTime = periodStartTime;
    }

    public Date getPeriodEndTime() {
        return periodEndTime;
    }

    public void setPeriodEndTime(Date periodEndTime) {
        this.periodEndTime = periodEndTime;
    }

    public BigDecimal getTotalAbleQuantity() {
        return totalAbleQuantity;
    }

    public void setTotalAbleQuantity(BigDecimal totalAbleQuantity) {
        this.totalAbleQuantity = totalAbleQuantity;
    }

    public BigDecimal getOccupiedQuantity() {
        return occupiedQuantity;
    }

    public void setOccupiedQuantity(BigDecimal occupiedQuantity) {
        this.occupiedQuantity = occupiedQuantity;
    }

    /**
     * 生成分组Key：工厂代码_资源代码_区间开始时间
     *
     * @return 分组Key
     */
    public String getGroupKey() {
        return plantCode + "_" + resourceCode + "_" + (periodStartTime != null ? periodStartTime.getTime() : "");
    }

    @Override
    public String toString() {
        return "ResourceTotalDetail{" +
                "scenarioId=" + scenarioId +
                ", operationCode='" + operationCode + '\'' +
                ", plantCode='" + plantCode + '\'' +
                ", resourceCode='" + resourceCode + '\'' +
                ", dailyCapacity=" + dailyCapacity +
                ", periodCode='" + periodCode + '\'' +
                ", periodStartTime=" + periodStartTime +
                ", periodEndTime=" + periodEndTime +
                ", totalAbleQuantity=" + totalAbleQuantity +
                ", occupiedQuantity=" + occupiedQuantity +
                '}';
    }
}
