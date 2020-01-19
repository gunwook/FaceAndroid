package com.gunwook.faceapp.ui.feedPage.model

import com.gunwook.faceapp.ui.loginPage.model.AuthModel

data class FeedModel(var story_id : Long, var story_imgs : List<String>, var story_date : String, var story_title : String, var story_tag : String, var story_message : String, var story_visible : String , var user : AuthModel? = null)