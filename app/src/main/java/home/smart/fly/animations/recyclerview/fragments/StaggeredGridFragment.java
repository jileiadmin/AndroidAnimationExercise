package home.smart.fly.animations.recyclerview.fragments;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import home.smart.fly.animations.R;
import home.smart.fly.animations.recyclerview.DataFactory;
import home.smart.fly.animations.recyclerview.adapter.StaggeredRecyclerViewAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StaggeredGridFragment extends BaseListFragment<String> {


    @NotNull
    @Override
    public RecyclerView.LayoutManager getCustomLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_grid_view;
    }

    @NotNull
    @Override
    public RecyclerView.Adapter<?> getCustomAdapter() {
        return new StaggeredRecyclerViewAdapter(datas);
    }

    @NotNull
    @Override
    public ArrayList<String> loadDatas() {
        return DataFactory.INSTANCE.initDefaultData(getContext());
    }
}
