package com.shopify.testify.task.internal

import com.shopify.testify.DeviceUtility
import com.shopify.testify.task.deprecated.PullScreenshotsTask

class PullScreenshotsSyncTask extends PullScreenshotsTask {

    @Override
    boolean isHidden() {
        return true
    }

    @Override
    String getDescription() {
        return "Pull screenshots and wait for all the files to be committed to disk"
    }

    @Override
    def taskAction() {
        super.taskAction()
        def failedScreenshots = new DeviceUtility(project).detectFailedScreenshots();
        if (failedScreenshots.length > 0) {
            println "Pulling screenshots:"
            for (int i = 0; i < failedScreenshots.size(); i++) {
                println "\tCopied " + new File(failedScreenshots[i]).name + "..."
                sleep(125);
            }
            println "\n"
        }
    }
}