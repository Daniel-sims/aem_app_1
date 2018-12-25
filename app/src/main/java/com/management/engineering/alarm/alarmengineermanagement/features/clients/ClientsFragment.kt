package com.management.engineering.alarm.alarmengineermanagement.features.clients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.data.models.ClientResponse
import com.management.engineering.alarm.alarmengineermanagement.data.models.Resource
import com.management.engineering.alarm.alarmengineermanagement.utils.adapters.ClientsAdapter
import kotlinx.android.synthetic.main.fragment_clients.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientsFragment : Fragment() {

    private val viewModel: ClientsViewModel by viewModel()
    private lateinit var adapter: ClientsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_clients, container, false)
        view.toolbar_clients.setNavigationOnClickListener { Navigation.findNavController(view).navigateUp() }

        initClients(view)

        return view
    }

    private fun initClients(view: View) {
        view.rv_clients.layoutManager = LinearLayoutManager(context)

        viewModel.getClients().observe(this,
                Observer<Resource<List<ClientResponse>>> { resource ->
                    if (resource != null)
                        when (resource.status) {
                            Resource.Status.SUCCESS -> {
                                adapter = ClientsAdapter(resource.data as ArrayList<ClientResponse>)
                                view.rv_clients.adapter = adapter

                                adapter.onModuleClicked = { client ->

                                }

                                view.layout_loading_clients.visibility = View.GONE

                                if (resource.data.isEmpty()) {
                                    view.layout_no_clients.visibility = View.VISIBLE
                                } else {
                                    view.rv_clients.visibility = View.VISIBLE
                                }
                            }

                            Resource.Status.FAILED -> {
                                showErrorSnackbar(getString(R.string.failed_to_clients_error), view)
                            }

                            Resource.Status.ERROR -> {
                                showErrorSnackbar("Error: " + resource.exception?.exceptin?.message, view)
                            }
                        }
                })
    }

    private fun showErrorSnackbar(message: String, view: View) {
        Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_SHORT
        ).show()
    }
}