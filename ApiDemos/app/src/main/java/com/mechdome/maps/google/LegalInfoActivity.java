/*
 * Copyright (C) 2012 The Android Open Source Project
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

package com.mechdome.maps.google;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
//import com.google.android.gms.common.GooglePlayServicesUtil;

//import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.lang.reflect.Method;

/**
 * Activity to show legal information.
 */
public class LegalInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.legal_info);

        TextView legalInfoTextView = (TextView) findViewById(R.id.legal_info);
//        String openSourceSoftwareLicenseInfo = //"Google Play services 11.2 - The getOpenSourceSoftwareLicenseInfo() method in the GoogleApiAvailability class is now deprecated. ";
//                GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo(this);
//                    GoogleApiAvailability.getInstance().getOpenSourceSoftwareLicenseInfo(this);

        String openSourceSoftwareLicenseInfo;
        try {
            Method getOpenSourceSoftwareLicenseInfoMethod = GoogleApiAvailability.class.getMethod("getOpenSourceSoftwareLicenseInfo", Context.class);
            openSourceSoftwareLicenseInfo = (String)getOpenSourceSoftwareLicenseInfoMethod.invoke(GoogleApiAvailability.getInstance(), this);
        } catch (Exception e) {
            openSourceSoftwareLicenseInfo = "Google Play services 11.2 - The getOpenSourceSoftwareLicenseInfo() method in the GoogleApiAvailability class is now deprecated. ";
        }

        if (openSourceSoftwareLicenseInfo != null) {
            legalInfoTextView.setText(openSourceSoftwareLicenseInfo);
        } else {
            legalInfoTextView.setText(R.string.play_services_not_installed);
        }
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
