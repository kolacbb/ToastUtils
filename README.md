[![](https://jitpack.io/v/kolacbb/ToastUtils.svg)](https://jitpack.io/#kolacbb/ToastUtils) [![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)

# Toaster
A Toast with strong compatible


* Same style on any device version.
* Easy shown on Thread.
* Fix Toast can't shown on up Android 7.0 device when app notification permission was denied.
* Fix BadTokenException.
* Fix long duration toast not work on Android N.

# Dependency

Step 1. Add the JitPack repository to your build file

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

```gradle
dependencies {
	...
	implementation 'com.github.kolacbb:ToastUtils:1.0.0'
}
```

# Usage

```java
Toaster.show("Hello");
```



