package cl.monsoon.s1next.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cl.monsoon.s1next.R;
import cl.monsoon.s1next.model.Forum;
import cl.monsoon.s1next.singleton.Config;
import cl.monsoon.s1next.util.ColorUtil;
import cl.monsoon.s1next.util.ViewHelper;

public final class ForumListRecyclerAdapter extends RecyclerAdapter<Forum, ForumListRecyclerAdapter.ViewHolder> {

    private final int mSecondaryTextColor;

    public ForumListRecyclerAdapter() {
        setHasStableIds(true);

        mSecondaryTextColor = ColorUtil.a(Config.getColorAccent(), Config.getSecondaryTextAlpha());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.single_line_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Forum forum = mList.get(position);

        TextView textView = holder.mTextView;
        textView.setText(forum.getName());
        // add today's posts count to each forum
        if (forum.getTodayPosts() != 0) {
            int start = textView.length();
            ViewHelper.concatWithTwoSpaces(textView, forum.getTodayPosts());
            ViewHelper.setForegroundColor(textView, mSecondaryTextColor, start, textView.length());
        }
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(mList.get(position).getId());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView;
            ViewHelper.updateTextSize(mTextView);
        }
    }
}
