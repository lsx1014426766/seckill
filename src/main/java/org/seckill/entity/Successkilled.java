package org.seckill.entity;

import java.io.Serializable;
import java.util.Date;

public class Successkilled implements Serializable {
	private static final long serialVersionUID = 1L;
	private long seckillId;
	private long userPhone;
	private short state;
	private Date createTime;
	private Seckill seckill;

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	public Successkilled() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Successkilld [seckillId=" + seckillId + ", userPhone="
				+ userPhone + ", state=" + state + ", seckill=" + seckill + "]";
	}

}
