package com.yourssu.soongpt.domain.timetable.implement.strategy

import com.yourssu.soongpt.domain.course.implement.Courses
import com.yourssu.soongpt.domain.courseTime.implement.Time
import com.yourssu.soongpt.domain.timetable.implement.CourseTimes

class GuaranteedLunchTimeStrategy: TagStrategy {
    override fun isCorrect(courses: Courses, courseTimes: CourseTimes): Boolean {
        return courseTimes.hasContinuousTime(Time.of("11:30"), Time.of("13:30"), 45)
    }
}