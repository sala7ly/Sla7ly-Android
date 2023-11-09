package com.CyberDunkers.Sla7ly.data.models

data class GetPostCommentsResponseItem(
    val accept: Int,
    val created_at: String,
    val days: Int,
    val description: String,
    val id: Int,
    val job_id: Int,
    val price: Int,
    val updated_at: String,
    val worker_id: Int
)