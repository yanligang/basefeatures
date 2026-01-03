package com.filtering.logic;

import java.util.ArrayList;
import java.util.List;

public class ReportFilterService {

    /**
     * Filters the list of ApsPmcPlanReport based on the provided criteria.
     *
     * @param reports The list of reports to filter.
     * @param criteria The filtering criteria.
     * @return A list of reports that match the criteria.
     */
    public List<ApsPmcPlanReport> filterReports(List<ApsPmcPlanReport> reports, FilterCriteria criteria) {
        if (reports == null || reports.isEmpty()) {
            return new ArrayList<>();
        }
        if (criteria == null) {
            return reports;
        }

        List<ApsPmcPlanReport> filteredReports = new ArrayList<>();

        for (ApsPmcPlanReport report : reports) {
            if (matches(report, criteria)) {
                filteredReports.add(report);
            }
        }

        return filteredReports;
    }

    private boolean matches(ApsPmcPlanReport report, FilterCriteria criteria) {
        // Customer Code (Exact Match)
        if (criteria.getCustomerCode() != null && !criteria.getCustomerCode().isEmpty()) {
            if (report.getCustomerCode() == null || !report.getCustomerCode().equals(criteria.getCustomerCode())) {
                return false;
            }
        }

        // Battery Pack Model (Contains/Partial Match)
        if (criteria.getPackModel() != null && !criteria.getPackModel().isEmpty()) {
             if (report.getPackModel() == null || !report.getPackModel().toLowerCase().contains(criteria.getPackModel().toLowerCase())) {
                 return false;
             }
        }

        // Plant Code (Exact Match)
        if (criteria.getPlantCode() != null && !criteria.getPlantCode().isEmpty()) {
            if (report.getPackPlant() == null || !report.getPackPlant().equals(criteria.getPlantCode())) {
                return false;
            }
        }

        // Resource/Line Code (Exact Match)
        if (criteria.getResourceCode() != null && !criteria.getResourceCode().isEmpty()) {
            if (report.getPackResource() == null || !report.getPackResource().equals(criteria.getResourceCode())) {
                return false;
            }
        }

        // Material Code (Exact Match)
        if (criteria.getMaterialCode() != null && !criteria.getMaterialCode().isEmpty()) {
            // Assuming PackProduct corresponds to Material Code
            if (report.getPackProduct() == null || !report.getPackProduct().equals(criteria.getMaterialCode())) {
                return false;
            }
        }

        // Supply Time (Range)
        // Assuming PackTime corresponds to Supply Time
        if (criteria.getSupplyTimeStart() != null) {
            if (report.getPackTime() == null || report.getPackTime().before(criteria.getSupplyTimeStart())) {
                return false;
            }
        }
        if (criteria.getSupplyTimeEnd() != null) {
            if (report.getPackTime() == null || report.getPackTime().after(criteria.getSupplyTimeEnd())) {
                return false;
            }
        }

        return true;
    }
}
