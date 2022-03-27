package com.agesadev.passengerlist.models


data class PassengerResponse(
    val `data`: List<Passenger>,
    val totalPages: Int,
    val totalPassengers: Int
)

data class Passenger(
    val _v: Int,
    val _id: String,
    val airline: Airline,
    val name: String,
    val trips: Int
)

data class Airline(
    val country: String,
    val established: String,
    val head_quarter: String,
    val id: String,
    val logo: String,
    val name: String,
    val slogan: String,
    val website: String
)