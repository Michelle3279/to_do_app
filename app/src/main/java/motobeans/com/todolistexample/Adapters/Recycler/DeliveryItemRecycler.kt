package motobeans.com.floweraura_deliveryapp_kotlin.MVP.View.Adapters.Recycler

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import motobeans.com.floweraura_deliveryapp_kotlin.MVP.View.Adapters.Holders.DeliveryItemHolder
import motobeans.com.todolistexample.R
import motobeans.com.todolistexample.databinding.ItemTaskBinding
import motobeans.com.todolistexample.ui.TaskViewModel

/**
 * Created by munishkumarthakur on 09/11/17.
 */
class DeliveryItemRecycler(activity: AppCompatActivity, val taskViewModel: TaskViewModel) : RecyclerView.Adapter<DeliveryItemHolder>() {
  val context: Context = activity

  init {
    taskViewModel.taskList.observe(activity, Observer { notifyDataSetChanged() })
  }

  private fun getLayoutInflater(parent: ViewGroup?): LayoutInflater {
    return LayoutInflater.from(parent?.context)
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DeliveryItemHolder {
    val binding: ItemTaskBinding = DataBindingUtil.inflate(getLayoutInflater(parent), R.layout.item_task, parent, false)
    return DeliveryItemHolder(context = context, binding = binding, taskViewModel = taskViewModel)
  }

  override fun getItemCount(): Int {
    return taskViewModel.taskList?.value?.size ?: 0
  }

  override fun onBindViewHolder(holder: DeliveryItemHolder?, position: Int) {
    holder?.handleCard(position, taskViewModel.taskList.value!![position])
  }
}