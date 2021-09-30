package com.gekaradchenko.moviescheck.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gekaradchenko.moviescheck.R
import com.gekaradchenko.moviescheck.databinding.FragmentMoviesBinding
import com.gekaradchenko.moviescheck.network.MULT
import com.gekaradchenko.moviescheck.network.PREMIERE
import com.gekaradchenko.moviescheck.network.SERIES
import com.gekaradchenko.moviescheck.ui.movies.adapter.MoviesAdapter
import com.gekaradchenko.moviescheck.ui.movies.viewmodel.MoviesFragmentViewModel
import com.gekaradchenko.moviescheck.ui.movies.viewmodel.MoviesFragmentViewModelFactory


class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MoviesFragmentViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onResume() {
        super.onResume()

        // languages
        val languages = resources.getStringArray(R.array.languages)
        val languagesArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, languages)
        binding.languageAutoCompleteTextView.setAdapter(languagesArrayAdapter)
        binding.languageAutoCompleteTextView.setText(
            languagesArrayAdapter.getItem(0).toString(),
            false
        )

        //types
        val types = resources.getStringArray(R.array.type)
        val typesArrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, types)
        binding.typeAutoCompleteTextView.setAdapter(typesArrayAdapter)
        binding.typeAutoCompleteTextView.setText(typesArrayAdapter.getItem(0).toString(), false)

        // filters
        val filter = resources.getStringArray(R.array.filter)
        val filtersArrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, filter)
        binding.filtersAutoCompleteTextView.setAdapter(filtersArrayAdapter)
        binding.filtersAutoCompleteTextView.setText(
            filtersArrayAdapter.getItem(0).toString(),
            false
        )

        //genre films
        val genreFilms = resources.getStringArray(R.array.genre_films)
        val genreFilmsArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, genreFilms)
        binding.genresAutoCompleteTextView.setAdapter(genreFilmsArrayAdapter)
        binding.genresAutoCompleteTextView.setText(
            genreFilmsArrayAdapter.getItem(0).toString(),
            false
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        val factory = MoviesFragmentViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MoviesFragmentViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.languageAutoCompleteTextView.setDropDownBackgroundDrawable(resources.getDrawable(R.color.card_background_color))
        binding.typeAutoCompleteTextView.setDropDownBackgroundDrawable(resources.getDrawable(R.color.card_background_color))
        binding.filtersAutoCompleteTextView.setDropDownBackgroundDrawable(resources.getDrawable(R.color.card_background_color))
        binding.genresAutoCompleteTextView.setDropDownBackgroundDrawable(resources.getDrawable(R.color.card_background_color))

        //movie item click
        moviesAdapter = MoviesAdapter {

        }
        binding.moviesRecyclerView.apply {
            adapter = moviesAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.moviesList.observe(viewLifecycleOwner, {
            moviesAdapter.submitList(it)
        })

        viewModel.baseUrl.observe(viewLifecycleOwner, {
            viewModel.getMoviesGrid()
        })




        viewModel.typeUrl.observe(viewLifecycleOwner, {

            // genre reSet after change type
            when (it) {
                SERIES -> {
                    val genreSeries = resources.getStringArray(R.array.genre_series)
                    val genreFilmsArrayAdapter =
                        ArrayAdapter(requireContext(), R.layout.dropdown_item, genreSeries)
                    binding.genresAutoCompleteTextView.setAdapter(genreFilmsArrayAdapter)
                    binding.genresAutoCompleteTextView.setText(
                        genreFilmsArrayAdapter.getItem(0).toString(), false
                    )
                }
                MULT -> {
                    val genreCartoons = resources.getStringArray(R.array.genre_cartoons)
                    val genreCartoonsArrayAdapter =
                        ArrayAdapter(requireContext(), R.layout.dropdown_item, genreCartoons)
                    binding.genresAutoCompleteTextView.setAdapter(genreCartoonsArrayAdapter)
                    binding.genresAutoCompleteTextView.setText(
                        genreCartoonsArrayAdapter.getItem(0).toString(), false
                    )
                }
                PREMIERE -> {
                    val genrePremieres = resources.getStringArray(R.array.genre_premieres)
                    val genrePremieresArrayAdapter =
                        ArrayAdapter(requireContext(), R.layout.dropdown_item, genrePremieres)
                    binding.genresAutoCompleteTextView.setAdapter(genrePremieresArrayAdapter)
                    binding.genresAutoCompleteTextView.setText(
                        genrePremieresArrayAdapter.getItem(0).toString(), false
                    )


                }
                else -> {
                    val genreFilms = resources.getStringArray(R.array.genre_films)
                    val genreFilmsArrayAdapter =
                        ArrayAdapter(requireContext(), R.layout.dropdown_item, genreFilms)
                    binding.genresAutoCompleteTextView.setAdapter(genreFilmsArrayAdapter)
                    binding.genresAutoCompleteTextView.setText(
                        genreFilmsArrayAdapter.getItem(0).toString(), false
                    )
                }
            }

            // filter reSet after change type
            when (it) {
                PREMIERE -> {
                    val filter = resources.getStringArray(R.array.filter_premieres)
                    val filtersArrayAdapter =
                        ArrayAdapter(requireContext(), R.layout.dropdown_item, filter)
                    binding.filtersAutoCompleteTextView.setAdapter(filtersArrayAdapter)
                    binding.filtersAutoCompleteTextView.setText(
                        filtersArrayAdapter.getItem(0).toString(), false
                    )
                }
                else -> {
                    val filter = resources.getStringArray(R.array.filter)
                    val filtersArrayAdapter =
                        ArrayAdapter(requireContext(), R.layout.dropdown_item, filter)
                    binding.filtersAutoCompleteTextView.setAdapter(filtersArrayAdapter)
                    binding.filtersAutoCompleteTextView.setText(
                        filtersArrayAdapter.getItem(0).toString(), false
                    )
                }
            }
        })

        binding.languageAutoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, id ->
                viewModel.setLanguage(id.toInt())
                viewModel.language.value = parent.getItemAtPosition(position).toString()
                viewModel.setBaseUrl()
            }
        binding.typeAutoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, id ->
                viewModel.setType(id.toInt())
                viewModel.type.value = parent.getItemAtPosition(position).toString()
                viewModel.setBaseUrl()
            }
        binding.filtersAutoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, id ->
                viewModel.setFilter(id.toInt())
                viewModel.filter.value = parent.getItemAtPosition(position).toString()
                viewModel.setBaseUrl()
            }
        binding.genresAutoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, id ->
                viewModel.setGenre(id.toInt())
                viewModel.genre.value = parent.getItemAtPosition(position).toString()
                viewModel.setBaseUrl()
            }

        binding.moviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!recyclerView.canScrollVertically(1)){
                    println("!!! !!! !!! !!! !!! !!! !!! !!!")
                }
            }
        })


        return binding.root
    }


}