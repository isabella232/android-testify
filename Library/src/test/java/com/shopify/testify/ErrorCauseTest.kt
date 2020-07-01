package com.shopify.testify

import android.app.Activity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.shopify.testify.internal.exception.ActivityMustImplementResourceOverrideException
import com.shopify.testify.internal.exception.ActivityNotRegisteredException
import com.shopify.testify.internal.exception.AssertSameMustBeLastException
import com.shopify.testify.internal.exception.MissingAssertSameException
import com.shopify.testify.internal.exception.MissingScreenshotInstrumentationAnnotationException
import com.shopify.testify.internal.exception.NoScreenshotsOnUiThreadException
import com.shopify.testify.internal.exception.RootViewNotFoundException
import com.shopify.testify.internal.exception.ScreenshotBaselineNotDefinedException
import com.shopify.testify.internal.exception.ScreenshotDirectoryNotFoundException
import com.shopify.testify.internal.exception.ScreenshotIsDifferentException
import com.shopify.testify.internal.exception.TestMustLaunchActivityException
import com.shopify.testify.internal.exception.TestMustWrapContextException
import com.shopify.testify.internal.exception.ViewModificationException
import com.shopify.testify.report.ErrorCause
import org.junit.Assert.assertEquals
import org.junit.Test

class ErrorCauseTest {

    @Test
    fun `all exceptions match their cause`() {
        assertEquals(ErrorCause.ACTIVITY_OVERRIDE, ErrorCause.match(ActivityMustImplementResourceOverrideException("")))
        assertEquals(ErrorCause.ASSERT_LAST, ErrorCause.match(AssertSameMustBeLastException()))
        assertEquals(ErrorCause.DIFFERENT, ErrorCause.match(ScreenshotIsDifferentException("", "")))
        assertEquals(ErrorCause.LAUNCH_ACTIVITY, ErrorCause.match(TestMustLaunchActivityException("")))
        assertEquals(ErrorCause.NO_ACTIVITY, ErrorCause.match(ActivityNotRegisteredException(Activity::class.java)))
        assertEquals(ErrorCause.NO_ANNOTATION, ErrorCause.match(MissingScreenshotInstrumentationAnnotationException("")))
        assertEquals(ErrorCause.NO_ASSERT, ErrorCause.match(MissingAssertSameException()))
        assertEquals(ErrorCause.NO_BASELINE, ErrorCause.match(ScreenshotBaselineNotDefinedException("", "", "")))
        assertEquals(ErrorCause.NO_DIRECTORY, ErrorCause.match(ScreenshotDirectoryNotFoundException(false, "")))
        assertEquals(ErrorCause.NO_ROOT_VIEW, ErrorCause.match(RootViewNotFoundException(mock {
            whenever(mock.resources).thenReturn(mock())
        }, 0)))
        assertEquals(ErrorCause.UI_THREAD, ErrorCause.match(NoScreenshotsOnUiThreadException()))
        assertEquals(ErrorCause.VIEW_MODIFICATION, ErrorCause.match(ViewModificationException(Throwable())))
        assertEquals(ErrorCause.WRAP_CONTEXT, ErrorCause.match(TestMustWrapContextException("")))
        assertEquals(ErrorCause.UNKNOWN, ErrorCause.match(Throwable()))
    }
}
