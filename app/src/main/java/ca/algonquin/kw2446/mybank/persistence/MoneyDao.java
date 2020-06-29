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

    @Query("SELECT * FROM Money WHERE isOut = :isOut")
    LiveData<List<Money>> getMoneyList(boolean isOut);

   @Query("Select ifnull(sum(amount),0) from Money where accountId = :id")
    LiveData<Double> getBalance(int id);

    @Query("SELECT ifnull(sum(amount),0)  FROM Money ")
    LiveData<Double> getBalance();


}
