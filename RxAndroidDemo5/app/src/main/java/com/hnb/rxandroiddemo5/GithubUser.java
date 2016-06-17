package com.hnb.rxandroiddemo5;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Huynh Binh PC on 6/17/2016.
 */
public class GithubUser
{
    @SerializedName("login")
    public String login;

    @SerializedName("id")
    public int id;

    @SerializedName("avatar_url")
    public String avatar_url;

    @SerializedName("name")
    public String name;

    @SerializedName("location")
    public String location;

    @SerializedName("email")
    public String email;

    @SerializedName("created_at")
    public String created_at;
}
