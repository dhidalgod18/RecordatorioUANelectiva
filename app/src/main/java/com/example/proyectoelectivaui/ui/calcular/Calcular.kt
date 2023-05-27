package com.example.proyectoelectivaui.ui.calcular

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoelectivaui.adapter.servicioAdapter
import com.example.proyectoelectivaui.databinding.FragmentCalcularBinding
import com.example.proyectoelectivaui.entities.servicioEntity
import com.example.proyectoelectivaui.events.ServicioAddedEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class Calcular : Fragment() {
    private var _binding : FragmentCalcularBinding? = null
    private val binding get() = _binding!!
    private lateinit var miViewModel: CalcularRegistrarViewModel
    private var userId: Int = 0

    //changes recyclerView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: servicioAdapter
    //end of changes recyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalcularBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.listaServicios
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = servicioAdapter()
        recyclerView.adapter = adapter

        userId = requireActivity().intent.getIntExtra("id", 0)
        Log.d("Calcular.kt", "info sent to CalculatRegistrarViewModel for userId: $userId")
        miViewModel = ViewModelProvider(this).get(CalcularRegistrarViewModel::class.java)
        miViewModel.setUserId(userId)

        val observer = Observer<List<servicioEntity>> { listaServicios ->
            adapter.submitList(listaServicios)
        }

        miViewModel.listaServicios.observe(viewLifecycleOwner, observer)

        EventBus.getDefault().register(this)

        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(requireContext(), recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val servicio = adapter.getItemAtPosition(position)
                        Toast.makeText(context, servicio.nombreServicio, Toast.LENGTH_SHORT).show()
                    }
                })
        )

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        EventBus.getDefault().unregister(this)
    }

    override fun onResume() {
        super.onResume()
        // Refresh the list when returning to this fragment
        miViewModel.refreshListaServicios()
    }

    @Subscribe
    fun onServicioAdded(event: ServicioAddedEvent) {

        adapter.notifyDataSetChanged()
    }

    private class RecyclerItemClickListener(
        context: Context,
        recyclerView: RecyclerView,
        private val listener: OnItemClickListener
    ) : RecyclerView.SimpleOnItemTouchListener() {
        private val gestureDetector: GestureDetector =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }
            })

        interface OnItemClickListener {
            fun onItemClick(view: View, position: Int)
        }

        init {
            recyclerView.addOnItemTouchListener(this)
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            val childView = rv.findChildViewUnder(e.x, e.y)
            if (childView != null && gestureDetector.onTouchEvent(e)) {
                listener.onItemClick(childView, rv.getChildAdapterPosition(childView))
                return true
            }
            return false
        }
    }

}