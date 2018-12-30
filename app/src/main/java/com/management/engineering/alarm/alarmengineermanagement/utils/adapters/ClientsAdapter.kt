package com.management.engineering.alarm.alarmengineermanagement.utils.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.data.models.ClientResponse
import com.management.engineering.alarm.alarmengineermanagement.utils.inflate
import kotlinx.android.synthetic.main.item_client.view.*

class ClientsAdapter(
        val clients: ArrayList<ClientResponse> = ArrayList(),
        var onModuleClicked: ((ClientResponse) -> Unit)? = null
) : RecyclerView.Adapter<ClientsAdapter.ModuleHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsAdapter.ModuleHolder {
        return ModuleHolder(parent.inflate(R.layout.item_client, false))
    }

    override fun getItemCount() = clients.size

    override fun onBindViewHolder(holder: ClientsAdapter.ModuleHolder, position: Int) {
        holder.bindModule(clients[position])
    }

    fun updateData(data: List<ClientResponse>) {
        clients.clear()
        clients.addAll(data)
        notifyDataSetChanged()
    }

    inner class ModuleHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var client: ClientResponse? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            client?.let { client -> onModuleClicked?.invoke(client)}
        }

        fun bindModule(client: ClientResponse) {
            this.client = client

            view.text_client_name.text = client.name
            
            view.text_customer_count.text = client.customers.size.toString()

            view.text_active_job_count.text = "0"

            view.text_total_job_count.text = "0"

        }
    }
}