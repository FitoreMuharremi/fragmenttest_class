package com.example.fragmenttest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.fragmenttest.dummy.DummyContent;

/**
 * ListFragment e cila reprezenton list‘n t‘ cil‘n jemi duke e par‘ n‘ ekran,
 * ky fragment po ashtu p‘rkrah‘ tablet‘t, n‘ tablet‘ lejon elementet e list‘s
 * t‘ "aktivizohen" kur t‘ selektohen e cila ndihmon p‘rdoruesin t‘ jet‘ n‘ 
 * njohuri se cili element ‘sht‘ i selektuar n‘ ItemDetailFragment
 * 
 * Aktivitetet t‘ cilat e p‘rmbajn‘ k‘t‘ fragment, duhen ti implementojn‘ 
 * Callbacks (thirrjet sistemore) t‘ k‘tij fragmenti
 */
public class ItemListFragment extends ListFragment {

    /**
     * Kjo gjendje asociohet me saved instance state q‘ t‘ dijm‘ se
     * cila ngjarje ‘sht‘ ruajtur
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * Objekti p‘r callback
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * Pozita e selektuar
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * Interface-i i Callbacks q‘ secili aktivitet duhet ta implementoj‘
     */
    public interface Callbacks {
        /**
         * Kur nj‘ element t‘ selektohet, kjo metod‘ thirret
         */
        public void onItemSelected(String id);
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    public ItemListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                DummyContent.ITEMS));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // E kthejm‘ pozit‘n e ruajtur m‘ heret p‘rmes
        // savedInstanceState objektit
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Exception n‘se aktiviteti nuk e ka implementu interface-in "Callbacks"
        // Nj‘ shembull i mire se ku p‘rdoren exceptions.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Njofto callbacks aktiv q‘ nj‘ element ‘sht‘ klikuar
        // pra kjo i njofton t‘ gjith‘ aktivitetet q‘ elementi ‘sht‘
        // selektuar p‘r t‘ thirr‘ kod n‘ at‘ aktivitet
        mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Ruaje gjendjen e elementit t‘ aktivizuar
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Dhez‘ aktivizimin e elementeve t‘ selektuara n‘se jemi n‘ tablet
     * pra kur t‘ jet‘ choiceMode n‘ CHOICE_MODE_SINGLE, gjendja activated p‘r
     * tablet aktivizohet dhe e shohim q‘ n‘ tablet e kemi t‘ selektuar gjendjen.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    /**
     * Ruhet pozita e elementit t‘ aktivizuar
     * 
     */
    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }
}
