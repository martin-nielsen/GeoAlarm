package geoalarmapp.activity.mainactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import geoalarmapp.R;

public class AlarmViewHolder extends RecyclerView.ViewHolder {
    private final TextView alarmItemView;

    private AlarmViewHolder(View itemView) {
        super(itemView);
        alarmItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        alarmItemView.setText(text);
    }

    static AlarmViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new AlarmViewHolder(view);
    }
}
