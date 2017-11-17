package motobeans.com.todolistexample.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView.LayoutManager
import kotlinx.android.synthetic.main.activity_task.toolbar
import motobeans.com.floweraura_deliveryapp_kotlin.MVP.View.Adapters.Recycler.DeliveryItemRecycler
import motobeans.com.todolistexample.Injection
import motobeans.com.todolistexample.R
import motobeans.com.todolistexample.databinding.ActivityAddUpdateTaskBinding
import motobeans.com.todolistexample.databinding.ActivityTaskBinding
import motobeans.com.todolistexample.persistence.Model.Task
import java.security.AccessController.getContext
import java.util.Random

class AddUpdateTaskActivity : AppCompatActivity() {

  companion object {

    val KEY_TASK_ID = "id"

    fun start(context: Context, id: String? = null){
      val bundle = Bundle()
      if(id != null) let {  bundle.putString(KEY_TASK_ID, id) }

      val intent = Intent(context, AddUpdateTaskActivity::class.java)
      intent.putExtras(bundle)
      context.startActivity(intent)
    }
  }

  private lateinit var binding: ActivityAddUpdateTaskBinding
  private lateinit var viewModelFactory: ViewModelFactory
  private lateinit var taskViewModel: TaskViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_add_update_task)

    setSupportActionBar(toolbar)
    title = "Add / Update Task"

    init()
  }

  private var task: Task? = null
  private val id: String? by lazy { intent?.extras?.getString(KEY_TASK_ID) }

  private fun init() {
    viewModelFactory = Injection.provideViewModelFactory(this)
    taskViewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskViewModel::class.java)

    if(id != null) let { taskViewModel.task(id.toString()).observe(this, Observer { task -> populateTask(task) }) }

    binding.buttonAddUpdatetask.setOnClickListener { _ -> saveData() }
  }

  private fun saveData() {
    val taskDesc = binding.etTaskDesc.text.toString()

    if(task == null) let { task = Task(taskDesc = taskDesc) }
    else {
      task?.taskDesc = taskDesc
      task?.isActive = binding.rbActive.isChecked
    }

    taskViewModel.save(task)
    finish()
  }

  private fun populateTask(task: Task?) {
    this.task = task
    binding.etTaskDesc.setText(task?.taskDesc)
    val isActive = task?.isActive ?: false
    binding.rbActive.isChecked = !isActive
    binding.rbDeActive.isChecked = isActive
  }
}
