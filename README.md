# CRM Base (Android)

A simple Android CRM starter app built with Kotlin, Room database, and Material Design.

## Features
- View list of contacts/leads (name, company, phone)
- Add new contact
- Edit existing contact
- Delete contact (from list or detail screen)
- Local persistence via Room (SQLite)

## Tech Stack
- Kotlin
- Android Jetpack: Room, ViewModel, LiveData, ViewBinding
- Material Components
- RecyclerView with ListAdapter/DiffUtil

## Project Structure
```
app/src/main/java/com/example/crmapp/
├── Contact.kt              # Room entity
├── ContactDao.kt            # Database access object
├── AppDatabase.kt            # Room database
├── ContactViewModel.kt       # ViewModel
├── ContactAdapter.kt         # RecyclerView adapter
├── MainActivity.kt           # Contact list screen
└── AddEditContactActivity.kt # Add/edit/delete screen
```

## How to Open
1. Extract the zip.
2. Open the folder in Android Studio (File > Open).
3. Let Gradle sync.
4. Run on an emulator or device (minSdk 23).

## How to Push to GitHub
```bash
cd CRMApp
git init
git add .
git commit -m "Initial commit: CRM Base Android app"
git branch -M main
git remote add origin https://github.com/dharshdiva/android_CRM.git
git push -u origin main
```

## Next Steps / Ideas
- Add search/filter on contact list
- Add deal/pipeline tracking
- Add tags or status fields (Lead, Customer, etc.)
- Add cloud sync (Firebase/REST API)
- Add authentication
