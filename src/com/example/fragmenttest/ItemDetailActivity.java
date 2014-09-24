package com.example.fragmenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;


/**
 * Ky aktivitet shfaqet vetëm në telefon, në tablet shfaqen "kry-për-kry"
 * lista edhe përmbajtja, në telefon shfaqet ky aktivitet nëse elementi selektohet.
 * Ky aktivitet është vetëm një mbajtës i fragmentit, kurgjo nuk ka përveç
 * fragmentin për detale.
 */
public class ItemDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        // I tregojmë action bar që dëshirojmë 
        // tek logo të jetë "UP" e cila të kthen në faqën e parë
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState nuk është null nëse gjendja e fragmentit
        // është ruajtur prej konfigurimit të mëparshëm (përshembull 
        // kur e kemi ndrru orientimin e pajisjes prej portrait në landscape).
        // Në këtë mënyrë fragmenti ri-bashkohet automatikisht për mos të 
        // pasë nevojë që manualisht ta shtojmë atë. E kemi spjeguar në
        // orët e mëparshme
        if (savedInstanceState == null) {
            // Rutina e njëjtë, e krijojmë fragmentin dhe e shtojmë përmes
        	// në aktivitet përmes FragmentTransaction
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            
            FragmentManager fManager = getSupportFragmentManager();
            
            FragmentTransaction fTransaction = fManager.beginTransaction();
            fTransaction.add(R.id.item_detail_container, fragment);
            fTransaction.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
        	// Nëse kjo ID është prezente (e lëshuar përmes setDisplayHomeAsUpEnabled())
            // atëherë në krye të aktivitetit ku është emri, nëse ajo pullë preket
        	// kthehet në fillim të aplikacionit, se sa të shkojë prapa në back stack.
        	// 
        	// Për më shumë, lexoni në:
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, ItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
