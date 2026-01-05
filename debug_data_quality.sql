SELECT
    -- 检查 Pack 记录 (Type 1) 的字段填充情况
    COUNT(*) as total_pack_records,
    SUM(CASE WHEN parent_id IS NULL THEN 1 ELSE 0 END) as pack_no_parent_id,
    SUM(CASE WHEN customer_code IS NULL OR TRIM(customer_code) = '' THEN 1 ELSE 0 END) as pack_no_customer_code,
    SUM(CASE WHEN product_code IS NULL OR TRIM(product_code) = '' THEN 1 ELSE 0 END) as pack_no_product_code,
    
    -- 检查 Demand 记录 (Type 0) 的重复情况 (Product + Customer)
    (SELECT COUNT(*) FROM (
        SELECT product_code, customer_code 
        FROM aps_production_demand_pmc_new 
        WHERE type = 0 AND scenario_id = 3757
        GROUP BY product_code, customer_code 
        HAVING COUNT(*) > 1
    ) as duplicates) as demand_duplicate_groups
    
FROM aps_production_demand_pmc_new
WHERE type = 1 AND scenario_id = 3757;
