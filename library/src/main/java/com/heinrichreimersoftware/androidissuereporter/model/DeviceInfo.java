/*
 * MIT License
 *
 * Copyright (c) 2017 Jan Heinrich Reimer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.heinrichreimersoftware.androidissuereporter.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.Arrays;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

public class DeviceInfo {
    private final int versionCode;
    private final String versionName;
    private final String buildVersion = Build.VERSION.INCREMENTAL;
    private final String releaseVersion = Build.VERSION.RELEASE;
    @IntRange(from = 0)
    private final int sdkVersion = Build.VERSION.SDK_INT;
    private final String buildID = Build.DISPLAY;
    private final String brand = Build.BRAND;
    private final String manufacturer = Build.MANUFACTURER;
    private final String device = Build.DEVICE;
    private final String model = Build.MODEL;
    private final String product = Build.PRODUCT;
    private final String hardware = Build.HARDWARE;
    @SuppressLint("NewApi")
    private final String[] abis = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ?
            Build.SUPPORTED_ABIS : new String[]{Build.CPU_ABI, Build.CPU_ABI2};
    @SuppressLint("NewApi")
    private final String[] abis32Bits = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ?
            Build.SUPPORTED_32_BIT_ABIS : null;
    @SuppressLint("NewApi")
    private final String[] abis64Bits = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ?
            Build.SUPPORTED_64_BIT_ABIS : null;

    public DeviceInfo(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
        }
        if (packageInfo != null) {
            versionCode = packageInfo.versionCode;
            versionName = packageInfo.versionName;
        } else {
            versionCode = -1;
            versionName = null;
        }
    }

    String toMarkdown() {
        return "Device info:\n"
                + "---\n"
                + "<table>\n"
                + "<tr><td>App version</td><td>" + versionName + "</td></tr>\n"
                + "<tr><td>App version code</td><td>" + versionCode + "</td></tr>\n"
                + "<tr><td>Android build version</td><td>" + buildVersion + "</td></tr>\n"
                + "<tr><td>Android release version</td><td>" + releaseVersion + "</td></tr>\n"
                + "<tr><td>Android SDK version</td><td>" + sdkVersion + "</td></tr>\n"
                + "<tr><td>Android build ID</td><td>" + buildID + "</td></tr>\n"
                + "<tr><td>Device brand</td><td>" + brand + "</td></tr>\n"
                + "<tr><td>Device manufacturer</td><td>" + manufacturer + "</td></tr>\n"
                + "<tr><td>Device name</td><td>" + device + "</td></tr>\n"
                + "<tr><td>Device model</td><td>" + model + "</td></tr>\n"
                + "<tr><td>Device product name</td><td>" + product + "</td></tr>\n"
                + "<tr><td>Device hardware name</td><td>" + hardware + "</td></tr>\n"
                + "<tr><td>ABIs</td><td>" + Arrays.toString(abis) + "</td></tr>\n"
                + "<tr><td>ABIs (32bit)</td><td>" + Arrays.toString(abis32Bits) + "</td></tr>\n"
                + "<tr><td>ABIs (64bit)</td><td>" + Arrays.toString(abis64Bits) + "</td></tr>\n"
                + "</table>\n";
    }

    @NonNull
    @Override
    public String toString() {
        return "App version: " + versionName + "\n"
                + "App version code: " + versionCode + "\n"
                + "Android build version: " + buildVersion + "\n"
                + "Android release version: " + releaseVersion + "\n"
                + "Android SDK version: " + sdkVersion + "\n"
                + "Android build ID: " + buildID + "\n"
                + "Device brand: " + brand + "\n"
                + "Device manufacturer: " + manufacturer + "\n"
                + "Device name: " + device + "\n"
                + "Device model: " + model + "\n"
                + "Device product name: " + product + "\n"
                + "Device hardware name: " + hardware + "\n"
                + "ABIs: " + Arrays.toString(abis) + "\n"
                + "ABIs (32bit): " + Arrays.toString(abis32Bits) + "\n"
                + "ABIs (64bit): " + Arrays.toString(abis64Bits);
    }
}
