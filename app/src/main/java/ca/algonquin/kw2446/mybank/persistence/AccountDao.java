package ca.algonquin.kw2446.mybank.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ca.algonquin.kw2446.mybank.model.Account;
import ca.algonquin.kw2446.mybank.model.AccountBalance;
import ca.algonquin.kw2446.mybank.model.AccountSimple;
import ca.algonquin.kw2446.mybank.model.Money;

@Dao
public interface AccountDao {
    @Insert
    long[] insertAccount(Account... accounts);

    @Update
    int updateAccount(Account... accounts);

    @Delete
    int deleteAccount(Account... accounts);

    @Query("Select title from Account")
    LiveData<List<String>> getAccountTitleList();

    @Query("Select * from Account where id=:id")
    LiveData<Account> getAccount(int id);

    @Query("Select id, title from Account")
    LiveData<List<AccountSimple>> getAccountSimpleList();

    @Query("Select * from Account")
    LiveData<List<Account>> getAccountList();

    @Query("Select a.id, accountNumber, a.title, sum(b.amount) as balance from Account a "
            +"left join Money b on a.id=b.accountId "
            +"group by a.id, accountNumber,a.title")
    LiveData<List<AccountBalance>> getAccountsWithBalance();
}
