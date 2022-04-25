package ble.libraries

import UuidUtils.convert16to128UUID
import java.util.*

class UuidConstants {
    companion object {
        // MARK: Servicios Nodo
        val BLE_SERVICE_DeviceInformation: UUID = UUID.fromString(convert16to128UUID("180A"))

        val BLE_SERVICE_SiliconLabsOta: UUID = UUID.fromString("12345678-FD63-4FA1-BFA4-8F47B42119F0")

        var BLE_SERVICE_CURRENT_TIME: UUID = UUID.fromString(convert16to128UUID("1805"))

        val BLE_SERVICE_Programming: UUID = UUID.fromString("12345678-e997-4d99-b542-6c065251e62b")

        val BLE_SERVICE_RelojCrepuscular: UUID = UUID.fromString("12345678-8323-463e-9a7d-b59ba22ed952")

        // OTA CHARACTERISTICS
        val BLE_CHARACTERISTIC_OTADataAttributeUUIDString: UUID = UUID.fromString("12345678-34fc-4045-a5d0-2c581f81a153")
        val BLE_CHARACTERISTIC_OTAControlAttributeUUIDString: UUID = UUID.fromString("12345678-fb6d-4e53-88a4-5e37e0326063")


        //DEVICEINFORMATION.CHARACTERISTICS
        val BLE_CHARACTERISTIC_ManufacturerName: UUID = UUID.fromString(convert16to128UUID("2A29"))
        val BLE_CHARACTERISTIC_ModelNumber: UUID = UUID.fromString(convert16to128UUID("2A24"))
        val BLE_CHARACTERISTIC_VersionSwDevice: UUID = UUID.fromString(convert16to128UUID("2A28"))
        val BLE_CHARACTERISTIC_VersionHwDevice: UUID = UUID.fromString(convert16to128UUID("2A27"))

        //PROGRAMMING.CHARACTERISTICS
        val BLE_CHARACTERISTIC_SequenceSettings: UUID = UUID.fromString("12345678-a3a4-4f31-8f8a-60d205cd5e1f")
        val BLE_CHARACTERISTIC_StartProgramming: UUID = UUID.fromString("12345678-e4b9-496a-b425-3ff84a3d4228")


        //CURRENT TIME-CHARACTERISTICS
        val BLE_CHARACTERISTIC_CurrentTime: UUID = UUID.fromString(convert16to128UUID("2A2B"))

        //RELOJ CREPUSCULAR CHARACTERISTICS
       val BLE_CHARACTERISTIC_CREPUSCULAR_HORA_ACTUAL_RELOJ_ASTRONOMICO: UUID = UUID.fromString("12345678-6151-49c0-a7ef-e74f7314305d")


    }
}
