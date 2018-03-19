package fr.bessugesv.starwarschauffeurprive.common.utils

import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test

/**
 * Created by Vincent on 3/19/2018.
 */
class FormatTest {

    @Test
    fun millisecToReadableTime() {
        Assert.assertThat(Format.millisecToReadableTime(0), Matchers.equalTo("0:00:00"))
        Assert.assertThat(Format.millisecToReadableTime(1), Matchers.equalTo("0:00:00"))
        Assert.assertThat(Format.millisecToReadableTime(1000), Matchers.equalTo("0:00:01"))
        Assert.assertThat(Format.millisecToReadableTime(30500), Matchers.equalTo("0:00:30"))
        Assert.assertThat(Format.millisecToReadableTime(60000), Matchers.equalTo("0:01:00"))
        Assert.assertThat(Format.millisecToReadableTime(60001), Matchers.equalTo("0:01:00"))
        Assert.assertThat(Format.millisecToReadableTime(61001), Matchers.equalTo("0:01:01"))
        Assert.assertThat(Format.millisecToReadableTime(900000), Matchers.equalTo("0:15:00"))
        Assert.assertThat(Format.millisecToReadableTime(3600000), Matchers.equalTo("1:00:00"))
        Assert.assertThat(Format.millisecToReadableTime(39600000), Matchers.equalTo("11:00:00"))
    }
}