set JAVA_HOME=D:\Program Files\Java\jdk-12.0.2
set PATH=%PATH%;%JAVA_HOME%\bin;
set PATH=%PATH%;E:\Android\sdk\ndk-bundle\android-ndk-r20;D:\Android\ndk\android-ndk-r19c
rem javac -encoding utf-8 -d . callc.java
rem javah -jni com.durongze.jni.CallC
rem javac -encoding utf-8 -h . callc.java
del ..\MyApplication\app\libs\* /q /s
xcopy libs\* ..\MyApplication\app\libs\ /b /y /e /i /q
ndk-build NDK_PROJECT_PATH=. NDK_APPLICATION_MK=Application.mk APP_BUILD_SCRIPT=Android.mk
xcopy libs\* ..\MyApplication\app\libs\ /b /y /e /i /q
pause

rem adb shell am force-stop com.example.myapplication
rem adb shell am start -n "com.example.myapplication/com.example.myapplication.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -D
