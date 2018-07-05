package com.example.tsd032.firevideo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v17.leanback.app.VerticalGridFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.VerticalGridPresenter;

import com.example.tsd032.firevideo.data.Video;
import com.example.tsd032.firevideo.data.VideoList;
import com.example.tsd032.firevideo.presenter.CardPresenter;

import java.util.List;

public class MainFragment extends VerticalGridFragment {

    private static final int NUM_COLUMNS = 5;

    private ArrayObjectAdapter mAdapter;
    private List<Video> mVideos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Videos");

        mVideos = VideoList.setupVideos();

        mAdapter = new ArrayObjectAdapter(new CardPresenter());

        for (int i = 0; i < mVideos.size(); i++) {
            mAdapter.add(mVideos.get(i));
        }

        setAdapter(mAdapter);

        VerticalGridPresenter gridPresenter = new VerticalGridPresenter();
        gridPresenter.setNumberOfColumns(NUM_COLUMNS);
        setGridPresenter(gridPresenter);


        setOnItemViewClickedListener(new OnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder viewHolder, Object item, RowPresenter.ViewHolder viewHolder1, Row row) {
                Intent intent = new Intent(getActivity(), PlaybackActivity.class);
                intent.putExtra(MainActivity.VIDEO, (Video) item);
                startActivity(intent);

            }
        });
    }
}
