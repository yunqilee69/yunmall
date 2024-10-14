package icu.yunke.framework.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Id实体类，只包含ID
 * 方便切换id生成策略
 */
@Data
public class IDEntity {

    @TableId(type= IdType.ASSIGN_ID)
    private Long id;

}
