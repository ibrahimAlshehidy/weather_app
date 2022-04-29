package com.demo.weatherapp.features.home.presentation.ui.activities


import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.demo.weatherapp.R
import com.demo.weatherapp.databinding.ActivityMainBinding
import com.demo.weatherapp.features.home.data.models.CitiesData
import com.demo.weatherapp.features.home.data.models.CityWeatherDetailsData
import com.demo.weatherapp.features.home.presentation.ui.SearchCitiesAdapter
import com.demo.weatherapp.features.home.presentation.ui.viewModels.MainViewModel
import com.demo.weatherapp.utils.Const
import com.demo.weatherapp.utils.result.Resource
import com.demo.weatherapp.utils.result.ResourceType.*
import com.demo.weatherapp.utils.views.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), SearchCitiesAdapter.ActionsListener {

    private val mainViewModel: MainViewModel by viewModels()
    private val searchCitiesAdapter: SearchCitiesAdapter by lazy { SearchCitiesAdapter(this) }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun setUpViews() {
        setupCitiesRecyclerView()
        setClickListener()
    }

    private fun setupCitiesRecyclerView() {
        binding.rvCities.layoutManager = LinearLayoutManager(this)
        binding.rvCities.adapter = searchCitiesAdapter

    }

    private fun setClickListener() {
        binding.ivSearch.setOnClickListener { binding.rlHeader.visibility = View.VISIBLE }
        binding.ivClearSearch.setOnClickListener { clearSearchResults() }
        binding.ivGoBack.setOnClickListener { clearSearchView() }
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                startSearch()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.ivClearSearch.visibility = View.VISIBLE
            }
        })
    }

    private fun clearSearchResults() {
        binding.etSearch.text.clear()
        binding.ivClearSearch.visibility = View.GONE
        hideRecyclerWithData()
    }

    private fun clearSearchView() {
        clearSearchResults()
        binding.rlHeader.visibility = View.GONE

    }

    private fun startSearch() {
        mainViewModel.getSearchCities(binding.etSearch.text.toString())
            .observe(this, this::handleCitiesResult)
    }

    private fun handleCitiesResult(result: Resource<List<CitiesData>>) {
        when (result.resourceType) {
            SUCCESS -> {
                showRecyclerWithData()
                submitCityList(result.data)
            }
            ERROR -> {
                hideRecyclerWithData()
            }
            LOADING -> {
            }
        }
    }

    private fun showRecyclerWithData() {
        binding.rvCities.visibility = View.VISIBLE
        binding.rlBottomView.visibility = View.VISIBLE
    }

    private fun hideRecyclerWithData() {
        binding.rvCities.visibility = View.GONE
        binding.rlBottomView.visibility = View.GONE
    }

    private fun submitCityList(list: List<CitiesData>?) = searchCitiesAdapter.addAll(list)

    private fun getCityWeather(cityName: String?) {
        cityName?.let {
            mainViewModel.getCityWeatherForecast(it)
                .observe(this, this::handleWeatherForecastResult)
        }
    }

    private fun handleWeatherForecastResult(result: Resource<CityWeatherDetailsData>) {
        when (result.resourceType) {
            SUCCESS -> {
                setCityWeather(result.data)
            }
            ERROR -> {
                Toast.makeText(this, getString(R.string.somthingWentWrong), Toast.LENGTH_SHORT)
                    .show()
            }
            LOADING -> {
                binding.loadingBar.visibility = View.VISIBLE
            }
        }
    }

    private fun setCityWeather(response: CityWeatherDetailsData?) {
        binding.ivWindSpeed.setImageResource(R.drawable.wind)
        binding.ivHumidity.setImageResource(R.drawable.humidity)
        binding.tvTempDegreeType.text = getString(R.string.fahrenheit)
        binding.tvToday.text = getString(R.string.today)
        binding.tvTomorrow.text = getString(R.string.tomorrow)

        binding.loadingBar.visibility = View.GONE
        binding.tvCityName.text = response?.location?.name
        binding.tvDate.text = response?.location?.localtime

        Glide.with(binding.root)
            .load("https:" + response?.current?.condition?.icon)
            .into(binding.ivWeatherCondition)

        binding.tvTempDegree.text = response?.current?.temp_f.toString()

        binding.tvWeatherDescription.text = response?.current?.condition?.text
        ("${response?.current?.wind_mph}${Const.WIND_MEASURE_TYPE}").also {
            binding.tvWindSpeed.text = it
        }
        ("${response?.current?.humidity}${Const.PERCENTAGE}").also { binding.tvHumidity.text = it }

        Glide.with(binding.root)
            .load("https:" + response?.forecast?.forecastday?.get(0)?.day?.condition_day?.icon)
            .into(binding.ivTodayCondition)

        ("${response?.forecast?.forecastday?.get(0)?.day?.mintemp_f} / ${
            response?.forecast?.forecastday?.get(0)?.day?.maxtemp_f
        }${Const.TEMPERATURE_F}").also { binding.tvTodayTemp.text = it }

        Glide.with(binding.root)
            .load("https:" + response?.forecast?.forecastday?.get(1)?.day?.condition_day?.icon)
            .into(binding.ivTomorrowCondition)

        ("${response?.forecast?.forecastday?.get(1)?.day?.mintemp_f} / ${
            response?.forecast?.forecastday?.get(1)?.day?.maxtemp_f
        }${Const.TEMPERATURE_F}").also { binding.tvTomorrowTemp.text = it }

        Glide.with(binding.root)
            .load("https:" + response?.forecast?.forecastday?.get(2)?.day?.condition_day?.icon)
            .into(binding.ivDayAfterTomorrowCondition)

        ("${response?.forecast?.forecastday?.get(2)?.day?.mintemp_f} / ${
            response?.forecast?.forecastday?.get(2)?.day?.maxtemp_f
        }${Const.TEMPERATURE_F}").also { binding.tvDayAfterTomorrowTemp.text = it }
        getDayFromDate(response?.forecast?.forecastday?.get(2)?.date)

    }

    @SuppressLint("SimpleDateFormat")
    private fun getDayFromDate(date: String?) {
        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val dateTransform: Date? = inFormat.parse(date!!)
        val outFormat = SimpleDateFormat("EEEE")
        val dayName: String = outFormat.format(dateTransform!!)
        binding.tvDayAfterTomorrow.text = dayName
    }

    private fun dismissKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
    }

    override fun onCityClickClick(city: CitiesData?) {
        dismissKeyboard()
        clearSearchView()
        getCityWeather(city?.name)
    }

}