package com.iset.words

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iset.words.adapter.LetterAdapter
import com.iset.words.databinding.ActivityMainBinding
import com.iset.words.databinding.FragmentLetterListBinding

class LetterListFragment : Fragment() {

        private var _binding: FragmentLetterListBinding? = null
        private val binding get() = _binding!!
        private lateinit var recyclerView: RecyclerView
        private var isLinearLayoutManager = true

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setHasOptionsMenu(true)
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Retrieve and inflate the layout for this fragment
            _binding = FragmentLetterListBinding.inflate(inflater, container, false)
            val view = binding.root
            return view
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            recyclerView = binding.recyclerView
            // Sets the LayoutManager of the recyclerview
            // On the first run of the app, it will be LinearLayoutManager
            chooseLayout()
        }

        /**
         * Frees the binding object when the Fragment is destroyed.
         */
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            inflater.inflate(R.menu.layout_menu, menu)

            val layoutButton = menu.findItem(R.id.action_switch_layout)
            setIcon(layoutButton)
        }

        private fun chooseLayout() {
            if (isLinearLayoutManager) {
                recyclerView.layoutManager = LinearLayoutManager(context)
            } else {
                recyclerView.layoutManager = GridLayoutManager(context, 4)
            }
            recyclerView.adapter = LetterAdapter()
        }

        private fun setIcon(menuItem: MenuItem?) {
            if (menuItem == null)
                return

            menuItem.icon =
                if (isLinearLayoutManager)
                    ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
                else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.action_switch_layout -> {
                    // Sets isLinearLayoutManager (a Boolean) to the opposite value
                    isLinearLayoutManager = !isLinearLayoutManager
                    // Sets layout and icon
                    chooseLayout()
                    setIcon(item)

                    return true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
    }