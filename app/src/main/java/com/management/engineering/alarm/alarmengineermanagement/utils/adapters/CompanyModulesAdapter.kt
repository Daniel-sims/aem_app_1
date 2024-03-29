package com.management.engineering.alarm.alarmengineermanagement.utils.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.data.models.CompanyModuleResponse
import com.management.engineering.alarm.alarmengineermanagement.utils.GlideApp
import com.management.engineering.alarm.alarmengineermanagement.utils.inflate
import kotlinx.android.synthetic.main.item_company_module.view.*

class CompanyModulesAdapter(
        val companyModuleAdapters: ArrayList<CompanyModuleResponse> = ArrayList(),
        var onModuleClicked: ((CompanyModuleResponse) -> Unit)? = null
) : RecyclerView.Adapter<CompanyModulesAdapter.ModuleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyModulesAdapter.ModuleHolder {
        return ModuleHolder(parent.inflate(R.layout.item_company_module, false))
    }

    override fun getItemCount() = companyModuleAdapters.size

    override fun onBindViewHolder(holder: CompanyModulesAdapter.ModuleHolder, position: Int) {
        holder.bindModule(companyModuleAdapters[position])
    }

    fun updateData(data: List<CompanyModuleResponse>) {
        companyModuleAdapters.clear()
        companyModuleAdapters.addAll(data)
        notifyDataSetChanged()
    }

    inner class ModuleHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var module: CompanyModuleResponse? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            module?.let { module -> onModuleClicked?.invoke(module) }
        }

        fun bindModule(module: CompanyModuleResponse) {
            this.module = module

            view.text_module_title.text = module.name

            GlideApp.with(view.context)
                    .load(module.imageUrl)
                    .into(view.image_module_image)
        }
    }
}