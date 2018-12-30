package com.management.engineering.alarm.alarmengineermanagement.features.clients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.management.engineering.alarm.alarmengineermanagement.R
import com.management.engineering.alarm.alarmengineermanagement.data.models.ClientResponse
import com.management.engineering.alarm.alarmengineermanagement.data.models.Resource
import com.management.engineering.alarm.alarmengineermanagement.utils.ARG_CLIENT
import com.management.engineering.alarm.alarmengineermanagement.utils.adapters.ClientsAdapter
import kotlinx.android.synthetic.main.fragment_clients.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ClientsFragment : Fragment() {

    private val viewModel: ClientsViewModel by viewModel()
    private lateinit var clientsAdapter: ClientsAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_clients, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar_clients.setNavigationOnClickListener { Navigation.findNavController(view).navigateUp() }

        initClients()
    }

    private fun initClients() {
        clientsAdapter = ClientsAdapter()
        clientsAdapter.onModuleClicked = { client ->
            view?.let { view ->
                Navigation.findNavController(view).navigate(R.id.action_clientsFragment_to_clientFragment,
                        bundleOf(ARG_CLIENT to client))
            }
        }

        rv_clients.adapter = clientsAdapter
        rv_clients.layoutManager = LinearLayoutManager(context)

        getClientsData()
    }

    private fun getClientsData() {
        viewModel.getClients().observe(this,
                Observer<Resource<List<ClientResponse>>> { resource ->
                    if (resource != null)
                        when (resource.status) {
                            Resource.Status.SUCCESS -> {
                                layout_loading_clients.visibility = View.GONE

                                resource.data?.let { data ->
                                    if (data.isEmpty()) {
                                        layout_no_clients.visibility = View.VISIBLE
                                    } else {
                                        rv_clients.visibility = View.VISIBLE

                                        clientsAdapter.updateData(data)
                                    }
                                }
                            }

                            Resource.Status.FAILED -> {
                                layout_loading_clients.visibility = View.GONE
                                layout_no_clients.visibility = View.VISIBLE

                                showErrorSnackbar(getString(R.string.failed_to_clients_error))
                            }

                            Resource.Status.ERROR -> {
                                layout_loading_clients.visibility = View.GONE
                                layout_no_clients.visibility = View.VISIBLE

                                showErrorSnackbar("Error: " + resource.exception?.exceptin?.message)
                            }
                        }
                })
    }

    private fun showErrorSnackbar(message: String) {
        view?.let { view ->
            Snackbar.make(
                    view,
                    message,
                    Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}