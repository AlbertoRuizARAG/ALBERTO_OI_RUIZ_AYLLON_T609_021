package com.prilux.cmr.global

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import org.openapitools.client.models.CoraListDto

object CmrBleActiveDevice {
    var coraCmr =  CoraListDto ()
    var device: BluetoothDevice? = null
    var deviceGatt: BluetoothGatt? = null

    fun clearObject () {
        coraCmr =  CoraListDto ()
        device = null
        deviceGatt = null
    }
}
