package com.tenmiles.helpstack.activities;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.tenmiles.helpstack.R;
import com.tenmiles.helpstack.fragments.HSFragmentManager;
import com.tenmiles.helpstack.fragments.NewUserFragment;
import com.tenmiles.helpstack.logic.HSHelpStack;
import com.tenmiles.helpstack.logic.OnFetchedSuccessListener;
import com.tenmiles.helpstack.model.HSUser;

public class NewUserActivity extends HSActivityParent {
	
	NewUserFragment newUserFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		
		if (savedInstanceState == null) {
			NewUserFragment userFragment = new NewUserFragment();
			HSFragmentManager.putFragmentInActivity(this, R.id.container, userFragment, "NewUser");
		}
		
		newUserFragment = (NewUserFragment) HSFragmentManager.getFragmentInActivity(this, "NewUser");
		
	}
	
	@Override
	public void configureActionBar(ActionBar actionBar) {
		super.configureActionBar(actionBar);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("New User");
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_issue, menu);
		
		MenuItem nextMenu = menu.findItem(R.id.nextbutton);
		MenuItemCompat.setShowAsAction(nextMenu, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		
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
		else if (id == R.id.nextbutton) {
			// 
			setSupportProgressBarIndeterminateVisibility(true);
			HSHelpStack.getInstance(this).getGear().registerNewUser("Nalin", "Chhajer", "nalin@tenmiles.com", new OnFetchedSuccessListener() {
				
				@Override
				public void onSuccess(Object successObject) {
					// TODO Auto-generated method stub
					setSupportProgressBarIndeterminateVisibility(false);
					startNewIssueActivity((HSUser)successObject);
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					// TODO Auto-generated method stub
					
					setSupportProgressBarIndeterminateVisibility(false);
					
				}
			});
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void startNewIssueActivity(HSUser user) {
		HSActivityManager.startNewIssueActivity(this);
	}

}
