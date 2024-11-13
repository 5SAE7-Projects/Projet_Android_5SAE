package tn.esprit.espritclubs.fragments.bacem;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tn.esprit.espritclubs.OnDialogCloseListener;
import tn.esprit.espritclubs.R;
import tn.esprit.espritclubs.RecyclerViewTouchHelper;
import tn.esprit.espritclubs.activities.bacemActivities.Adapter.ToDoAdapter;
import tn.esprit.espritclubs.activities.bacemActivities.AddNewTask;
import tn.esprit.espritclubs.dao.TaskDao;
import tn.esprit.espritclubs.database.AppDatabase;
import tn.esprit.espritclubs.entities.Task;

public class TaskFragment extends Fragment implements OnDialogCloseListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public TaskFragment() {
        // Required empty public constructor
    }

    public static TaskFragment newInstance(String param1, String param2) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private FrameLayout frameLayout;
    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private FloatingActionButton addButton;
    private List<Task> mList = new ArrayList<>();
    private ToDoAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        addButton = view.findViewById(R.id.addButton1);

        loadTasks();

        addButton.setOnClickListener(view1 -> {
            AddNewTask.newInstance().show(getChildFragmentManager(), AddNewTask.TAG);

        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    private void loadTasks() {
        adapter = new ToDoAdapter(this.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        AppDatabase database = AppDatabase.getDatabase(getContext());
        TaskDao taskDao = database.taskDao();
        mList = taskDao.getAll();
        Collections.reverse(mList);
        adapter.setTasks(mList);
    }

    @Override
    public void onDialogClose(DialogInterface dialog) {
        this.loadTasks();
    }
}
