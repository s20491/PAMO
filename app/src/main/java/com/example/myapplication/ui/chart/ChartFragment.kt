package com.example.myapplication.ui.chart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentChartBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class ChartFragment : Fragment() {
    private var binding: FragmentChartBinding? = null
    private var chartViewModel: ChartViewModel? = null
    private lateinit var barchart: BarChart
    private var barData: BarData? = null
    private var barDataSet: BarDataSet? = null
    private var barEntries: ArrayList<BarEntry>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        chartViewModel = ViewModelProvider(requireActivity()).get(ChartViewModel::class.java)
        binding = FragmentChartBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        barchart = root.findViewById(R.id.barchart)
        data
        barDataSet = BarDataSet(barEntries, "Data Set")
        barData = BarData(barDataSet)
        barchart.setData(barData)
        barDataSet!!.setColors(*ColorTemplate.MATERIAL_COLORS)
        barDataSet!!.valueTextColor = Color.BLUE
        barDataSet!!.valueTextSize = 14f
        return root
    }



    private val data: Unit
        private get() {
            barEntries = ArrayList<BarEntry>()
            (barEntries as ArrayList<BarEntry>).add(BarEntry(1f, 10f))
            (barEntries as ArrayList<BarEntry>).add(BarEntry(2f, 5f))
            (barEntries as ArrayList<BarEntry>).add(BarEntry(3f, 12f))
            (barEntries as ArrayList<BarEntry>).add(BarEntry(4f, 8f))
            (barEntries as ArrayList<BarEntry>).add(BarEntry(5f, 4f))
            (barEntries as ArrayList<BarEntry>).add(BarEntry(6f, 20f))
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}