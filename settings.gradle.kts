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
        maven { setUrl("https://devrepo.kakao.com/nexus/content/groups/public/") }
    }
}

rootProject.name = "SSUPlector"

include(":app")
include(":domain")
include(":data")
include(":feature")
include(":feature:projects")
include(":core:designsystem")
include(":core:common")
include(":core:network")
