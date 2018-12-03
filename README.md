[![](https://jitpack.io/v/kolacbb/ToastUtils.svg)](https://jitpack.io/#kolacbb/ToastUtils)

# ToastUtils
A ToastUtils with strong compatible


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
ToastUtils.show("Hello");
```



