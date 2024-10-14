package icu.yunke.framework.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 基础实体类，与IDEntity不同时使用
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BaseEntity extends IDEntity {

    @TableField("create_at")
    private LocalDateTime createAt;

    @TableField("create_by")
    private Long createBy;

    @TableField("update_at")
    private LocalDateTime updateAt;

    @TableField("update_by")
    private Long updateBy;
}
