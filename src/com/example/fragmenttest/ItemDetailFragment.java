package com.example.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fragmenttest.dummy.DummyContent;
import com.squareup.picasso.Picasso;

/**
 * Fragmenti i cili shfaq� detalet n� ekran
 * Ky fragment shfaqet n� ItemListActivity aktivitet n�se �sht� si two-pane (tablet)
 * ose n� aktivitet t� ve�ant� si� �sht� ItemDetailActivity
 */
public class ItemDetailFragment extends Fragment {
    /**
     * Marrim ID-n� e elementit t� selektuar
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * P�rmbajtja e cila duhet t� shfaqet
     */
    private DummyContent.DummyItem mItem;

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Merr p�rmbajtjen p�rmes argumenteve t� cilat i kemi futur n� aktivitetet
        	// p�rket�se
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // Shfaq p�rmbajtjen n� TextView
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.teksti)).setText(mItem.content);
            ImageView fotografia = (ImageView) rootView.findViewById(R.id.fotografia);
            Picasso.with(getActivity()).load("http://artprojects.me/wp-content/uploads/2013/08/Picasso-Mural.jpg").into(fotografia);

            
        }

        return rootView;
    }
}
