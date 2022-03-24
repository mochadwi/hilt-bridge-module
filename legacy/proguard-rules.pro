# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:

# -printconfiguration full-r8-config.txt
# -printusage full-r8-usage.txt
# -printseeds full-r8-seeds.txt

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes *Annotation*                      # Keep annotations
-keepattributes SourceFile,LineNumberTable        # Keep file names and line numbers.
-keep public class * extends java.lang.Exception  # Optional: Keep custom exceptions.
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**
-keep class com.google.firebase.crashlytics.** { *; }
-dontwarn com.google.firebase.crashlytics.**

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# ServiceLoader support
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepnames class kotlinx.coroutines.android.AndroidExceptionPreHandler {}
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory {}

# Most of volatile fields are updated with AFU and should not be mangled
-dontwarn org.jetbrains.annotations.**
-keep class kotlin.Metadata { *; }
-keepattributes RuntimeVisibleAnnotations
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# https://stackoverflow.com/a/60449002/3763032
-keep class java.time.zone.*

# DataBinding
-dontwarn androidx.databinding.**
-keep class androidx.databinding.** { *; }
-keep class * extends androidx.databinding.DataBinderMapper

# datastore
-keepclassmembers class * extends androidx.datastore.preferences.protobuf.GeneratedMessageLite {
    <fields>;
}

# gson
## dynamic-ui
-keepnames @kotlin.Metadata class com.google.gson.JsonObject.** { *; }
-keep class com.google.gson.JsonObject.** { *; }
-keepclassmembers class com.google.gson.JsonObject.** { *; }

## Keep generic signature of TypeToken class and its subclasses.
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

-keepnames @kotlin.Metadata class androidx.recyclerview.widget.SortedList
-keep class androidx.recyclerview.widget.SortedList { *; }
-keepclassmembers class androidx.recyclerview.widget.SortedList { *; }
-keepnames @kotlin.Metadata class com.google.android.material.tabs.TabLayoutMediator
-keep class com.google.android.material.tabs.TabLayoutMediator { *; }
-keepclassmembers class com.google.android.material.tabs.TabLayoutMediator { *; }

# all companion object
-keepclassmembers class * {
 public static ** Companion;
}

# branch.io
-keep class com.google.android.gms.** { *; }
-keep class com.facebook.applinks.** { *; }
-keepclassmembers class com.facebook.applinks.** { *; }
-keep class com.facebook.FacebookSdk { *; }
-keep class com.huawei.hms.ads.** { *; }
-keep interface com.huawei.hms.ads.** { *; }

# network
## okio
-dontwarn org.codehaus.mojo.animal_sniffer.*

## okhttp3
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform
-dontwarn org.conscrypt.ConscryptHostnameVerifier

## retrofit
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations,
                RuntimeVisibleParameterAnnotations,
                RuntimeVisibleTypeAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# Retain annotation default values for all annotations.
# Required until R8 version >= 3.1.12-dev (expected in AGP 7.1.0-alpha4).
-keep,allowobfuscation,allowshrinking @interface *
