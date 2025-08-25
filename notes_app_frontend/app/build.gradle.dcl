androidApplication {
    namespace = "org.example.app"

    dependencies {
        // AndroidX core and appcompat
        implementation("androidx.core:core-ktx:1.13.1")
        implementation("androidx.appcompat:appcompat:1.7.0")

        // Material Components
        implementation("com.google.android.material:material:1.12.0")

        // ConstraintLayout
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")

        // RecyclerView
        implementation("androidx.recyclerview:recyclerview:1.3.2")
        // CardView
        implementation("androidx.cardview:cardview:1.0.0")

        // Lifecycle ViewModel + LiveData
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")

        // Navigation Component (no Compose)
        implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
        implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

        // Room components
        implementation("androidx.room:room-runtime:2.6.1")
        implementation("androidx.room:room-ktx:2.6.1")

        // Security Crypto (for simple credential storage)
        implementation("androidx.security:security-crypto:1.1.0-alpha06")

        // Legacy utilities retained from template
        implementation("org.apache.commons:commons-text:1.11.0")
        implementation(project(":utilities"))
    }
}
