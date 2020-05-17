package com.team1.covid19info.ui.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import com.team1.covid19info.R
import com.team1.covid19info.ui.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.component_covid_data.*
import kotlinx.android.synthetic.main.component_heatmap.*
import android.R.string.no
import android.location.Geocoder
import android.view.KeyEvent
import android.widget.EditText
import android.widget.PopupMenu
import androidx.core.widget.addTextChangedListener
import com.anychart.APIlib
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import com.team1.covid19info.model.StatisticsCountry
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import kotlinx.android.synthetic.main.component_piechart.*


class StatisticsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var mapView: MapView
    private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    private val heatMapData = MutableLiveData<List<WeightedLatLng>>()
    private var pieChart: Pie? = null

    companion object {
        fun newInstance() = StatisticsFragment()
    }

    private lateinit var viewModel: StatisticsViewModel
    private var selectedCountry = MutableLiveData<StatisticsCountry>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initViews()
        val mapViewBundle = savedInstanceState?.getBundle(MAPVIEW_BUNDLE_KEY)
        mapView = requireView().findViewById(R.id.mvHeatmap)
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)
    }

    private fun initViewModel() {
        viewModel = StatisticsViewModel(requireContext())
        viewModel.globalStatistics.observe(viewLifecycleOwner, Observer {
            updateViews()
        })
        viewModel.countryStatistics.observe(viewLifecycleOwner, Observer {
            val scope = CoroutineScope(Dispatchers.Default)

            scope.launch {
                val data = ArrayList<WeightedLatLng>()
                for (country in it) {
                    val geoCoder = Geocoder(context, Locale.getDefault())
                    val geoDataList = geoCoder.getFromLocationName(country.country, 1)
                    if (geoDataList.isNotEmpty())
                    {
                        val geoData = geoDataList[0]
                        data.add(WeightedLatLng(LatLng(geoData.latitude, geoData.longitude), country.totalConfirmed.toDouble()))
                    }
                }
                heatMapData.postValue(data)
            }
        })
        viewModel.getCovidData()
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
        heatMapData.observe(viewLifecycleOwner, Observer {
            val heatMapProvider = HeatmapTileProvider.Builder()
                .weightedData(it) // load our weighted data
                .radius(20) // optional, in pixels, can be anything between 20 and 50
                .maxIntensity(1000.0) // set the maximum intensity
                .build()

            googleMap.addTileOverlay(TileOverlayOptions().tileProvider(heatMapProvider))
            clLoadingHeatmap.visibility = View.INVISIBLE
        })
    }

    private fun initViews() {
        var popupMenu: PopupMenu
        etSearchCountry.addTextChangedListener {text ->
            if (viewModel.countryStatistics.value == null) {
                // niks
            }
            else if (etSearchCountry.text.isEmpty()) {
                selectedCountry.postValue(null)
            }
            else if (etSearchCountry.text.toString() == selectedCountry.value?.translatedCountry) {
                // niks
            }
            else {
                val filteredCountries = viewModel.countryStatistics.value!!.stream()
                    .filter {
                        it.translatedCountry.toLowerCase(Locale.getDefault()).contains(text.toString().toLowerCase(Locale.getDefault()))
                    }.toArray()

                if (filteredCountries.isNotEmpty() && filteredCountries.size <= 3) {
                    println(filteredCountries)
                    popupMenu = PopupMenu(context, etSearchCountry)
                    for (filteredCountry in filteredCountries) {
                        popupMenu.menu.add((filteredCountry as StatisticsCountry).translatedCountry)
                    }
                    popupMenu.setOnMenuItemClickListener { menuItem ->
                        val clickedCountry = filteredCountries.find {
                            (it as StatisticsCountry).translatedCountry == menuItem.title
                        } as StatisticsCountry
                        selectedCountry.postValue(clickedCountry)
                        true
                    }
                    popupMenu.show()
                }
            }
            btClearText.setOnClickListener {
                etSearchCountry.setText("")
            }
        }
        selectedCountry.observe(viewLifecycleOwner, Observer {
            updateTitle()
            updateViews()
        })
        selectedCountry.postValue(null)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        var mapViewBundle = outState.getBundle(getString(R.string.google_maps_key))
        if (mapViewBundle == null)
        {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }
        mapView.onSaveInstanceState(mapViewBundle)
    }

    private fun updateTitle()
    {
        (requireActivity() as MainActivity).setToolbarTitle("${getString(R.string.Bottom_Nav_Statistics)} ${getTitleAppendix()}")
    }

    private fun getTitleAppendix() : String
    {
        return if (selectedCountry.value != null) selectedCountry.value!!.translatedCountry else getString(R.string.DefaultStatisticsTitleAppendix)
    }

    private fun updateViews() {
        if (selectedCountry.value == null) {
            coHeatmap.visibility = View.VISIBLE
            coPiechart.visibility = View.GONE
            tvNewConfirmed.text = viewModel.globalStatistics.value?.newConfirmed.toString()
            tvTotalConfirmed.text = viewModel.globalStatistics.value?.totalConfirmed.toString()
            tvNewDeaths.text = viewModel.globalStatistics.value?.newDeaths.toString()
            tvTotalDeaths.text = viewModel.globalStatistics.value?.totalDeaths.toString()
            tvNewRecovered.text = viewModel.globalStatistics.value?.newRecovered.toString()
            tvTotalRecovered.text = viewModel.globalStatistics.value?.totalRecovered.toString()
        } else {
            coHeatmap.visibility = View.GONE
            coPiechart.visibility = View.VISIBLE
            tvNewConfirmed.text = selectedCountry.value!!.newConfirmed.toString()
            tvTotalConfirmed.text = selectedCountry.value!!.totalConfirmed.toString()
            tvNewDeaths.text = selectedCountry.value!!.newDeaths.toString()
            tvTotalDeaths.text = selectedCountry.value!!.totalDeaths.toString()
            tvNewRecovered.text = selectedCountry.value!!.newRecovered.toString()
            tvTotalRecovered.text = selectedCountry.value!!.totalRecovered.toString()
            etSearchCountry.setText(selectedCountry.value!!.translatedCountry)
            cvChart.setZoomEnabled(true)
            val chartEntries = arrayListOf<DataEntry>()
            APIlib.getInstance().setActiveAnyChartView(cvChart)
            chartEntries.add(ValueDataEntry(getString(R.string.TotalConfirmed), selectedCountry.value!!.totalConfirmed))
            chartEntries.add(ValueDataEntry(getString(R.string.TotalDeaths), selectedCountry.value!!.totalDeaths))
            chartEntries.add(ValueDataEntry(getString(R.string.TotalRecoveries), selectedCountry.value!!.totalRecovered))
            if (pieChart == null) {
                pieChart = AnyChart.pie()
                cvChart.setChart(pieChart)
            }
            pieChart!!.data(chartEntries)
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map!!
        MapsInitializer.initialize(activity)
        map.uiSettings.isZoomControlsEnabled = false
        map.uiSettings.isZoomGesturesEnabled = false
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onStart() {
        mapView.onStart()
        super.onStart()
    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onStop() {
        mapView.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }
}
