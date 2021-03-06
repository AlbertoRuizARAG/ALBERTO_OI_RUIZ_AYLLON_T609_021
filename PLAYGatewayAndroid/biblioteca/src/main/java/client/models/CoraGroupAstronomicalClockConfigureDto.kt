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

import org.openapitools.client.models.AstronomicalClockConfigDataDto

import com.squareup.moshi.Json

/**
 * 
 *
 * @param groupId 
 * @param astronomicalClockConfigDataDto 
 * @param launchTime 
 * @param allCoras 
 */

data class CoraGroupAstronomicalClockConfigureDto (

    @Json(name = "groupId")
    val groupId: kotlin.Int,

    @Json(name = "astronomicalClockConfigDataDto")
    val astronomicalClockConfigDataDto: AstronomicalClockConfigDataDto,

    @Json(name = "launchTime")
    val launchTime: java.time.OffsetDateTime,

    @Json(name = "allCoras")
    val allCoras: kotlin.Boolean? = null

)

