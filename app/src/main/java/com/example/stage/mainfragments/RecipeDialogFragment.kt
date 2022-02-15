package com.example.stage.mainfragments


import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.example.stage.R

class RecipeDialogFragment: DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.recipe_dialog,container,false)

        //크기설정
        dialog?.setContentView(R.layout.recipe_dialog)
        dialog?.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog!!.setCancelable(false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recipeExitBtn = view.findViewById<ImageButton>(R.id.recipe_exit_button)
        recipeExitBtn.setOnClickListener {
            dismiss()
            parentFragmentManager.beginTransaction().replace(
                R.id.mainpage_fragment_container_view,
                CategoryFragment()
            ).commit()
        }
    }
}