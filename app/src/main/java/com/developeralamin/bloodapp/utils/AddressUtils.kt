package com.developeralamin.bloodapp.utils

object AddressUtils {
    private val divisions = listOf(
        "Select Division",
        "Barishal",
        "Chattogram",
        "Dhaka",
        "Khulna",
        "Rajshahi",
        "Rangpur",
        "Sylhet",
        "Mymensingh"
    )

    private val districts = hashMapOf(
        divisions[0] to listOf("Select District"),
        divisions[1] to listOf(
            "Select District",
            "Barishal",
            "Barguna",
            "Bhola",
            "Jhalokati",
            "Patuakhali",
            "Pirojpur"
        ),
        divisions[2] to listOf(
            "Select District",
            "Chattogram",
            "Bandarban",
            "Brahmanbaria",
            "Chandpur",
            "Cumilla",
            "Cox's Bazar",
            "Feni",
            "Khagrachari",
            "Lakshmipur",
            "Noakhali",
            "Rangamati"
        ),
        divisions[3] to listOf(
            "Select District",
            "Dhaka",
            "Faridpur",
            "Gazipur",
            "Gopalganj",
            "Kishoreganj",
            "Madaripur",
            "Manikganj",
            "Munshiganj",
            "Narayanganj",
            "Narsingdi"
        ),
        divisions[4] to listOf(
            "Select District",
            "Khulna",
            "Bagerhat",
            "Chuadanga",
            "Jashore",
            "Jhenaidah",
            "Kushtia",
            "Magura",
            "Meherpur",
            "Narail",
            "Satkhira"
        ),
        divisions[5] to listOf(
            "Select District",
            "Rajshahi",
            "Bogura",
            "Joypurhat",
            "Natore",
            "Pabna",
            "Sirajgonj"
        ),
        divisions[6] to listOf(
            "Select District",
            "Rangpur",
            "Dinajpur",
            "Gaibandha",
            "Kurigram",
            "Lalmonirhat",
            "Nilphamari",
            "Panchagarh",
            "Thakurgaon"
        ),
        divisions[7] to listOf("Select District", "Sylhet", "Habiganj", "Maulvibazar", "Sunamganj"),
        divisions[8] to listOf("Select District", "Mymensingh", "Jamalpur", "Netrokona", "Sherpur"),
    )

    private val thanas = hashMapOf(
        "Abul" to listOf("THana", "kana"),
        "Kabul" to listOf("KabulTHana", "Kabulkana")
    )

    fun getDivisions(): List<String> = divisions
    fun getDistrict(division: String): List<String> = districts.get(division) ?: listOf()

    fun getThan(district: String): List<String> = thanas[district] ?: listOf()
}