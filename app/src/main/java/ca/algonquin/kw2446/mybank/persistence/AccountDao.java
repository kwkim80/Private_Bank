package ca.algonquin.kw2446.mybank.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ca.algonquin.kw2446.mybank.model.Account;
import ca.algonquin.kw2446.mybank.model.Money;

@Dao
public interface AccountDao {
    @Insert
    long[] insertAccount(Account... accounts);

    @Update
    int updateAccount(Account... accounts);

    @Delete
    int deleteAccount(Account... accounts);

    @Query("Select * from Account")
    LiveData<List<Account>> getAccountList();
}
