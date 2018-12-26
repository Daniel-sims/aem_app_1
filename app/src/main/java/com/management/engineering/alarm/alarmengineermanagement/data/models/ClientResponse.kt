package com.management.engineering.alarm.alarmengineermanagement.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ClientResponse(

        @SerializedName("id")
        val pk: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("account_number")
        val accountNumber: String,

        @SerializedName("mobile_number")
        val mobileNumber: String,

        @SerializedName("landline_number")
        val landlineNumber: String,

        @SerializedName("email")
        val email: String,

        @SerializedName("description")
        val description: String,

        @SerializedName("system_details")
        val systemDetails: String,

        @SerializedName("customer")
        val customers: List<CustomerResponse>

) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            TODO("customers"))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(pk)
        parcel.writeString(name)
        parcel.writeString(accountNumber)
        parcel.writeString(mobileNumber)
        parcel.writeString(landlineNumber)
        parcel.writeString(email)
        parcel.writeString(description)
        parcel.writeString(systemDetails)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClientResponse> {
        override fun createFromParcel(parcel: Parcel): ClientResponse {
            return ClientResponse(parcel)
        }

        override fun newArray(size: Int): Array<ClientResponse?> {
            return arrayOfNulls(size)
        }
    }
}
