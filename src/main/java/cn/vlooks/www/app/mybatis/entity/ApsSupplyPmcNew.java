package cn.vlooks.www.app.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 供应表
 *
 * @author generator
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("aps_supply_pmc_new")
public class ApsSupplyPmcNew implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 场景ID
     */
    @TableField("scenario_id")
    private Long scenarioId;

    /**
     * 供应编码
     */
    @TableField("supply_code")
    private String supplyCode;

    /**
     * 生产工厂
     */
    @TableField("production_plant")
    private String productionPlant;

    /**
     * 生产资源
     */
    @TableField("production_resource")
    private String productionResource;

    /**
     * 类别
     */
    @TableField("category")
    private String category;

    /**
     * 生产日期
     */
    @TableField("production_date")
    private LocalDateTime productionDate;

    /**
     * 生产数量
     */
    @TableField("production_quantity")
    private BigDecimal productionQuantity;

    /**
     * 产品编码
     */
    @TableField("product_code")
    private String productCode;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}
