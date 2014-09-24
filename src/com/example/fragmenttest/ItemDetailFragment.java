package com.example.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.fragmenttest.dummy.DummyContent;

/**
 * Fragmenti i cili shfaqë detalet në ekran
 * Ky fragment shfaqet në ItemListActivity aktivitet nëse është si two-pane (tablet)
 * ose në aktivitet të veçantë siç është ItemDetailActivity
 */
public class ItemDetailFragment extends Fragment {
    /**
     * Marrim ID-në e elementit të selektuar
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * Përmbajtja e cila duhet të shfaqet
     */
    private DummyContent.DummyItem mItem;

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Merr përmbajtjen përmes argumenteve të cilat i kemi futur në aktivitetet
        	// përketëse
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // Shfaq përmbajtjen në TextView
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
        }

        return rootView;
    }
}
