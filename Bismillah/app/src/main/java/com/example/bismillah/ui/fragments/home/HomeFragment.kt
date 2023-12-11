package com.example.bismillah.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bismillah.R
import com.example.bismillah.adapters.RecipesAdapter
import com.example.bismillah.databinding.FragmentHomeBinding
import com.example.bismillah.databinding.FragmentRecipesBinding
import com.example.bismillah.ui.fragments.recipes.RecipesFragmentArgs
import com.example.bismillah.util.NetworkListener
import com.example.bismillah.util.NetworkResult
import com.example.bismillah.util.observeOnce
import com.example.bismillah.viewmodels.MainViewModel
import com.example.bismillah.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch


//@ExperimentalCoroutinesApi
//@AndroidEntryPoint
//class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
//
//    private var dataRequested = false
//    private val args by navArgs<RecipesFragmentArgs>()
//
//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!
//
//    private lateinit var mainViewModel: MainViewModel
//    private lateinit var recipesViewModel: RecipesViewModel
//    private val mAdapter by lazy { RecipesAdapter() }
//
//    private lateinit var networkListener: NetworkListener
//
//    override fun onResume() {
//        super.onResume()
//        if (mainViewModel.recyclerViewState != null) {
//            binding.rekomendasiRecyclerView.layoutManager?.onRestoreInstanceState(mainViewModel.recyclerViewState)
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
//        recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Inflate the layout for this fragment
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        binding.lifecycleOwner = this
//        binding.mainViewModel = mainViewModel
//
//        val menuHost: MenuHost = requireActivity()
//        menuHost.addMenuProvider(object : MenuProvider {
//            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//                menuInflater.inflate(R.menu.recipes_menu, menu)
//
//                val search = menu.findItem(R.id.menu_search)
//                val searchView = search.actionView as? SearchView
//                searchView?.isSubmitButtonEnabled = true
//                searchView?.setOnQueryTextListener(this@HomeFragment)
//            }
//
//            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//                return true
//            }
//        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
//
//        setupRecyclerView()
//
//        recipesViewModel.readBackOnline.observe(viewLifecycleOwner) {
//            recipesViewModel.backOnline = it
//        }
//
//        lifecycleScope.launch {
//            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
//                networkListener = NetworkListener()
//                networkListener.checkNetworkAvailability(requireContext())
//                    .collect { status ->
//                        Log.d("NetworkListener", status.toString())
//                        recipesViewModel.networkStatus = status
//                        recipesViewModel.showNetworkStatus()
//                        readDatabase()
//                    }
//            }
//        }
//
//        return binding.root
//    }
//
//    private fun setupRecyclerView() {
//        binding.rekomendasiRecyclerView.adapter = mAdapter
//        binding.rekomendasiRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        showShimmerEffect()
//    }
//
//    override fun onQueryTextSubmit(query: String?): Boolean {
//        if (query != null) {
//            searchApiData(query)
//        }
//        return true
//    }
//
//    override fun onQueryTextChange(p0: String?): Boolean {
//        return true
//    }
//
//    private fun readDatabase() {
//        lifecycleScope.launch {
//            mainViewModel.readRecipes.observeOnce(viewLifecycleOwner) { database ->
//                if (database.isNotEmpty() && !args.backFromBottomSheet
//                    || database.isNotEmpty() && dataRequested
//                ) {
//                    Log.d("RecipesFragment", "readDatabase called!")
//                    mAdapter.setData(database.first().foodRecipe)
//                    hideShimmerEffect()
//                } else {
//                    Log.d("RecipesFragment", "requestApiData called!")
//                    if (!dataRequested) {
//                        requestApiData()
//                        dataRequested = true
//                    }
//                }
//            }
//        }
//    }
//
//    private fun requestApiData() {
//        Log.d("RecipesFragment", "requestApiData called!")
//        mainViewModel.getRecipes(recipesViewModel.applyQueries())
//        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
//            when (response) {
//                is NetworkResult.Success -> {
//                    hideShimmerEffect()
//                    response.data?.let { mAdapter.setData(it) }
//                    recipesViewModel.saveMealAndDietType()
//                }
//
//                is NetworkResult.Error -> {
//                    hideShimmerEffect()
//                    loadDataFromCache()
//                    Toast.makeText(
//                        requireContext(),
//                        response.message.toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//                is NetworkResult.Loading -> {
//                    showShimmerEffect()
//                }
//            }
//        }
//    }
//
//    private fun searchApiData(searchQuery: String) {
//        showShimmerEffect()
//        mainViewModel.searchRecipes(recipesViewModel.applySearchQuery(searchQuery))
//        mainViewModel.searchedRecipesResponse.observe(viewLifecycleOwner) { response ->
//            when (response) {
//                is NetworkResult.Success -> {
//                    hideShimmerEffect()
//                    val foodRecipe = response.data
//                    foodRecipe?.let { mAdapter.setData(it) }
//                }
//
//                is NetworkResult.Error -> {
//                    hideShimmerEffect()
//                    loadDataFromCache()
//                    Toast.makeText(
//                        requireContext(),
//                        response.message.toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//                is NetworkResult.Loading -> {
//                    showShimmerEffect()
//                }
//            }
//        }
//    }
//
//    private fun loadDataFromCache() {
//        mainViewModel.readRecipes.observe(viewLifecycleOwner) { database ->
//            if (database.isNotEmpty()) {
//                mAdapter.setData(database.first().foodRecipe)
//            }
//        }
//    }
//
//    private fun showShimmerEffect() {
//        binding.shimmerFrameLayout.startShimmer()
//        binding.shimmerFrameLayout.visibility = View.VISIBLE
//        binding.rekomendasiRecyclerView.visibility = View.GONE
//    }
//
//    private fun hideShimmerEffect() {
//        binding.shimmerFrameLayout.stopShimmer()
//        binding.shimmerFrameLayout.visibility = View.GONE
//        binding.shimmerFrameLayout.visibility = View.VISIBLE
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        mainViewModel.recyclerViewState =
//            binding.rekomendasiRecyclerView.layoutManager?.onSaveInstanceState()
//        _binding = null
//    }
//
//
//}

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}

