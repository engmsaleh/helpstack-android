package com.tenmiles.helpstack.activities;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.tenmiles.helpstack.R;
import com.tenmiles.helpstack.fragments.HSFragmentManager;
import com.tenmiles.helpstack.fragments.NewIssueFragment;

public class NewIssueActivity extends HSActivityParent {

	NewIssueFragment newIssueFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_issue);

		if (savedInstanceState == null) {
			NewIssueFragment issueFragment = new NewIssueFragment();
			HSFragmentManager.putFragmentInActivity(this, R.id.container, issueFragment, "Issue");
		}
		
		newIssueFragment = (NewIssueFragment) HSFragmentManager.getFragmentInActivity(this, "Issue");
	}
	
	@Override
    public void configureActionBar(ActionBar actionBar) {
    	super.configureActionBar(actionBar);
    	
    	actionBar.setTitle("New User");
    	actionBar.setDisplayHomeAsUpEnabled(true);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.issue_menu, menu);
		
		MenuItem doneMenu = menu.findItem(R.id.doneItem);
		MenuItemCompat.setShowAsAction(doneMenu, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
			return true;
		}
		else if (id == R.id.doneItem) {
			newIssueFragment = new NewIssueFragment();
			
			HSFragmentManager.putFragmentInActivity(this, R.id.container, newIssueFragment, "Issue");
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}