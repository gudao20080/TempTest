package com.example.test2.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * User: WangKai(123940232@qq.com)
 * 2015-12-11 14:11
 */
public class MsSubBankAutoCompleteTextView extends AutoCompleteTextView{

    private static final String[] COUNTRIES = new String[]{
        "Belgium", "France", "Italy", "Germany", "Spain",
        "abc", "abcd",
        "abcde",
        "abcdef",
        "abcdefg",
        "hij",
        "hijk",
        "hijkl",
        "hijklm",
        "hijklmn"
    };

    private ArrayAdapter<String> adapter;

    public MsSubBankAutoCompleteTextView(Context context) {
        super(context);
    }

    public MsSubBankAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MsSubBankAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String bankName = s.toString();
                if (!TextUtils.isEmpty(bankName)) {
//                    load(bankName);


                    adapter.clear();
                        adapter.addAll(Arrays.asList(COUNTRIES));
                        adapter.notifyDataSetChanged();
                }

            }
        });
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
            new ArrayList<String>());
        setAdapter(adapter);

    }


}
