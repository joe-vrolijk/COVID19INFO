package com.team1.covid19info.ui.statistics

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import com.team1.covid19info.R
import com.team1.covid19info.ui.MainActivity
import kotlinx.android.synthetic.main.component_covid_data.*
import kotlinx.android.synthetic.main.component_heatmap.*
import android.location.Geocoder
import android.os.Build
import android.os.Handler
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import com.aachartmodel.aainfographics.AAInfographicsLib.AAChartCreator.AAChartModel
import com.aachartmodel.aainfographics.AAInfographicsLib.AAChartCreator.AAChartType
import com.aachartmodel.aainfographics.AAInfographicsLib.AAChartCreator.AAChartView
import com.aachartmodel.aainfographics.AAInfographicsLib.AAChartCreator.AASeriesElement
import com.aachartmodel.aainfographics.AAInfographicsLib.AAOptionsModel.AADataLabels
import com.anychart.APIlib
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import com.team1.covid19info.model.StatisticsCountry
import kotlinx.android.synthetic.main.fragment_statistics.*
import java.util.*
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.google.android.gms.maps.model.TileOverlay
import com.team1.covid19info.config.Constants.Constants.heatmapCoordinates
import com.team1.covid19info.model.StatisticsCountryDateData
import ir.farshid_roohi.linegraph.ChartEntity
import kotlinx.android.synthetic.main.component_piechart.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter


class StatisticsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var mapView: MapView
    private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    private var pieChart: Pie? = null

    private val heatMapWeightedList = arrayListOf<WeightedLatLng>()
    private var heatMapTileProvider: HeatmapTileProvider? = null
    private lateinit var overlay: TileOverlay

    private lateinit var viewModel: StatisticsViewModel
    private var selectedCountry = MutableLiveData<StatisticsCountry>()
    private lateinit var handler: Handler
    private var thread: Thread? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        handler = Handler(requireContext().mainLooper)
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
                val geoCoder = Geocoder(context, Locale.getDefault())
                for ((i, country) in it.withIndex()) {
                    if (heatmapCoordinates.containsKey(country.country)) {
                        heatMapWeightedList.add(
                            WeightedLatLng(
                                heatmapCoordinates[country.country],
                                country.totalConfirmed.toDouble()
                            )
                        )
                    } else {
                        val geoDataList = geoCoder.getFromLocationName(country.country, 1)
                        if (geoDataList.isNotEmpty())
                        {
                            val geoData = geoDataList[0]
                            heatMapWeightedList.add(WeightedLatLng(LatLng(geoData.latitude, geoData.longitude), country.totalConfirmed.toDouble()))
                        }
                    }
                    if (heatMapTileProvider == null) {
                        initHeatMap()
                    }
                    else if (Thread.currentThread().isInterrupted) {
                        break
                    }
                }

                if (!Thread.currentThread().isInterrupted) {
                    updateHeatMap()
                    clLoadingHeatmap.visibility = View.INVISIBLE
                }
        })
        viewModel.getCovidData()
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initHeatMap() {
//        handler.post {
            heatMapTileProvider = HeatmapTileProvider.Builder()
                .weightedData(heatMapWeightedList) // load our weighted data
                .radius(20) // optional, in pixels, can be anything between 20 and 50
                .maxIntensity(1000.0) // set the maximum intensity
                .build()

            overlay = googleMap.addTileOverlay(TileOverlayOptions().tileProvider(heatMapTileProvider))
//        }
    }

    private fun updateHeatMap() {
//        handler.post {
            heatMapTileProvider!!.setWeightedData(heatMapWeightedList)
            overlay.clearTileCache()
//        }
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
            if (it != null)
                viewModel.getCountryData(it.country)
        })
        viewModel.countryDateDataStatistics.observe(viewLifecycleOwner, Observer {
            updateLineChart(it)
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

    private fun updateLineChart(data: List<StatisticsCountryDateData>) {
        val pattern = "dd/MM/yyyy"
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        val activeGraphData = arrayListOf<Double>()
        val confirmedGraphData = arrayListOf<Double>()
        val deathsGraphData = arrayListOf<Double>()
        val recoveredGraphData = arrayListOf<Double>()
        val legend = arrayListOf<String>()
        for (day in data) {
            activeGraphData.add(day.active.toDouble())
            confirmedGraphData.add(day.confirmed.toDouble())
            deathsGraphData.add(day.deaths.toDouble())
            recoveredGraphData.add(day.recovered.toDouble())
            legend.add(dateFormat.format(day.date))
        }
        val aaChartView =  requireView().findViewById<AAChartView>(R.id.lcCountryData)

        val aaChartModel = AAChartModel()
            .chartType(AAChartType.Line)
            .title("Verloop")
            .subtitle("")
            .backgroundColor("#FFFFFFFF")
            .dataLabelsEnabled(true)
            .yAxisGridLineWidth(0f)
            .xAxisLabelsEnabled(true)
            .series(arrayOf(
                AASeriesElement()
                    .name("Actief")
                    .data(activeGraphData.toTypedArray()),
                AASeriesElement()
                    .name("Bevestigd")
                    .data(confirmedGraphData.toTypedArray()),
                AASeriesElement()
                    .name("Overleden")
                    .data(deathsGraphData.toTypedArray()),
                AASeriesElement()
                    .name("Genezen")
                    .data(recoveredGraphData.toTypedArray())
            ))
            .categories(legend.toTypedArray())
        aaChartView.aa_drawChartWithChartModel(aaChartModel)
    }

    private fun updateViews() {
        if (selectedCountry.value == null) {
            coHeatmap.visibility = View.VISIBLE
            coPiechart.visibility = View.GONE
            lcCountryData.visibility = View.GONE
            tvNewConfirmed.text = viewModel.globalStatistics.value?.newConfirmed.toString()
            tvTotalConfirmed.text = viewModel.globalStatistics.value?.totalConfirmed.toString()
            tvNewDeaths.text = viewModel.globalStatistics.value?.newDeaths.toString()
            tvTotalDeaths.text = viewModel.globalStatistics.value?.totalDeaths.toString()
            tvNewRecovered.text = viewModel.globalStatistics.value?.newRecovered.toString()
            tvTotalRecovered.text = viewModel.globalStatistics.value?.totalRecovered.toString()
        } else {
            coHeatmap.visibility = View.GONE
            coPiechart.visibility = View.VISIBLE
            lcCountryData.visibility = View.VISIBLE
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
//        map.uiSettings.isZoomControlsEnabled = false
//        map.uiSettings.isZoomGesturesEnabled = false
        initViewModel()
        initViews()
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
        killThreadIfPresent()
        super.onStop()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        killThreadIfPresent()
        super.onDestroy()
    }

    override fun onDetach() {
        killThreadIfPresent()
        super.onDetach()
    }

    private fun killThreadIfPresent() {
        if (thread != null) {
            thread!!.interrupt()
            thread = null
        }
    }
}
