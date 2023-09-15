package com.sungil.wear.presentation.utill

import timber.log.Timber

class TimberDebugTree : Timber.DebugTree() {

    private val filterName = "Timber"

    override fun createStackElementTag(element: StackTraceElement): String {
        return "${filterName}(${element.fileName})"
    }
}