package com.songzhen.howcool.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * User
 *
 * @author lucas
 * @date 2018-08-27 20:42
 */
@TableName("t_user")
public class UserModel extends Model<UserModel> {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 * 
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Long id;

	/**
	 * 用户id
	 * 
	 */
	@TableField(value="u_id")
	private Long uId;

	/**
	 * 用户名
	 *
	 */
	@TableField(value="user_name")
	private String userName;

    /**
     * 密码
     *
     */
    @TableField(value="password")
    private String password;

	/**
	 * 电话
	 * 
	 */
	@TableField(value="mobile")
	private String mobile;

	/**
	 * 邮箱
	 * 
	 */
	@TableField(value="email")
	private String email;

	/**
	 * 账号状态：0.不启用；1.启用
	 * 
	 */
	@TableField(value="status")
	private byte status;

	/**
	 * 是否删除
	 * 
	 */
	@TableLogic
	@TableField(value="is_delete")
	private byte isDelete;

	/**
	 * 创建人
	 * 
	 */
	@TableField(value="create_by")
	private String createBy;

	/**
	 * 创建时间
	 * 
	 */
	@TableField(value="create_time")
	private Date createTime;

	/**
	 * 更新人
	 * 
	 */
	@TableField(value="update_by")
	private String updateBy;

	/**
	 * 更新时间
	 * 
	 */
	@TableField(value="update_time")
	private Date updateTime;

    
	/**
	 * 获取: 主键
	 * 
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置: 主键
	 * 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取: 用户id
	 * 
	 */
	public Long getUId() {
		return uId;
	}
	/**
	 * 设置: 用户id
	 * 
	 */
	public void setUId(Long uId) {
		this.uId = uId;
	}
    /**
     * 获取: 用户名
     *
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置: 用户名
     *
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * 获取: 密码
     *
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置: 密码
     *
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取: 电话
	 * 
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置: 电话
	 * 
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取: 邮箱
	 * 
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置: 邮箱
	 * 
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取: 账号状态：0.不启用；1.启用
	 * 
	 */
	public byte getStatus() {
		return status;
	}
	/**
	 * 设置: 账号状态：0.不启用；1.启用
	 * 
	 */
	public void setStatus(byte status) {
		this.status = status;
	}
	/**
	 * 获取: 是否删除
	 * 
	 */
	public byte getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置: 是否删除
	 * 
	 */
	public void setIsDelete(byte isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取: 创建人
	 * 
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置: 创建人
	 * 
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取: 创建时间
	 * 
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置: 创建时间
	 * 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取: 更新人
	 * 
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置: 更新人
	 * 
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取: 更新时间
	 * 
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置: 更新时间
	 * 
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}