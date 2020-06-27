package ca.algonquin.kw2446.mybank.persistence;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import ca.algonquin.kw2446.mybank.model.Account;
import ca.algonquin.kw2446.mybank.model.Money;

public class BankRepository {

    private MoneyDB db;
    private Context context;

    public BankRepository(Context context) {
        this.context=context;
        this.db = MoneyDB.getInstance(context);
    }

    public void insertMoney(Money... monies){
        new InsertMoneyAsyncTask(db.getMoneyDao()).execute(monies);
    }

    public void updateMoney(Money... monies){
        new upateMoneyAsyncTask(db.getMoneyDao()).execute(monies);
    }

    public void deleteMoney(Money... monies){
         new deleteMoneyAsyncTask(db.getMoneyDao()).execute(monies);
    }

    public LiveData<List<Money>> getMoneyList(){
        return db.getMoneyDao().getMoneyList();
    }

    public ArrayList<Money> getList(){
        ArrayList<Money> result=(ArrayList<Money>) db.getMoneyDao().getList();
    return result;
    }

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

    public void deleteAccount(int id){

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

    public LiveData<List<Account>> getAccountList(){
        return  db.getAccountDao().getAccountList();
    }

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
