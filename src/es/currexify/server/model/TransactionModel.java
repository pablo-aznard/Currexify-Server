package es.currexify.server.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.appengine.api.datastore.Key;

@Entity
@Table(name = "TRANSACTION_MODEL")
public class TransactionModel implements Serializable,
		Comparable<TransactionModel> {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Key key;
	@Column(name = "CARD_N")
	private String cardN;
	@Column(name = "SOURCE_COIN")
	private String sCoin;
	@Column(name = "DEST_COIN")
	private String dCoin;
	@Column(name = "AMOUNT")
	private double amount;
	@Column(name = "AMOUNT_LEFT")
	private double amountLeft;
	@Column(name = "END_DATE")
	private Date eDate;
	@Column(name = "FRIEND_ID")
	private Long friendId;
	@Column(name = "CHARGE")
	private double charge;

	public TransactionModel(String cardN, String sCoin, String dCoin,
			double amount, Date eDate, double charge) {
		this.cardN = cardN;
		this.sCoin = sCoin;
		this.dCoin = dCoin;
		this.amount = amount;
		this.amountLeft = amount;
		this.eDate = eDate;
		this.friendId = 0L;
		this.charge = charge;
	}

	public TransactionModel(String cardN, String sCoin, String dCoin,
			double amount, Date eDate, Long friendId) {
		this.cardN = cardN;
		this.sCoin = sCoin;
		this.dCoin = dCoin;
		this.amount = amount;
		this.amountLeft = amount;
		this.eDate = eDate;
		this.charge = 0.01;
		this.friendId = friendId;
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

	public double getAmountLeft() {
		return amountLeft;
	}

	public void setAmountLeft(double amountLeft) {
		this.amountLeft = amountLeft;
	}

	public Date getEDate() {
		return eDate;
	}

	public void setEDate(Date eDate) {
		this.eDate = eDate;
	}

	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}
	
	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}
	
	public static Comparator<TransactionModel> DateComparator = new Comparator<TransactionModel>() {

		public int compare(TransactionModel t1, TransactionModel t2) {

			// ascending order
			return t1.compareTo(t2);

			// descending order
			// return fruitName2.compareTo(fruitName1);
		}

	};

	 @Override
	 public int compareTo(TransactionModel tm) {
	 int compareDate = (int) ((TransactionModel) tm).getEDate().getTime();
	
	 //ascending order
	 return (int) (this.eDate.getTime() - compareDate);
	
	 }
}
