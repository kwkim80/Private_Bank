package ca.algonquin.kw2446.mybank.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account implements Parcelable {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String accountNumber;
    private String ownerName;
    private String title;
    private String createAt;
    private boolean isAvailable;


    public Account(String accountNumber, String ownerName, String title, String createAt,boolean isAvailable) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.title = title;
        this.createAt = createAt;
        this.isAvailable = isAvailable;
    }


    protected Account(Parcel in) {
        id = in.readInt();
        accountNumber = in.readString();
        ownerName = in.readString();
        title = in.readString();
        createAt = in.readString();
       isAvailable = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(accountNumber);
        dest.writeString(ownerName);
        dest.writeString(title);
        dest.writeString(createAt);
       dest.writeByte((byte) (isAvailable ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateAt() {
        return createAt;
    }
    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

//    @Override
//    public String toString() {
//        return "Account{" +
//                "id=" + id +
//                ", accountNumber='" + accountNumber + '\'' +
//                ", ownerName='" + ownerName + '\'' +
//                ", title='" + title + '\'' +
//                ", createAt='" + createAt + '\'' +
//                ", isAvailable=" + isAvailable +
//                '}';
//    }
}
