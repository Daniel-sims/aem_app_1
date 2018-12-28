package com.management.engineering.alarm.alarmengineermanagement.utils.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.data.models.CustomerResponse
import com.management.engineering.alarm.alarmengineermanagement.utils.inflate
import kotlinx.android.synthetic.main.item_customer.view.*

class CustomersAdapter(
        private val customers: ArrayList<CustomerResponse>,
        var onCustomerClicked: ((CustomerResponse) -> Unit)? = null
) : RecyclerView.Adapter<CustomersAdapter.ModuleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomersAdapter.ModuleHolder {
        return ModuleHolder(parent.inflate(R.layout.item_customer, false))
    }

    override fun getItemCount() = customers.size

    override fun onBindViewHolder(holder: CustomersAdapter.ModuleHolder, position: Int) {
        holder.bindModule(customers[position])
    }

    fun updateData(data: List<CustomerResponse>) {
        customers.clear()
        customers.addAll(data)
        notifyDataSetChanged()
    }

    inner class ModuleHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var customer: CustomerResponse? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            onCustomerClicked?.invoke(customer!!)
        }

        fun bindModule(customer: CustomerResponse) {
            this.customer = customer

            view.text_customer_name.text = customer.name
        }
    }
}