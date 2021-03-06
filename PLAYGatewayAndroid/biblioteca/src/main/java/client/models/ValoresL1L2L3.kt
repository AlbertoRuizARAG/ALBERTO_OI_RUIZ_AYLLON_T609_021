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
 * @param numeroFase 
 * @param tipoMedicionInstMaxMin 
 * @param potenciaActivaW 
 * @param potenciaInductivaVar 
 * @param potenciaCapacitivaVar 
 * @param potenciaAparenteVA 
 * @param factorPotenciaX100 
 * @param cosenoFiX100 
 * @param frecuenciaL1Hzx100 
 * @param tensionL1L2Vx10 
 * @param tensionL2L3Vx10 
 * @param tensionL3L1Vx10 
 */

data class ValoresL1L2L3 (

    @Json(name = "numeroFase")
    val numeroFase: kotlin.Int? = null,

    @Json(name = "tipoMedicionInstMaxMin")
    val tipoMedicionInstMaxMin: kotlin.Int? = null,

    @Json(name = "potenciaActiva_W")
    val potenciaActivaW: kotlin.collections.List<kotlin.Int>? = null,

    @Json(name = "potenciaInductiva_var")
    val potenciaInductivaVar: kotlin.collections.List<kotlin.Int>? = null,

    @Json(name = "potenciaCapacitiva_var")
    val potenciaCapacitivaVar: kotlin.collections.List<kotlin.Int>? = null,

    @Json(name = "potenciaAparente_VA")
    val potenciaAparenteVA: kotlin.collections.List<kotlin.Int>? = null,

    @Json(name = "factorPotencia_x100")
    val factorPotenciaX100: kotlin.collections.List<kotlin.Int>? = null,

    @Json(name = "cosenoFi_x100")
    val cosenoFiX100: kotlin.collections.List<kotlin.Int>? = null,

    @Json(name = "frecuenciaL1_Hzx100")
    val frecuenciaL1Hzx100: kotlin.collections.List<kotlin.Int>? = null,

    @Json(name = "tensionL1L2_Vx10")
    val tensionL1L2Vx10: kotlin.collections.List<kotlin.Int>? = null,

    @Json(name = "tensionL2L3_Vx10")
    val tensionL2L3Vx10: kotlin.collections.List<kotlin.Int>? = null,

    @Json(name = "tensionL3L1_Vx10")
    val tensionL3L1Vx10: kotlin.collections.List<kotlin.Int>? = null

)

