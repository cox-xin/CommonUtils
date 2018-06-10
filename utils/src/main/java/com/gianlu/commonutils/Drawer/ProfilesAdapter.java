package com.gianlu.commonutils.Drawer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public abstract class ProfilesAdapter<P extends BaseDrawerProfile, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected final Context context;
    protected final List<P> profiles;
    protected final DrawerManager.ProfilesDrawerListener<P> listener;

    public ProfilesAdapter(Context context, List<P> profiles, DrawerManager.ProfilesDrawerListener<P> listener) {
        this.context = context;
        this.profiles = profiles;
        this.listener = listener;
    }

    public final void startProfilesTest() {
        for (int i = 0; i < profiles.size(); i++)
            if (i == profiles.size() - 1) runTest(i);
            else runTest(i);
    }

    protected abstract P getItem(int pos);

    protected abstract void runTest(int pos);

    public final void onBindViewHolder(@NonNull VH holder, int position) {
        final P profile = getItem(position);

        onBindViewHolder(holder, position, profile);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onDrawerProfileSelected(profile);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return listener != null && listener.onDrawerProfileLongClick(profile);
            }
        });
    }

    protected abstract void onBindViewHolder(@NonNull VH holder, int position, @NonNull P profile);

    @Override
    public final int getItemCount() {
        return profiles.size();
    }
}
