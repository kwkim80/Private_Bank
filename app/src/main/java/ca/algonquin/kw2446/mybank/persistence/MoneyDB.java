package ca.algonquin.kw2446.mybank.persistence;

import android.content.Context;
import android.content.Entity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ca.algonquin.kw2446.mybank.model.Account;
import ca.algonquin.kw2446.mybank.model.Money;

@Database(entities = {Money.class, Account.class}, version = 3, exportSchema = true)
public abstract class MoneyDB extends RoomDatabase {

    public static final String DB_NAME = "MoneyDB";
    private static MoneyDB instance;

    public static MoneyDB getInstance(Context context) {
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),MoneyDB.class, DB_NAME).build();
        }
        return instance;
    }

    public abstract MoneyDao getMoneyDao();
    public abstract AccountDao getAccountDao();
}
