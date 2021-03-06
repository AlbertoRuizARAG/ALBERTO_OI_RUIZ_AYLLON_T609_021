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
 * @param newAlertCount 
 * @param seenAlertCount 
 * @param totalAlertCount 
 * @param activeAlertCount 
 * @param closedAlertCount 
 */

data class CoraAlertDashboardDto (

    @Json(name = "newAlertCount")
    val newAlertCount: kotlin.Int? = null,

    @Json(name = "seenAlertCount")
    val seenAlertCount: kotlin.Int? = null,

    @Json(name = "totalAlertCount")
    val totalAlertCount: kotlin.Int? = null,

    @Json(name = "activeAlertCount")
    val activeAlertCount: kotlin.Int? = null,

    @Json(name = "closedAlertCount")
    val closedAlertCount: kotlin.Int? = null

)

