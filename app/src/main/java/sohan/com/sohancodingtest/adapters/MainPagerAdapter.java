package sohan.com.sohancodingtest.adapters;

import android.content.Context;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Hashtable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import sohan.com.sohancodingtest.R;
import sohan.com.sohancodingtest.ui.topalbumslisting.TopAlbumsFragment;
import sohan.com.sohancodingtest.ui.topartistslisting.TopArtistsFragment;
import sohan.com.sohancodingtest.ui.toptrackslisting.TopTracksFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private static final int NUMBER_OF_ITEMS = 3;
    private static final int TOP_ARTISTS_INDEX = 0;
    private static final int TOP_ALBUMS_INDEX = 1;
    private static final int TOP_TRACKS_INDEX = 2;
    private String topArtistsTitle;
    private String topTracksTitle;
    private String topAlbumsTitle;

    protected Hashtable<Integer, WeakReference<Fragment>> fragments;

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        topArtistsTitle = context.getString(R.string.top_artists_title);
        topTracksTitle = context.getString(R.string.top_tracks_title);
        topAlbumsTitle = context.getString(R.string.top_albums_title);
        fragments = new Hashtable<>();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TOP_ARTISTS_INDEX: {
                Fragment fr = TopArtistsFragment.newInstance();
                fragments.put(position, new WeakReference<>(fr));
                return fr;
            }
            case TOP_ALBUMS_INDEX: {
                Fragment fr = TopAlbumsFragment.newInstance();
                fragments.put(position, new WeakReference<>(fr));
                return fr;
            }
            case TOP_TRACKS_INDEX: {
                Fragment fr = TopTracksFragment.newInstance();
                fragments.put(position, new WeakReference<>(fr));
                return fr;
            }
        }
        return new TopArtistsFragment();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case TOP_ARTISTS_INDEX: {
                return topArtistsTitle;
            }
            case TOP_ALBUMS_INDEX: {

                return topAlbumsTitle;
            }
            case TOP_TRACKS_INDEX: {
                return topTracksTitle;
            }
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return NUMBER_OF_ITEMS;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        fragments.remove(position);
    }

    public ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> list = new ArrayList<>();
        for (int i = 0; i < fragments.size(); i++) {
            list.add(fragments.get(i).get());
        }
        return list;
    }
}
