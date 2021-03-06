/**
 * PriluxWebUI API
 *
 * PriluxWebUI
 *
 * The version of the OpenAPI document: v1
 * 
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.openapitools.client.models


import com.squareup.moshi.Json

/**
 * 
 *
 * @param latitude 
 * @param longitude 
 * @param externalID 
 * @param streetType 
 * @param streetName 
 * @param localizationString 
 * @param coraExternalId 
 * @param externalCircuitId 
 * @param coraRealAttachedCircuitNumber 
 * @param supportType 
 * @param supportBrand 
 * @param supportModel 
 * @param height 
 * @param currentLuminaryType 
 * @param currentLampType 
 * @param currentPower 
 * @param futureNomPower 
 * @param futureAbsPower 
 * @param operativity 
 * @param auxiliarEquipment 
 * @param observations 
 * @param auxiliarEquipmentSituation 
 * @param lampStatus 
 * @param luminaryStatus 
 * @param supportStatus 
 */

data class CoraLuminaryImportDto (

    @Json(name = "latitude")
    val latitude: kotlin.Double? = null,

    @Json(name = "longitude")
    val longitude: kotlin.Double? = null,

    @Json(name = "externalID")
    val externalID: kotlin.Int? = null,

    @Json(name = "streetType")
    val streetType: kotlin.String? = null,

    @Json(name = "streetName")
    val streetName: kotlin.String? = null,

    @Json(name = "localizationString")
    val localizationString: kotlin.String? = null,

    @Json(name = "coraExternalId")
    val coraExternalId: kotlin.Int? = null,

    @Json(name = "externalCircuitId")
    val externalCircuitId: kotlin.Int? = null,

    @Json(name = "coraRealAttachedCircuitNumber")
    val coraRealAttachedCircuitNumber: kotlin.Int? = null,

    @Json(name = "supportType")
    val supportType: kotlin.String? = null,

    @Json(name = "supportBrand")
    val supportBrand: kotlin.String? = null,

    @Json(name = "supportModel")
    val supportModel: kotlin.String? = null,

    @Json(name = "height")
    val height: kotlin.Double? = null,

    @Json(name = "currentLuminaryType")
    val currentLuminaryType: kotlin.String? = null,

    @Json(name = "currentLampType")
    val currentLampType: kotlin.String? = null,

    @Json(name = "currentPower")
    val currentPower: kotlin.Double? = null,

    @Json(name = "futureNomPower")
    val futureNomPower: kotlin.Double? = null,

    @Json(name = "futureAbsPower")
    val futureAbsPower: kotlin.Double? = null,

    @Json(name = "operativity")
    val operativity: kotlin.String? = null,

    @Json(name = "auxiliarEquipment")
    val auxiliarEquipment: kotlin.String? = null,

    @Json(name = "observations")
    val observations: kotlin.String? = null,

    @Json(name = "auxiliarEquipmentSituation")
    val auxiliarEquipmentSituation: kotlin.String? = null,

    @Json(name = "lampStatus")
    val lampStatus: kotlin.String? = null,

    @Json(name = "luminaryStatus")
    val luminaryStatus: kotlin.String? = null,

    @Json(name = "supportStatus")
    val supportStatus: kotlin.String? = null

)

