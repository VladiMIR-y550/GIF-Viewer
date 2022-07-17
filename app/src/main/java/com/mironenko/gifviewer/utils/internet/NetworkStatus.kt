package com.mironenko.gifviewer.utils.internet

sealed class NetworkStatus {
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
}