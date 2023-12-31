package com.sungil.runningproejct_mvvm.utill

class Define {
    /**
     * static
     */
    companion object{
        const val FIREBASE_BASE_URL = "https://flabrunningproject-default-rtdb.firebaseio.com"
        const val FIREBASE_USERINFO_URL = "user"
        const val FIREBASE_FOLLOWER = "follower"
        const val FIREBASE_SNS = "sns"
        const val FIREBASE_SNS_test = "snsTest"

        //Message
        const val PROP_MESSAGE_SIGN_UP_OKAY = "signUpOkay"
        const val PROP_MESSAGE_SIGN_UP_NOT_OKAY = "signUpNotOkay"
        const val PROP_MESSAGE_SIGN_UP_SUCCESS = "signUpSuccess"
        const val PROP_MESSAGE_SIGN_UP_FAIL = "signUpFail"
        const val PROP_MESSAGE_ERROR_TO_NETWORK = "errorNetWork"
        const val PROP_MESSAGE_NO_USER="noUser"
        const val PROP_MESSAGE_SUCCESS_LOGIN = "successLogin"
        const val PROP_MESSAGE_CHECK_LOGIN_DATA = "checkLoginData"
        const val PROP_DATA_NULL = "dataNull"
        const val PROP_SAVE_USERINFO = "userInfo"

        //RoomDatabase
        const val WEAR_RUNNING_TABLE = "runningTable"
        const val USERINFO_TABLE = "userInfoTable"

        //Bundle Key
        const val KM_KEY = "kmKey"
    }
    enum class Status {
        LOADING ,
        SUCCESS ,
        ERROR
    }
}