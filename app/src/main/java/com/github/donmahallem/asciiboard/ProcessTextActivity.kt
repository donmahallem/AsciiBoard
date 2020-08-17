package com.github.donmahallem.asciiboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.util.regex.Matcher
import java.util.regex.Pattern


class ProcessTextActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var txtPreview: TextView;
    lateinit var editInput: EditText;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process)
        val text = intent
            .getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString();
        // process the text
        val isReadOnlyTest=intent
            .getBooleanExtra(Intent.EXTRA_PROCESS_TEXT_READONLY, false);
        txtPreview = findViewById(R.id.txtPreview);
        editInput = findViewById(R.id.txtInput);
        editInput.setText(text.toString());
        findViewById<Button>(R.id.btnCancel).setOnClickListener(this);
        findViewById<Button>(R.id.btnOk).setOnClickListener(this);
        txtPreview.text =this.convertText(text);
        editInput.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                txtPreview.text=convertText(editable.toString());
            }
        });
    }

    private fun convertText(inp: String):String{
       return Pattern.compile("\\h+").matcher(inp.trim()).replaceAll("👏");
    }

    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.btnCancel -> this.finish();
            R.id.btnOk -> submit();
        }
    }

    fun submit(){
        var outputText=editInput.text.toString().trim();
        outputText=Pattern.compile("\\h+").matcher(outputText).replaceAll("👏");
        intent.putExtra(Intent.EXTRA_PROCESS_TEXT, outputText);
        setResult(Activity.RESULT_OK, intent);
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }
}
