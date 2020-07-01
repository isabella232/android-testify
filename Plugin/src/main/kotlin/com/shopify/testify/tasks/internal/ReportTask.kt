package com.shopify.testify.tasks.internal

abstract class ReportTask : TestifyDefaultTask() {

    override fun getGroup() = "Testify reports"

    protected val reportName: String
        get() {
            return project.properties["reportFileName"]?.toString() ?: DEFAULT_REPORT_FILE_NAME
        }

    protected val destinationPath: String
        get() {
            return project.properties["reportPath"]?.toString() ?: "."
        }
}

internal const val DEFAULT_REPORT_FILE_NAME = "report.yml"
