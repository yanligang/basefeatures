WITH
-- 1. Supply 汇总
Supply_Agg AS (
SELECT
    arpn.demand_id, arpn.scenario_id,
    -- 修正：根据用户指示，Production Quantity 需要乘以 Demand 表中的 scal_coefficient
    -- 这通常用于将 Pack 的生产数量转换为 Cell 的数量
    SUM(aspn.production_quantity * COALESCE(d.scal_coefficient, 1)) AS supply_total_qty
FROM aps_relation_pmc_new arpn
JOIN aps_supply_pmc_new aspn
    ON arpn.scenario_id = aspn.scenario_id AND arpn.supply_code = aspn.supply_code
    and aspn.production_resource not like '%Pseudo%'
-- 关联 Demand 表获取系数
LEFT JOIN aps_production_demand_pmc_new d 
    ON arpn.demand_id = d.id 
    AND arpn.scenario_id = d.scenario_id
GROUP BY arpn.demand_id, arpn.scenario_id
),

-- 2. Cell 汇总 (关联到 Pack ID)
Cell_Agg_For_Pack AS (
SELECT
    a2.parent_id, a2.scenario_id,
    MAX(a2.cell_code) AS cell_product_code,
    MAX(a2.cell_model) AS cell_model,
    SUM(a2.net_demand_quantity) AS cell_req_qty,
    SUM(COALESCE(s.supply_total_qty, 0)) AS supply_grand_total
FROM aps_production_demand_pmc_new a2
LEFT JOIN Supply_Agg s ON a2.id = s.demand_id AND a2.scenario_id = s.scenario_id
WHERE a2.type = 2 AND a2.product_category = 'Cell'
GROUP BY a2.parent_id, a2.scenario_id
),

-- 3. Pack 汇总 (按 parent_id 关联 Demand)
Pack_Agg_By_Demand_ID AS (
SELECT
    a1.parent_id AS demand_id, -- 直接指向 Demand 的 ID
    a1.scenario_id,
    SUM(a1.production_quantity) AS pack_prod_qty,
    SUM(COALESCE(c.cell_req_qty, 0)) AS cell_req_grand_total,
    SUM(COALESCE(c.supply_grand_total, 0)) AS supply_grand_total
FROM aps_production_demand_pmc_new a1
LEFT JOIN Cell_Agg_For_Pack c ON a1.id = c.parent_id AND a1.scenario_id = c.scenario_id
WHERE a1.type = 1 
AND a1.product_category = 'Pack'
and a1.resource_code not like '%Pseudo%'
GROUP BY a1.parent_id, a1.scenario_id
)

-- 4. 最终查询
SELECT
    d.scenario_id,
    d.customer_code,
    d.customer_name,
    d.customer_location,
    d.product_category AS delivery_type,
    d.product_code AS battery_pack_code,
    d.battery_pack_model AS battery_pack_model,
    d.cell_capacity,
    d.scal_coefficient AS battery_cell_quantity,
    d.electricity_quantity,

    -- 从 Pack 汇总里获取 Cell 信息
    (SELECT MAX(c.cell_product_code) 
     FROM aps_production_demand_pmc_new pack 
     LEFT JOIN Cell_Agg_For_Pack c ON pack.id = c.parent_id 
     WHERE pack.parent_id = d.id LIMIT 1) AS battery_cell_code,
    
    (SELECT MAX(c.cell_model) 
     FROM aps_production_demand_pmc_new pack 
     LEFT JOIN Cell_Agg_For_Pack c ON pack.id = c.parent_id 
     WHERE pack.parent_id = d.id LIMIT 1) AS battery_cell_model,

    SUM(d.demand_quantity) AS demand_quantity,
    
    SUM(COALESCE(p.pack_prod_qty, 0)) AS demand_fulfill_quantity,
    (SUM(d.demand_quantity) - SUM(COALESCE(p.pack_prod_qty, 0))) AS demand_insufficient_quantity,

    SUM(COALESCE(p.cell_req_grand_total, 0)) AS cell_demand_quantity,
    SUM(COALESCE(p.supply_grand_total, 0)) AS cell_demand_fulfill_quantity,
    ((SUM(COALESCE(p.cell_req_grand_total, 0))) - (SUM(COALESCE(p.supply_grand_total, 0)))) AS cell_demand_insufficient_quantity

FROM aps_production_demand_pmc_new d
LEFT JOIN Pack_Agg_By_Demand_ID p 
    ON d.id = p.demand_id 
    AND d.scenario_id = p.scenario_id

WHERE d.type = 0
AND d.scenario_id = 3757
AND d.product_category = 'Pack'

GROUP BY
    d.scenario_id,
    d.customer_code,
    d.customer_name,
    d.customer_location,
    d.product_category,
    d.product_code,
    d.battery_pack_model,
    d.cell_capacity,
    d.electricity_quantity,
    d.scal_coefficient,
    d.id
