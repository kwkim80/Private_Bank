package ca.algonquin.kw2446.mybank.service;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import ca.algonquin.kw2446.mybank.MainActivity;
import ca.algonquin.kw2446.mybank.R;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;
import ca.algonquin.kw2446.mybank.util.ApplicationClass;

public class BalanceService extends Service {

    Intent intent;
    BankRepository bankRepository;
    public BalanceService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        this.intent=intent;
       bankRepository=new BankRepository(getApplicationContext());
        if(ApplicationClass.connectionAvailable(getApplicationContext())){
            RemoteViews views=new RemoteViews("ca.algonquin.kw2446.mybank", R.layout.balance_widget);
            views.setTextViewText(R.id.tvTitle, "Total Balance");
            views.setTextViewText(R.id.tvBalance,"No data");
            views.setImageViewResource(R.id.ivSynce,R.drawable.ic_refresh_black_24dp);
            views.setImageViewResource(R.id.ivIcon,R.drawable.ic_dollar);
            AppWidgetManager.getInstance(getApplicationContext()).updateAppWidget(intent.getIntExtra("appWidgetId",0),views);
            new GetStoriesInBackground().execute();

        }else{
            Toast.makeText(getApplicationContext(), "Please make sure your phone has an active internet connection", Toast.LENGTH_SHORT).show();
            stopSelf();
        }
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class GetStoriesInBackground extends AsyncTask<Integer, Integer, Double>
    {

        @Override
        protected Double doInBackground(Integer... params) {
            return bankRepository.getBalanceValue();
        }


        @Override
        protected void onPostExecute(Double balance) {



            RemoteViews views=new RemoteViews(getPackageName(), R.layout.balance_widget);

            views.setTextViewText(R.id.tvBalance, String.format("$%.2f",balance));

            views.setImageViewResource(R.id.ivSynce,R.drawable.ic_refresh_black_24dp);
            views.setImageViewResource(R.id.ivIcon,R.drawable.ic_dollar);


             Intent linkIntent=new Intent(getApplicationContext(), MainActivity.class);
//
            PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),
                    0, linkIntent,PendingIntent.FLAG_UPDATE_CURRENT);

          views.setOnClickPendingIntent(R.id.tvBalance,  pendingIntent);

            PendingIntent pendingIntentSync=PendingIntent.getService(getApplicationContext(),0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            views.setOnClickPendingIntent(R.id.ivSynce, pendingIntentSync);

            AppWidgetManager.getInstance(getApplicationContext()).updateAppWidget(intent.getIntExtra("appWidgetId",0),views);

        }

    }
}
