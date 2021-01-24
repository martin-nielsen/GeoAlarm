package geoalarmapp.activity.mainactivity;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import geoalarmapp.data.entity.Alarm;

public class AlarmListAdapter extends ListAdapter<Alarm, AlarmViewHolder> {

    public AlarmListAdapter(@NonNull DiffUtil.ItemCallback<Alarm> diffCallback) {
        super(diffCallback);
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AlarmViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        Alarm current = getItem(position);
        holder.bind(current.name);
    }

    static class AlarmDiff extends DiffUtil.ItemCallback<Alarm> {

        @Override
        public boolean areItemsTheSame(@NonNull Alarm oldItem, @NonNull Alarm newItem) {
            return oldItem.name == newItem.name;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Alarm oldItem, @NonNull Alarm newItem) {
            return oldItem.name.equals(newItem.name);
        }
    }
}