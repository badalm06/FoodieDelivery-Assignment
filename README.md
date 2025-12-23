# FoodieDelivery – Android Internship Assignment
FoodieDelivery is a modern Android prototype of a food delivery app built with Kotlin, MVVM, and Retrofit. It focuses on a clean architecture, API‑driven data, and a polished, production‑like home screen experience.

---

## 🖼️ Screenshots

<img width="200" alt="Screenshot_20251223_203143" src="https://github.com/user-attachments/assets/f7c15212-f1cf-4177-ac53-7ccc78747731" /> &nbsp;&nbsp;&nbsp;
<img width="200" alt="Screenshot_20251223_203154" src="https://github.com/user-attachments/assets/0577b9d0-ec1a-4c28-bb69-d40fe8d5df1f" /> &nbsp;&nbsp;&nbsp;
<img width="200" alt="Screenshot_20251223_203204" src="https://github.com/user-attachments/assets/6dad6955-90a2-4878-98c6-ca73a52aa8ea" />


---

## ✨ Features
- **Dynamic category filtering:**
  Horizontal, icon-based categories (Pizza, Burger, Indian, Chinese, Dessert, Drinks, All) let users filter restaurants with a single tap, similar to real food delivery apps.
  
- **Live search:**
  Search-as-you-type on restaurant name and cuisine, with an empty state message when no results match.
  
- **“Top Rated” badge:**
  Green “Top Rated” pill badge appears automatically on restaurants with rating ≥ 4.5, computed from API data.

- **API-driven content:**
  Restaurants and images are loaded from the DummyJSON recipes API, not hardcoded.

- **Glide Image loading:**
  Smooth, cached image loading in the restaurant list using Glide, with placeholders while images load.


---


## 🏗 Architecture
The project follows a simple but clean MVVM structure:​

- **UI layer:**

  - MainActivity hosts the bottom navigation and fragment container.

  - HomeFragment renders the home screen, observes HomeViewModel, and handles search + category filters.

- **ViewModel layer**

  - HomeViewModel exposes LiveData<HomeUiState> (Loading / Success / Error) and triggers data loading from the repository.

- **Data layer**

  - RestaurantRepository calls the DummyJSON API through Retrofit and maps DTOs into the Restaurant domain model.

  - DTOs (RecipeDto, RecipeResponseDto) mirror the API response structure separately from the domain model.​


---


## 🌐 API Integration

- Base URL: `https://dummyjson.com/`
- Endpoint used: `GET /recipes` to fetch a list of recipes treated as restaurants.
- `RecipeApiService` defines a suspend `getRecipes()` method using Retrofit.
- `RestaurantRepository` calls this API, maps `RecipeDto` → `Restaurant`, and computes:
  - `deliveryTimeText` (e.g., `"25 min • ₹125"`)
  - `isFeatured` flag for the “Top Rated” badge based on `rating`.


---

## 🧠 UI Logic & User Flow

- On launch, `HomeViewModel` loads data and exposes `HomeUiState` (Loading / Success / Error).
- `HomeFragment` observes this state and:
  - shows a ProgressBar while loading,
  - shows the restaurant list on success,
  - shows an error message if the call fails.
- Search and category filters always work on a `fullRestaurantList` copy and update the adapter,
  so there is no inconsistent UI when switching between them.


---



## 🛠 Tech Stack
- **Language:** Kotlin

- **Architecture:** MVVM

- **Networking:** Retrofit + OkHttp + Gson

- **Async:** Kotlin Coroutines

- **State:** ViewModel + LiveData

- **UI:** XML layouts, ConstraintLayout, Material Components, CardView

- **Images:** Glide

- **Min SDK:** 21

- **Target SDK:** 34


---


## 📂 Project Structure (High Level)

 ```bash
app/src/main/
├─ ui/
│  ├─ MainActivity.kt
│  └─ home/
│     ├─ HomeFragment.kt
│     ├─ HomeViewModel.kt
│     ├─ HomeUiState.kt
│     ├─ CategoryAdapter.kt
│     └─ RestaurantAdapter.kt
├─ data/
│  ├─ model/Restaurant.kt
│  ├─ remote/
│  │  ├─ RecipeApiService.kt
│  │  ├─ RetrofitInstance.kt
│  │  └─ dto/ (RecipeDto, RecipeResponseDto)
│  └─ repository/RestaurantRepository.kt
└─ res/
   ├─ layout/ (activity_main, fragment_home, item_category, item_restaurant)
   ├─ drawable/ (badge + category backgrounds, icons)
   └─ menu/bottom_nav_menu.xml
```

---


## 🚀 Getting Started
Prerequisites:
- Android Studio (latest stable)

- Android SDK 33+

- Kotlin 1.9+

- Active internet connection (for API calls)​

Setup:
- Clone this repository.

- Open it in Android Studio; let Gradle sync.

- Run the app on an emulator or device (preferably Android 8.0+).


---

## ⚙️ Key Implementation Decisions
- MVVM + Repository: Keeps networking and mapping logic out of the Fragment and makes the code easier to maintain and test.​

- DTO → Domain mapping: API response models are kept separate and mapped into a clean Restaurant model with computed fields like deliveryTimeText and isFeatured.​

- ConstraintLayout for cards: Used 0dp widths with proper constraints so text and badges do not overlap on smaller screens.


---


## 💬 Contact & Support

For any queries or feedback, feel free to connect:

📧 Email: [badalsh908@gmail.com](mailto:badalsh908@gmail.com)  
🐙 GitHub: [github.com/badalm06](https://github.com/badalm06)
