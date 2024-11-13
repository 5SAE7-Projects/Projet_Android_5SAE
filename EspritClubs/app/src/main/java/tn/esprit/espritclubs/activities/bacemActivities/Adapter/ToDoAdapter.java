package tn.esprit.espritclubs.activities.bacemActivities.Adapter;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.activities.bacemActivities.AddNewTask;
import tn.esprit.espritclubs.database.AppDatabase;
import tn.esprit.espritclubs.entities.Task;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {
    private List<Task> mList;
    private Context context;
    private AppDatabase db;
    public ToDoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        final Task item = mList.get(position);
        db = AppDatabase.getDatabase(getContext());
        Context context = holder.itemView.getContext();
        holder.checkBox.setText(item.getTask());
        holder.checkBox.setChecked(toBoolean(item.getStatus()));
        holder.checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                // Update the database to mark task as completed
            } else {
                // Update the database to mark task as not completed
            }
        });
    }

    public boolean toBoolean(int n) {
        return n != 0;
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public void setTasks(List<Task> mList) {
        this.mList = mList;
        notifyItemChanged(mList.size());
    }

    public void deleteTask(int position) {

        db.taskDao().delete(mList.get(position));
        mList.remove(position);
        notifyItemRemoved(position);
        // Optional: Update database to delete the task
    }

    public void editItem(int position) {

        Task item = mList.get(position);
        // Update the database as needed
        Bundle bundle = new Bundle();
        bundle.putInt("Id", item.getId());
        bundle.putString("task", item.getTask());
        notifyItemChanged(position);
        // Launch AddNewTask dialog within the context of the parent fragment
        AddNewTask task = new AddNewTask();
        task.setArguments(bundle);
        FragmentActivity activity = (FragmentActivity) context;
        task.show(activity.getSupportFragmentManager(), task.getTag());
    }

    public Context getContext() {
        return context;


    }

    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}