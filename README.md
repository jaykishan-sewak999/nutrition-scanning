---

# Nutrak - Nutrients Scanning App 📱🍎

A nutrients analyzing app based on image processing algorithms built using modern tools and technology of Android. This app showcases clean architecture principles combined with the MVVM pattern for a scalable and maintainable codebase. It allows users to analyze nutrients of their meals just by taking a picture with phone's camera.

---

## Features ✨

### 1. Food scanning:
User friendly camera for conveniently take picture of your meal or food

### 2. Comprehensive Analysis:
The image analysis process considers ingredients of the food to provide information about important nutrients of it.
Analysis breakdown nutrition result into 3 parts:
1.  **Nutritional Overview**: Displays the amount of **calories** contained in the food item.
2.  **Macronutrients**: Breaks down the content of **protein**, **carbs**, and **fats** in the food.
3.  **Micronutrients**: Insights int amount of **vitamins** and **minerals** are within the food.

### 3. Streak feature:
- Helps user to stay consistent about what they consume by using the nutrak app.
- Multiple milestones which helps to increase motivation of a user.


---

## Tech Stack 🛠️

- **Programming Language:** [Kotlin](https://kotlinlang.org/)
- **UI Toolkit:** [Jetpack compose](https://developer.android.com/compose)
- **Architecture:** Clean Architecture + MVVM

## Libraries
- **Dependency Injection:** [Hilt](https://dagger.dev/hilt/)
- **Animations:**: [Lottie](https://github.com/airbnb/lottie-android)
- **Camera**: [CameraX](https://developer.android.com/jetpack/androidx/releases/camera)
- **Type safe navigation**: [Kotlin serialization](https://kotlinlang.org/docs/serialization.html)

---

## Project Structure 📂

The project adheres to Clean Architecture principles with distinct layers for data, domain, and presentation:

```
nutrition-scanning/
├── app/
│   ├── main/
│       ├── java/
│           ├── data/                 # Data layer
│           │   ├── di/               # Dependency injection modules
│           │   ├── repositoryimpl/   # Repository implementations
│           │   └── source/           # Data sources
│           │       ├── local/        # Local data sources (Room, SharedPrefs, etc.)
│           │       └── remote/       # Remote data sources (API, Retrofit, etc.)
│           ├── domain/               # Domain layer
│           │   ├── model/            # Business models
│           │   ├── repository/       # Repository interfaces
│           │   └── usecases/         # Use case definitions
│           ├── presentation/         # Presentation layer
│           │   ├── imageprocessing/  # Image processing screen
│           │   ├── nutritionresult/  # Nutrition result screen
│           │   ├── scanning/         # Scanning camera screen
│           │   └── streak/           # Streak screen
│           └── utils/                # Utility classes and extensions
└── build.gradle                      # Project configuration
```

---

## Screenshots 📸


### Lower resolution device
![Scanning screen](#)  
![Nutrition result screen](#)
![Streak screen](#)

### Higher resolution device
![Scanning screen](#)  
![Nutrition result screen](#)
![Streak screen](#)

---

## Installation 🚀

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/nutrition-scanning-app.git
   cd nutrition-scanning
   ```
2. Build and run the project in Android Studio. Check out this [Guide](https://developer.android.com/studio/run)


---

## License 📜

This project is licensed under the MIT License. See the `LICENSE` file for more details.

---