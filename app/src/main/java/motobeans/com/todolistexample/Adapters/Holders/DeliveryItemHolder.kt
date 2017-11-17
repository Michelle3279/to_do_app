package motobeans.com.floweraura_deliveryapp_kotlin.MVP.View.Adapters.Holders

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import motobeans.com.todolistexample.databinding.ItemTaskBinding
import motobeans.com.todolistexample.persistence.Model.Task
import motobeans.com.todolistexample.ui.AddUpdateTaskActivity
import motobeans.com.todolistexample.ui.TaskViewModel

/**
 * Created by munishkumarthakur on 09/11/17.
 */
class DeliveryItemHolder(val context: Context,
    val binding: ItemTaskBinding, val taskViewModel: TaskViewModel) : RecyclerView.ViewHolder(binding.root) {

  fun handleCard(position: Int, itemData: Task) {
    binding.tvTaskDesc.text = itemData.taskDesc

    if(itemData.isActive) {
      binding.tvStatus.text = "De-Activate"
      binding.tvStatus.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_light))
    } else {
      binding.tvStatus.text = "Activate"
      binding.tvStatus.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
    }

    binding.tvStatus.setOnClickListener { _ ->
      itemData.isActive = !itemData.isActive
      taskViewModel.save(task = itemData)
    }
    binding.tvDelete.setOnClickListener { _ -> taskViewModel.deleteTaskById(id = itemData.id)}
    binding.root.setOnClickListener { _ -> AddUpdateTaskActivity.start(context, itemData.id) }
  }
}