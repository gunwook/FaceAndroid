package com.gunwook.faceapp.utils

object CodeUtils {

    object Network {
        const val MASTER_API = "http://192.168.35.45:3000/api/"
        const val IMAGE_URL = "http://development-blog-image.s3-website.ap-northeast-2.amazonaws.com/"

        object Api {
            const val LOGIN = "auth/signin"
            const val SIGN  = "auth/signup"
            const val STORY = "story"
            const val AUTH = "auth/"
            const val EMAIL = "auth/email"
        }

        object Params {
            const val USER_EMAIL = "email"
            const val USER_PASSWORD = "password"
            const val PUSH_ID = "push_id"
            const val STORY_DATE = "story_date"
            const val STORY_TITLE = "story_title"
            const val STORY_TAG = "story_tag"
            const val FILES = "files"
            const val NAME = "name"
            const val NICKNAME = "nickname"
            const val EMAIL = "email"
            const val SUBJECT = "subject"
            const val CONTENT = "content"
            const val GENDER = "gender"
            const val MESSAGE = "message"
            const val CREATEAT = "createAt"
            const val PASSWORD = "password"
            const val PAGE = "page"
            const val LIMIT = "limit"
            const val STORY_MESSAGE = "story_message"
        }
    }

    object PreferenceCode {
        const val PREF_NAME = "FaceApp"
        const val TOKEN = "token"
        const val STATUS = "status"
        const val USER_ID = "user_id"
        const val EMAIL = "email"
        const val NAME = "name"
        const val FCM_TOKEN = "fcm_token"
    }

    object Parser {
        const val TOKEN = "token"
        const val USER_ID = "user_id"
        const val STATUS = "status"
        const val NAME = "name"
        const val EMAIL = "email"
        const val STORY_ID = "story_id"
        const val STORY_IMGS = "story_imgs"
        const val STORY_DATE = "story_date"
        const val STORY_TITLE = "story_title"
        const val STORY_TAG = "story_tag"
        const val STORY_MESSAGE = "story_message"
        const val STORY_VISIBLE = "story_visible"
    }

    object ActivityCode {
        const val ALBUM_KEY = "album_key"
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }


    object CommonCode{
        const val MAX_PAGE = 30
    }
}