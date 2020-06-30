package ca.algonquin.kw2446.mybank.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"accountId"})},
        foreignKeys = @ForeignKey(entity = Account.class,
        parentColumns = "id",
        childColumns = "accountId",
        onDelete = ForeignKey.RESTRICT))
public class Money implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int accountId;
    private String opponent;
    private double amount;
    private boolean isOut;
    private String timestamp;
    private String memo;



    @Ignore
    public Money(int accountId, String opponent, double amount, boolean isOut) {
       this(accountId,opponent,amount,isOut,"");
    }
    @Ignore
    public Money(int accountId,String opponent, double amount, boolean isOut, String memo) {
        this(accountId,opponent,amount,isOut,memo,"");
    }

    public Money(int accountId, String opponent, double amount, boolean isOut, String memo, String timestamp) {
        this.opponent = opponent;
        this.amount = amount;
        this.isOut = isOut;
        this.timestamp = timestamp;
        this.memo = memo;
        this.accountId = accountId;
    }

    protected Money(Parcel in) {
        id = in.readInt();
        accountId = in.readInt();
        opponent = in.readString();
        amount = in.readDouble();
        isOut = in.readByte() != 0;
        timestamp = in.readString();
        memo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(accountId);
        dest.writeString(opponent);
        dest.writeDouble(amount);
        dest.writeByte((byte) (isOut ? 1 : 0));
        dest.writeString(timestamp);
        dest.writeString(memo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Money> CREATOR = new Creator<Money>() {
        @Override
        public Money createFromParcel(Parcel in) {
            return new Money(in);
        }

        @Override
        public Money[] newArray(int size) {
            return new Money[size];
        }
    };

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public boolean isOut() { return isOut; }
    public void setOut(boolean out) {isOut = out; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Money{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", opponent='" + opponent + '\'' +
                ", amount=" + amount +
                ", isOut=" + isOut +
                ", timestamp='" + timestamp + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
