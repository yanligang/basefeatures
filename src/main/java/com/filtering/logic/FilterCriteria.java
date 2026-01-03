package com.filtering.logic;

import java.util.Date;

public class FilterCriteria {
    private String customerCode;
    private String packModel;
    private String plantCode;
    private String resourceCode; // Production Line Code
    private String materialCode;
    private Date supplyTimeStart;
    private Date supplyTimeEnd;

    public String getCustomerCode() { return customerCode; }
    public void setCustomerCode(String customerCode) { this.customerCode = customerCode; }

    public String getPackModel() { return packModel; }
    public void setPackModel(String packModel) { this.packModel = packModel; }

    public String getPlantCode() { return plantCode; }
    public void setPlantCode(String plantCode) { this.plantCode = plantCode; }

    public String getResourceCode() { return resourceCode; }
    public void setResourceCode(String resourceCode) { this.resourceCode = resourceCode; }

    public String getMaterialCode() { return materialCode; }
    public void setMaterialCode(String materialCode) { this.materialCode = materialCode; }

    public Date getSupplyTimeStart() { return supplyTimeStart; }
    public void setSupplyTimeStart(Date supplyTimeStart) { this.supplyTimeStart = supplyTimeStart; }

    public Date getSupplyTimeEnd() { return supplyTimeEnd; }
    public void setSupplyTimeEnd(Date supplyTimeEnd) { this.supplyTimeEnd = supplyTimeEnd; }
}
