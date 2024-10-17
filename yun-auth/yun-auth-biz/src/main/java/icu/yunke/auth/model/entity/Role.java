package icu.yunke.auth.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import icu.yunke.framework.mybatis.entity.BaseEntity;
import lombok.Data;

/**
 * 角色表
 */
@Data
@TableName("tb_sys_role")
public class Role extends BaseEntity {
    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 角色唯一编码
     */
    @TableField("code")
    private String code;

    /**
     * 是否启用
     */
    @TableField("is_enable")
    private Boolean enable ;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField("is_deleted")
    private Boolean deleted;
}
