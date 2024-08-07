package my.edu.um.umpoint.modules.space.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import my.edu.um.umpoint.common.entity.BaseEntity;

import java.io.Serial;
import java.util.Date;
import java.util.List;

/**
 * Space
 *
 * @author Tan Chun Hong tanchunhong717@gmail.com
 * @since 1.0.0 2024-08-03
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("spc_space")
public class SpaceEntity extends BaseEntity {
    /**
     * Name
     */
	private String name;
    /**
     * Category ID
     */
	private Long catId;
    /**
     * Department ID
     */
	private Long deptId;
    /**
     * Description
     */
	private String description;
    /**
     * Facilities
     */
	private String facilities;
	/**
	 * Updater
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;
	/**
	 * Update date
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;
	/**
	 * Category name
	 */
	@TableField(exist = false)
	private String category;
	/**
	 * Department name
	 */
	@TableField(exist = false)
	private String deptName;
	/**
	 * Image url list
	 */
	@TableField(exist = false)
	private List<String> imageUrls;
	/**
	 * Space tag list
	 */
	@TableField(exist = false)
	private List<String> tags;
}