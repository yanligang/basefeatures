package cn.vlooks.www.app.bean;

import java.io.Serializable;

public class ResourceOccupy implements Serializable {
    private String plantCode;
    private String resourceCode;
    private String periodStartTime;
    private Double totalAbleQuantity;

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

    public String getPeriodStartTime() {
        return periodStartTime;
    }

    public void setPeriodStartTime(String periodStartTime) {
        this.periodStartTime = periodStartTime;
    }

    public Double getTotalAbleQuantity() {
        return totalAbleQuantity;
    }

    public void setTotalAbleQuantity(Double totalAbleQuantity) {
        this.totalAbleQuantity = totalAbleQuantity;
    }
}
