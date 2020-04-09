set JAVA_HOME=D:\Program Files\Java\jdk1.8.0_60
set PATH=%PATH%;%JAVA_HOME%\bin;E:\Android\sdk\ndk-bundle\android-ndk-r20;D:\Android\ndk\android-ndk-r19c
rem javac -encoding utf-8 -d . callc.java
rem javah -jni com.durongze.jni.CallC
rem javac -encoding utf-8 -h . callc.java

ndk-build NDK_PROJECT_PATH=. NDK_APPLICATION_MK=Application.mk APP_BUILD_SCRIPT=Android.mk

pause