package com.CyberDunkers.Sla7ly.data.models

data class GetAllJobsResponseItem(
    val created_at: String,
    val description: String,
    val id: Int,
    val img: String,
    val length: String,
    val profession: String,
    val stage: String,
    val title: String,
    val updated_at: String,
    val user_id: Int,
    val width: String,
    val zone: String
)