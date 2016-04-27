package es.currexify.server.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.google.appengine.api.datastore.Key;

@Entity
@Table(name="HISTORY_MODEL")
public class HistoryModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Key key;
	@Column(name="CARD_N")
	private String cardN;
	@Column(name="SOURCE_COIN")
	private String sCoin;
	@Column(name="DEST_COIN")
	private String dCoin;
	@Column(name="AMOUNT")
	private double amount;
	@Column(name="TYPE")
	private String type;
	@Column (name="STATE")
	private String state;
	@Column(name="ORIGIN_DATE")
	private Date oDate;
	@Column(name="END_DATE")
	private Date eDate;
	
		
	public HistoryModel(String cardN, String sCoin, String dCoin, 
			double amount, String type, String state, Date oDate, 
			Date eDate) {
		this.cardN = cardN;
		this.sCoin = sCoin;
		this.dCoin = dCoin;
		this.amount = amount;
		this.type = type;
		this.state = state;
		this.oDate = oDate;
		this.eDate = eDate;
	}
	
	public Key getId() {
		return key;
	}
	public void setId(Key key) {
		this.key = key;
	}
	public String getCardN() {
		return cardN;
	}
	public void setCardN(String cardN) {
		this.cardN = cardN;
	}
	public String getSCoin() {
		return sCoin;
	}
	public void setSCoin(String sCoin) {
		this.sCoin = sCoin;
	}
	public String getDCoin() {
		return dCoin;
	}
	public void setDCoin(String dCoin) {
		this.dCoin = dCoin;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getODate() {
		return oDate;
	}
	public void setODate(Date oDate) {
		this.oDate = oDate;
	}
	public Date getEDate() {
		return eDate;
	}
	public void setEDate(Date eDate) {
		this.eDate = eDate;
	}
}
