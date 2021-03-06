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

import org.openapitools.client.models.CoraCircuitEnableCirtuitInfoDto

import com.squareup.moshi.Json

/**
 * 
 *
 * @param coraId 
 * @param delayInMinutes 
 * @param circuitInfo 
 * @param launchTime 
 * @param allCoras 
 */

data class CoraCircuitEnableDto (

    @Json(name = "coraId")
    val coraId: kotlin.Int,

    @Json(name = "delayInMinutes")
    val delayInMinutes: kotlin.Int,

    @Json(name = "circuitInfo")
    val circuitInfo: kotlin.collections.List<CoraCircuitEnableCirtuitInfoDto>? = null,

    @Json(name = "launchTime")
    val launchTime: java.time.OffsetDateTime? = null,

    @Json(name = "allCoras")
    val allCoras: kotlin.Boolean? = null

)

