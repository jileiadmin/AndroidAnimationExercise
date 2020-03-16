/*
 * Copyright (C) 2018 Jared Rummler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.engineer.imitate.ui.activity

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.engineer.imitate.R
import com.engineer.imitate.ui.widget.view.DemoPresenter
import com.engineer.imitate.ui.widget.view.DemoView
import com.jaredrummler.simplemvp.MvpAppCompatActivity
import kotlinx.android.synthetic.main.activity_run_shell.*


class RunShellActivity : MvpAppCompatActivity<DemoPresenter>(), DemoView {

    private var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_shell)
        commandsEditText.addTextChangedListener(object : TextChangeListener() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                runButton.isEnabled = !TextUtils.isEmpty(s)
            }
        })
        presenter.checkIfRootIsAvailable()
    }

    fun onRun(v: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(commandsEditText.windowToken, 0)
        val command = commandsEditText.text.toString()
        val asRoot = rootCheckBox.isChecked
        presenter.execute(asRoot, command)
    }

    override fun onRootAvailable(available: Boolean) {
        rootCheckBox.isChecked = available
    }

    override fun showProgress() {
        dialog = ProgressDialog.show(this, "Running Command", "Please Wait...")
        dialog?.setCancelable(true)
    }

    override fun hideProgress() {
        dialog?.dismiss()
    }

    override fun showResult(result: CharSequence) {
        Log.e("tag", ": $result")
        outputTextView.text = result
    }

    override fun createPresenter(): DemoPresenter = DemoPresenter()

    abstract class TextChangeListener : TextWatcher {
        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    }

}