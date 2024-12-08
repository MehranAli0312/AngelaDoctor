package com.example.angeladoctor.ui.clean.data.model

import com.google.gson.annotations.SerializedName


data class ResponseModel(
    val problems: List<Problem>
)

data class Problem(
    val Diabetes: List<Diabetes>
)

data class Diabetes(
    val medications: List<Medication>,
    val labs: List<Lab>
)

data class Medication(
    val medicationsClasses: List<MedicationsClass>
)

data class MedicationsClass(
    val className: List<ClassName>,
    val className2: List<ClassName>
)

data class ClassName(
    val associatedDrug: List<Drug>,
    @SerializedName("associatedDrug#2")
    val associatedDrug2: List<Drug>
)

data class Drug(
    val name: String = "",
    val dose: String = "",
    val strength: String = ""
)

data class Lab(
    val missing_field: String
)
