package com.benoitletondor.easybudget;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.benoitletondor.easybudget.view.calendar.CalendarFragment;
import com.benoitletondor.easybudget.view.expenses.ExpensesRecyclerViewAdapter;
import com.melnykov.fab.FloatingActionButton;
import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;

/**
 * @author Benoit LETONDOR
 */
public class MainActivity extends ActionBarActivity
{
    private CalendarFragment            calendarFragment;
    private RecyclerView                expensesRecyclerView;
    private LinearLayoutManager         expensesLayoutManager;
    private ExpensesRecyclerViewAdapter expensesViewAdapter;

// ------------------------------------------>

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCalendarFragment();
        initRecyclerView();
    }

    @Override
    protected void onDestroy()
    {
        calendarFragment = null;
        expensesRecyclerView = null;
        expensesLayoutManager = null;
        expensesViewAdapter = null;

        super.onDestroy();
    }

// ------------------------------------------>

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

// ------------------------------------------>

    private void initCalendarFragment()
    {
        calendarFragment = new CalendarFragment();

        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
        args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, false);

        calendarFragment.setArguments(args);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendarView, calendarFragment);
        t.commit();
    }

    private void initRecyclerView()
    {
        expensesRecyclerView = (RecyclerView) findViewById(R.id.expensesRecyclerView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToRecyclerView(expensesRecyclerView);

        expensesLayoutManager = new LinearLayoutManager(this);
        expensesRecyclerView.setLayoutManager(expensesLayoutManager);

        expensesViewAdapter = new ExpensesRecyclerViewAdapter();
        expensesRecyclerView.setAdapter(expensesViewAdapter);
    }
}