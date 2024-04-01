pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SSUPlector"

include(":app")
include(":domain")
include(":data")
include(":feature")
include(":feature:mypage")
include(":feature:projects")
include(":core:designsystem")
include(":core:common")
