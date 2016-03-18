package com.leoman.pay;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

// 鏀粯璁板綍
public class Payment {

	private Long id;
	private String create_date;
	private String motify_date;
	private String accout; // 鏀舵璐﹀彿
	private Double amount; // 浠樻閲戦
	private String bank; // 鏀舵閾惰
	private String expire; // 鍒版湡鏃堕棿
	private Double fee; // 鎵嬬画璐�
	private String memo; // 澶囨敞
	private Integer method; // 鏀粯鏂瑰紡
	private String paymemntMethod;

	private String operator; // 鎿嶄綔浜�
	private String payer; // 浠樻浜�
	private Timestamp paymentDate; // 浠樻鏃ユ湡
	private String paymentPluginId; // 鏀粯鎻掍欢ID
	private String sn; // 璁㈠崟缂栧彿
	private Integer status; // 鐘舵��

	private Integer type; // 绫诲瀷 璁㈠崟鏀粯 棰勫瓨娆�
	private Long member; // 浼氬憳
	private Long orders; // 璁㈠崟

	public static void main(String[] args) {
		XStream xs = new XStream();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", "yesong");

		System.out.println(xs.toXML(map));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getMotify_date() {
		return motify_date;
	}

	public void setMotify_date(String motify_date) {
		this.motify_date = motify_date;
	}

	public String getAccout() {
		return accout;
	}

	public void setAccout(String accout) {
		this.accout = accout;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getMethod() {
		return method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}

	public String getPaymemntMethod() {
		return paymemntMethod;
	}

	public void setPaymemntMethod(String paymemntMethod) {
		this.paymemntMethod = paymemntMethod;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentPluginId() {
		return paymentPluginId;
	}

	public void setPaymentPluginId(String paymentPluginId) {
		this.paymentPluginId = paymentPluginId;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getMember() {
		return member;
	}

	public void setMember(Long member) {
		this.member = member;
	}

	public Long getOrders() {
		return orders;
	}

	public void setOrders(Long orders) {
		this.orders = orders;
	}
}
