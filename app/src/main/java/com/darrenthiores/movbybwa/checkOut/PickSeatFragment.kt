package com.darrenthiores.movbybwa.checkOut

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.darrenthiores.core.model.data.CheckOut
import com.darrenthiores.movbybwa.R
import com.darrenthiores.movbybwa.databinding.FragmentPickSeatBinding

class PickSeatFragment : Fragment() {

    private var _binding:FragmentPickSeatBinding? = null
    private val binding get() = _binding
    private var statusB3:Boolean = false
    private var statusB4:Boolean = false
    private val checkOutList = arrayListOf<CheckOut>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPickSeatBinding.inflate(layoutInflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = PickSeatFragmentArgs.fromBundle(arguments as Bundle).title
        buttonBuyTicket(0)

        binding?.apply {
            tvPickSeatName.text = title

            btUp.setOnClickListener { requireActivity().onBackPressed() }

            b3.setOnClickListener {
                if(statusB3){
                    statusB3 = false
                    b3.setBackgroundResource(R.drawable.empty)

                    checkOutList.remove(CheckOut("B3", 70000))
                    buttonBuyTicket(checkOutList.size)

                } else {
                    statusB3 = true
                    b3.setBackgroundResource(R.drawable.selected)

                    checkOutList.add(CheckOut("B3", 70000))
                    buttonBuyTicket(checkOutList.size)
                }
            }

            b4.setOnClickListener {
                if(statusB4){
                    statusB4 = false
                    b4.setBackgroundResource(R.drawable.empty)

                    checkOutList.remove(CheckOut("B4", 70000))
                    buttonBuyTicket(checkOutList.size)

                } else {
                    statusB4 = true
                    b4.setBackgroundResource(R.drawable.selected)

                    checkOutList.add(CheckOut("B4", 70000))
                    buttonBuyTicket(checkOutList.size)
                }

            }

            btBuyTicket.setOnClickListener {
                val mBundle = Bundle()
                mBundle.putParcelableArrayList(SEAT_DATA, checkOutList)
                findNavController().navigate(R.id.action_pickSeatFragment_to_checkOutFragment, mBundle)
            }

        }

    }

    private fun buttonBuyTicket(total:Int){
        binding?.apply {
            if(total<=0){
                btBuyTicket.text = getString(R.string.beli_ticket_d, 0)
                btBuyTicket.isEnabled = false
            } else{
                btBuyTicket.text = getString(R.string.beli_ticket_d, total)
                btBuyTicket.isEnabled = true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        const val SEAT_DATA = "SEAT_DATA"
    }

}