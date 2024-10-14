package icu.yunke.auth.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import icu.yunke.framework.mybatis.entity.BaseEntity;
import lombok.Data;

/**
 * 菜单表
 */
@Data
@TableName("tb_sys_menu")
public class Menu extends BaseEntity {

    /**
     * 父菜单id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 路由地址
     */
    @TableField("path")
    private String path;

    /**
     * 前端组件
     */
    @TableField("component")
    private String component;

    /**
     * 排序
     */
    @TableField("sequence")
    private Integer sequence;

    /**
     * 类型：directory->目录,item->菜单项,button->按钮
     */
    @TableField("type")
    private String type;

    /**
     * 权限值
     */
    @TableField("auth_key")
    private String authKey;

    /**
     * 是否显示：1->是，0->否
     */
    @TableField("is_visible")
    private Boolean visible;
}
