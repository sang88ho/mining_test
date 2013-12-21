package sogang.ex.mining.exchange;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Exchange {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd HH:mm:ss");
		
	private Integer id;

	private String bank;	
	
	private String nation;

	private String monetaryCode;
	
	private Float transferSend;
	
	private Float transferReceive;
	
	private Float cashSend;
	
	private Float cashReceive;
	
	private Float saleStandard;
	
	private Float usdExchangeRate;
	
	private Calendar date;

	public Exchange() {
		super();
	}
	
	public Exchange(String bank, String nation, String monetaryCode, Date date) {
		super();
		this.bank = bank;		
		this.nation = nation;
		this.monetaryCode = monetaryCode;
		this.date = Calendar.getInstance();
		this.date.setTime(date);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getMonetaryCode() {
		return monetaryCode;
	}

	public void setMonetaryCode(String monetaryCode) {
		this.monetaryCode = monetaryCode;
	}

	public Float getTransferSend() {
		return transferSend;
	}

	public void setTransferSend(Float transferSend) {
		this.transferSend = transferSend;
	}
	
	public Float getTransferReceive() {
		return transferReceive;
	}

	public void setTransferReceive(Float transferReceive) {
		this.transferReceive = transferReceive;
	}

	public Float getCashSend() {
		return cashSend;
	}

	public void setCashSend(Float cashSend) {
		this.cashSend = cashSend;
	}

	public Float getCashReceive() {
		return cashReceive;
	}

	public void setCashReceive(Float cashReceive) {
		this.cashReceive = cashReceive;
	}

	public Float getSaleStandard() {
		return saleStandard;
	}

	public void setSaleStandard(Float saleStandard) {
		this.saleStandard = saleStandard;
	}

	public Float getUsdExchangeRate() {
		return usdExchangeRate;
	}

	public void setUsdExchangeRate(Float usdExchangeRate) {
		this.usdExchangeRate = usdExchangeRate;
	}

	public String getDate() {
		return date == null ? null : sdf.format(date.getTime());
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	// 추가 메소드. 은행명을 한글로
	public String convertBanktoKorean(String bank) {
		if (bank.equals("shinhan"))		return "신한";
		else if (bank.equals("woori"))	return "우리";
		else if (bank.equals("kiup"))	return "기업";
		else if (bank.equals("hana"))	return "하나";
		else if (bank.equals("jeil"))	return "제일";
		else 							return null;
	}
	
	@Override
	public String toString() {
		return "Exchange [id=" + id + ", bank=" + bank + ", nation=" + nation + ", monetaryCode=" + monetaryCode + ", transferSend=" + transferSend
				+ ", transferReceive=" + transferReceive + ", cashSend=" + cashSend + ", cashReceive=" + cashReceive + ", saleStandard="
				+ saleStandard + ", usdExchangeRate=" + usdExchangeRate + ", date=" + date + "]";
	}
}
