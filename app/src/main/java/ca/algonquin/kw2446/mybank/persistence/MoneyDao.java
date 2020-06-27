package ca.algonquin.kw2446.mybank.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import ca.algonquin.kw2446.mybank.model.Money;

@Dao
public interface MoneyDao {

   @Insert
    long[] insertMoney(Money... monies);

   @Update
    int updateMoney(Money... monies);

   @Delete
    int deleteMoney(Money... monies);

   @Query("Select * from Money")
   LiveData<List<Money>> getMoneyList();

//   @Query("Select sum(amount) from Money accountId=?")
//    LiveData<Double> getBalance();


    @Query("Select * from Money")
   List<Money> getList();

}
