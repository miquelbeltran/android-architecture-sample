package work.beltran.discogsbrowser.ui.main.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.eyeem.recyclerviewtools.adapter.WrapAdapter;

import javax.inject.Inject;

import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.ui.App;
import work.beltran.discogsbrowser.ui.main.common.CustomToolbar;

/**
 * Created by Miquel Beltran on 06.05.16.
 * More on http://beltran.work
 */
public class SearchFragment extends Fragment {

    private SearchRecordsAdapter adapter;
    private String searchTerm = "";

    @Inject
    public void setAdapter(SearchRecordsAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).getApiComponent().inject(this);
        ((App) getActivity().getApplication()).getApiComponent().inject(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(getTag(), adapter.getBundle());
        outState.putString("SEARCH", searchTerm);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            adapter.loadBundle(savedInstanceState.getBundle(getTag()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initRecyclerView(view, inflater, savedInstanceState);
        return view;
    }

    private void initRecyclerView(View view, LayoutInflater inflater, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_records);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        if (recyclerView != null) {
            recyclerView.setLayoutManager(layoutManager);
            WrapAdapter wrapAdapter = new WrapAdapter(adapter);
            recyclerView.setAdapter(wrapAdapter);
            initHeaderFooter(inflater, recyclerView, wrapAdapter, savedInstanceState);
        }
    }

    private void initHeaderFooter(LayoutInflater inflater, RecyclerView recyclerView, WrapAdapter wrapAdapter, Bundle savedInstanceState) {
        final View header = inflater.inflate(R.layout.header_search, recyclerView, false);
        wrapAdapter.addHeader(header);
        final SearchView searchView = (SearchView) header.findViewById(R.id.searchView);
        if (savedInstanceState != null) {
            searchTerm = savedInstanceState.getString("SEARCH");
            searchView.setQuery(searchTerm, false);
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchTerm = query;
                adapter.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        CustomToolbar.setToolbar(this, header);
        View footer = inflater.inflate(R.layout.footer, recyclerView, false);
        wrapAdapter.addFooter(footer);
    }
}
