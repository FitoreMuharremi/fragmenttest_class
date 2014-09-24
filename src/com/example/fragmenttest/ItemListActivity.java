package com.example.fragmenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;




/**
 * Aktiviteti i cili reprezenton list‘n e elementeve. Ky aktivitet
 * ka prezantim ndryshe n‘ telefon, ndryshe n‘ tablet. N‘ telefon aktivteti
 * prezanton list‘n e elementeve sikur q‘ e pam‘ n‘ sllajde, kurse n‘ tablet
 * shfaqet "two-pane layout"
 * 
 * Aktiviteti implementon interface-in Callbacks, p‘rndryshe aplikacioni
 * crash n‘se ky interface nuk ‘sht‘ i implementuar.
 */
public class ItemListActivity extends FragmentActivity
        implements ItemListFragment.Callbacks {

    /**
     * E ruajm‘ gjendjen n‘ t‘ cil‘n gjindet aplikacioni
     * pra e shikojm‘ n‘se ‘sht‘ table (two-pane layout) apo telefon (single-pane layout)
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        if (findViewById(R.id.item_detail_container) != null) {
        	// Kur ky aktivitet t‘ ekzekutohet, detail container do t‘ jet‘
        	// prezent vet‘m n‘ ekrane t‘ m‘dha, n‘se kjo view ‘sht‘ prezente
        	// at‘her‘ aktiviteti ‘sht‘ two-pane layout
            mTwoPane = true;

            // N‘ two-pane layout elementet e list‘s
            // duhet t‘ p‘rmbajn‘ gjendjen e aktivizuar
            // kur t‘ klikohen
            ((ItemListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.item_list))
                    .setActivateOnItemClick(true);
        }
    }
    
    /**
     * Kjo metod‘ implementohet n‘ rast se d‘shirojm‘ ti shtojm‘ 
     * elementet n‘ meny sikur n‘ pamjen n‘ prezantim.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add("Elementi pare");
    	menu.add("Elementi dyte");
    	menu.add("Elementi trete");
    	menu.add("Elementi katert");
    	menu.add("Elementi peste");
    	return super.onCreateOptionsMenu(menu);
    }

    /**
     * Pasi ta selektojm‘ nj‘ element n‘ list‘, kjo metod‘ thirret 
     * p‘rmes ItemListFragment, pra, selektimi b‘het n‘ fragment
     * dhe ne p‘rmes fragmentit e th‘rrasim k‘t‘ metod‘
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
        	// N‘ two-pane layout, ne e shfaqim detail view p‘rmes
        	// FragmentManager dhe FragmentTransaction
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
            
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            
            FragmentManager fManager = getSupportFragmentManager();
            
            FragmentTransaction fTransaction = fManager.beginTransaction();
            fTransaction.replace(R.id.item_detail_container, fragment);
            fTransaction.commit();

        } else {
            // N‘ single-pane vet‘m e shfaqim aktivitetin
            Intent detailIntent = new Intent(this, ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
