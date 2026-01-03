package com.filtering.logic;

import java.util.Date;

public class ApsPmcPlanReport {
    private String scenarioId;
    private String demandCode;
    private String customerCode;
    private String customerLocation;
    private Date customerTime;
    private Double customerQuantity;
    private String packPlant;
    private String packResource; // Production Line?
    private String packProduct; // Material Code?
    private String packModel; // Battery Pack Model
    private Date packTime; // Supply Time?
    private Boolean isFulfill;
    private Double packQuantity;

    // Getters and Setters
    public String getScenarioId() { return scenarioId; }
    public void setScenarioId(String scenarioId) { this.scenarioId = scenarioId; }

    public String getDemandCode() { return demandCode; }
    public void setDemandCode(String demandCode) { this.demandCode = demandCode; }

    public String getCustomerCode() { return customerCode; }
    public void setCustomerCode(String customerCode) { this.customerCode = customerCode; }

    public String getCustomerLocation() { return customerLocation; }
    public void setCustomerLocation(String customerLocation) { this.customerLocation = customerLocation; }

    public Date getCustomerTime() { return customerTime; }
    public void setCustomerTime(Date customerTime) { this.customerTime = customerTime; }

    public Double getCustomerQuantity() { return customerQuantity; }
    public void setCustomerQuantity(Double customerQuantity) { this.customerQuantity = customerQuantity; }

    public String getPackPlant() { return packPlant; }
    public void setPackPlant(String packPlant) { this.packPlant = packPlant; }

    public String getPackResource() { return packResource; }
    public void setPackResource(String packResource) { this.packResource = packResource; }

    public String getPackProduct() { return packProduct; }
    public void setPackProduct(String packProduct) { this.packProduct = packProduct; }

    public String getPackModel() { return packModel; }
    public void setPackModel(String packModel) { this.packModel = packModel; }

    public Date getPackTime() { return packTime; }
    public void setPackTime(Date packTime) { this.packTime = packTime; }

    public Boolean getIsFulfill() { return isFulfill; }
    public void setIsFulfill(Boolean isFulfill) { this.isFulfill = isFulfill; }

    public Double getPackQuantity() { return packQuantity; }
    public void setPackQuantity(Double packQuantity) { this.packQuantity = packQuantity; }
}
