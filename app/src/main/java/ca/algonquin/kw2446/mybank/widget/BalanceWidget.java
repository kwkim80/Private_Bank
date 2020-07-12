package ca.algonquin.kw2446.mybank.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import ca.algonquin.kw2446.mybank.R;
import ca.algonquin.kw2446.mybank.service.BalanceService;

/**
 * Implementation of App Widget functionality.
 */
public class BalanceWidget extends AppWidgetProvider {



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.balance_widget);
            Intent intent=new Intent(context, BalanceService.class);
            intent.putExtra("appWidgetId",appWidgetId);
            //context.startService(intent);
            context.startForegroundService(intent);
            appWidgetManager.updateAppWidget(appWidgetId,views);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

