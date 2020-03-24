package com.yich;

import org.gradle.api.Plugin;
import org.gradle.api.Project;


 class FirstPlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
        project.task("testTask") doLast{
                println("我的第一个gradle插件")
        }
    }
}