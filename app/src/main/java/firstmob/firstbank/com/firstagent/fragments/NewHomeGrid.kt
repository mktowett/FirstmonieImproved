package firstmob.firstbank.com.firstagent.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import firstmob.firstbank.com.firstagent.Activity.*
import kotlinx.android.synthetic.main.fragment_new_home_grid.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [NewHomeGrid.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [NewHomeGrid.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewHomeGrid : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_new_home_grid, container, false)

        val rldepo: RelativeLayout? = view?.findViewById(R.id.rl5)
        rldepo?.setOnClickListener(){

            val i = Intent(activity, CashDepoActivity::class.java)

            startActivity(i)
        }


        val rlinboxchos: RelativeLayout? = view?.findViewById(R.id.rlinbox)
        rlinboxchos?.setOnClickListener(){

            val i = Intent(activity, InboxActivity::class.java)

            startActivity(i)
        }

        val rltransfer: RelativeLayout? = view?.findViewById(R.id.rltransfer)
        rltransfer?.setOnClickListener(){

            val i = Intent(activity, FTMenuActivity::class.java)

            startActivity(i)
        }

        return view;
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewHomeGrid.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                NewHomeGrid().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
