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

import org.openapitools.client.models.DatosSequence

import com.squareup.moshi.Json

/**
 * 
 *
 * @param datos 
 * @param identificadorSecuencia 
 */

data class CoraDefaultSequenceDataDto (

    @Json(name = "datos")
    val datos: DatosSequence? = null,

    @Json(name = "identificadorSecuencia")
    val identificadorSecuencia: kotlin.Int? = null

)

