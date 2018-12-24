package com.management.engineering.alarm.alarmengineermanagement.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.data.models.CompanyModuleResponse
import com.management.engineering.alarm.alarmengineermanagement.data.models.CompanyResponse
import com.management.engineering.alarm.alarmengineermanagement.data.models.Resource
import com.management.engineering.alarm.alarmengineermanagement.utils.*
import com.management.engineering.alarm.alarmengineermanagement.utils.adapters.CompanyModulesAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: CompanyModulesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.text_company_name.text = arguments?.get(ARG_COMPANY_NAME)?.toString()
        view.text_user_role.text = arguments?.get(ARG_USER_ROLE)?.toString()
        view.text_current_date.text = getCurrentDateTime().toString("dd/MM/yyyy HH:mm")

        if (arguments?.get(ARG_USER_FIRST_NAME)?.toString() != null && arguments?.get(ARG_USER_LAST_NAME)?.toString() != null) {
            view.text_user_full_name.text = String.format("%s %s", arguments?.get(ARG_USER_FIRST_NAME)?.toString(), arguments?.get(ARG_USER_LAST_NAME)?.toString())
        }

        initCompanyModules(view)

        return view
    }

    private fun initCompanyModules(view: View) {
        view.rv_company_modules.layoutManager = GridLayoutManager(context, 2)

        viewModel.getCompany().observe(this,
                Observer<Resource<CompanyResponse>> { resource ->
                    if (resource != null)
                        when (resource.status) {
                            Resource.Status.SUCCESS -> {
                                adapter = CompanyModulesAdapter(resource.data?.modules as ArrayList<CompanyModuleResponse>)
                                view.rv_company_modules.adapter = adapter

                                adapter.onModuleClicked = { companyModule ->
                                    navigateToModule(companyModule.moduleSlug, view)
                                }

                                view.layout_loading_modules.visibility = View.GONE

                                if (resource.data.modules.isEmpty()) {
                                    view.layout_no_modules.visibility = View.VISIBLE
                                } else {
                                    view.rv_company_modules.visibility = View.VISIBLE
                                }
                            }

                            Resource.Status.FAILED -> {
                                showErrorSnackbar(getString(R.string.failed_to_load_modules_error), view)
                            }

                            Resource.Status.ERROR -> {
                                showErrorSnackbar("Error: " + resource.exception?.exceptin?.message, view)
                            }
                        }
                })
    }

    private fun navigateToModule(moduleSlug: String, view: View) {
        when (moduleSlug) {
            CLIENTS_MODULE_SLUG -> {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_clientsFragment)
            }
            else -> {
                showErrorSnackbar(String.format("%s - Module not supported", moduleSlug), view)
            }
        }
    }

    private fun showErrorSnackbar(message: String, view: View) {
        Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_SHORT
        ).show()
    }
}