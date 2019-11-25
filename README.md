# CS310App
USClassifieds

In order to run the app properly, it's required to setup Google Maps API key.
1. Open SDK manager
2. Click on SDK tools tab
3. Check Google Play Services
4. Click apply
5. Save and close
6. In... res/val/google_maps_api.xml replace the last sentence with
 
 <string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">YOUR_KEY</string>
 
 where YOUR_KEY is the API key that can be easily obtained by following the steps at 

https://console.developers.google.com/flows/enableapi?apiid=maps_android_backend&keyType=CLIENT_SIDE_ANDROID&r=1A:EB:87:F8:53:0D:BA:0C:FD:45:F9:63:9C:B5:AC:18:58:24:CC:CA%3Bcom.example.cs310app

Now the app is ready to be run.


INSTRUCTIONS FOR TESTING:

To test the following test cases, follow these steps:
Open the application in Android Studio
Under the project view, navigate to the ‘AndroidTest’ folder inside of the source folder
Open the test file of the activity testing is required on
Click the run button next to the function to test

Note: Because current user data is saved whenever someone logs into their account, running ‘testCorrectLogin()’ in the LoginActivityTest.java file will force that login each time the application is run until the emulator data is cleared or the user has logged out. In order to test all black-box tests correctly run RegisterActivityTest.java first, then run LoginActivityTest.java, and then run any remaining test classes. If you would like to rerun any tests in LoginActivityTest or RegisterActivityTest, either wipe the emulator’s data in the AVD Manager or log out of the current account in the emulator to test these activities’ functionality.

