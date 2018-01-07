package com.example.bradj.eventitorg.Model.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bradj.eventitorg.Model.Entity.Event;
import com.example.bradj.eventitorg.R;

import java.util.List;

/**
 * Created by Bradley Blanchard on 2017-12-28.
 */

public class EventRVAdapter extends RecyclerView.Adapter<EventRVAdapter.ViewHolder> {

    private List<Event> events;
    private Context context;
    private EventItemListener mItemListener;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvEventDetail;
        EventItemListener mItemListener;

        public ViewHolder(View itemView, EventItemListener itemListener) {
            super(itemView);
            tvEventDetail = (TextView) itemView.findViewById(android.R.id.text1);

            this.mItemListener = itemListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Event event = getItem(getAdapterPosition());
            this.mItemListener.onEventClick(event.getEventId());

            notifyDataSetChanged();
        }
    } // Inner Class End

    public EventRVAdapter(Context context, List<Event> events, EventItemListener itemListener) {
        this.events = events;
        this.context = context;
        this.mItemListener = itemListener;
    }
    public EventRVAdapter(Context context, List<Event> events) {
        this.events = events;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View eventView = inflater.from(parent.getContext()).inflate(R.layout.event_cell, parent, false);

        ViewHolder viewHolder = new ViewHolder(eventView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Event event = events.get(position);
        TextView textView = holder.tvEventDetail;
        textView.setText(event.getName());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void updateEvents(List<Event> items) {
        this.events = items;
        notifyDataSetChanged();
    }

    private Event getItem(int adapterPosition) {
        return events.get(adapterPosition);
    }

    public interface EventItemListener {
        void onEventClick(long id);
    }


}
