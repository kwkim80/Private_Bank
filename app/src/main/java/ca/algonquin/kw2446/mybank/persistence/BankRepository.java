package ca.algonquin.kw2446.mybank.persistence;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import ca.algonquin.kw2446.mybank.model.Account;
import ca.algonquin.kw2446.mybank.model.AccountBalance;
import ca.algonquin.kw2446.mybank.model.AccountSimple;
import ca.algonquin.kw2446.mybank.model.Money;

public class BankRepository {

    private MoneyDB db;
    private Context context;

    public BankRepository(Context context) {
        this.context=context;
        this.db = MoneyDB.getInstance(context);
    }

    public void insertMoney(Money... monies){ new InsertMoneyAsyncTask(db.getMoneyDao()).execute(monies); }
    public void updateMoney(Money... monies){ new upateMoneyAsyncTask(db.getMoneyDao()).execute(monies);}
    public void deleteMoney(Money... monies){ new deleteMoneyAsyncTask(db.getMoneyDao()).execute(monies); }

    public LiveData<List<Money>> getMoneyList(int type){
        switch (type){
            case 0: return db.getMoneyDao().getMoneyList(false);
            case 1: return db.getMoneyDao().getMoneyList(true);
            default: return db.getMoneyDao().getMoneyList();
        }
    }

    public LiveData<List<Money>> getMoneyList(int accountId, int type){
        switch (type){
            case 0: return db.getMoneyDao().getMoneyList(accountId,false);
            case 1: return db.getMoneyDao().getMoneyList(accountId,true);
            default: return db.getMoneyDao().getMoneyList(accountId);
        }
    }

    public LiveData<Double> getBalance(){ return db.getMoneyDao().getBalance(); }



    public void insertAccount(Account... accounts){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.getAccountDao().insertAccount(accounts);
                return null;
            }
        }.execute();
    }

    public void updateAccount(Account... account){
        //account.setModifiedAt(AppUtils.getCurrentDateTime());
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.getAccountDao().updateAccount(account);
                return null;
            }
        }.execute();
    }

    public void deleteAccount(Account... account){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.getAccountDao().deleteAccount(account);
                return null;
            }
        }.execute();
    }


    public LiveData<List<String>> getAccountTitleList(){
        return  db.getAccountDao().getAccountTitleList();
    }
    public LiveData<List<Account>> getAccountList(){
        return  db.getAccountDao().getAccountList();
    }
    public LiveData<List<AccountSimple>> getAccountSimpleList(){ return  db.getAccountDao().getAccountSimpleList(); }
    public LiveData<List<AccountBalance>> getAccountsWithBalance(){
        return db.getAccountDao().getAccountsWithBalance();
    }

    public LiveData<Account> getAccount(int id){
        return  db.getAccountDao().getAccount(id);
    }


    //money AsynTask

    public class InsertMoneyAsyncTask extends AsyncTask<Money, Void, Void>{
        private MoneyDao dao;

        public InsertMoneyAsyncTask(MoneyDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Money... monies) {
            dao.insertMoney(monies);
            return null;
        }
    }
    public class upateMoneyAsyncTask extends AsyncTask<Money, Void, Void>{
        private MoneyDao dao;

        public upateMoneyAsyncTask(MoneyDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Money... monies) {
            dao.updateMoney(monies);
            return null;
        }
    }
    public class deleteMoneyAsyncTask extends AsyncTask<Money, Void, Void>{
        private MoneyDao dao;

        public deleteMoneyAsyncTask(MoneyDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Money... monies) {
            dao.deleteMoney(monies);
            return null;
        }
    }
}
