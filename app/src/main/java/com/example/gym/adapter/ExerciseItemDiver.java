package com.example.gym.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by DefaultAccount on 9/25/2016.
 */
public class ExerciseItemDiver extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public ExerciseItemDiver(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = mSpace;
        }
    }
}
